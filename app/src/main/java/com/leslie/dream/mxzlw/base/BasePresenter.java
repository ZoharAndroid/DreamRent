package com.leslie.dream.mxzlw.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;


import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.config.DreamApplication;
import com.leslie.dream.mxzlw.model.User;
import com.leslie.dream.mxzlw.util.CommonUtils;
import com.leslie.dream.mxzlw.util.SharePreferenceUtil;
import com.leslie.dream.mxzlw.util.ToastUtil;
import com.yolanda.nohttp.Headers;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.error.NetworkError;
import com.yolanda.nohttp.error.NotFoundCacheError;
import com.yolanda.nohttp.error.TimeoutError;
import com.yolanda.nohttp.error.URLError;
import com.yolanda.nohttp.error.UnKnownHostError;
import com.yolanda.nohttp.rest.JsonObjectRequest;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * Presenter 基类
 * 封装网络数据请求，数据解析
 * Created on 2017/7/7.
 */
public abstract class BasePresenter {

    protected final String TAG = CommonUtils.getTag(this);

    protected IBaseView view;
    protected RequestQueue requestQueue;
    protected Request<JSONObject> request;

    protected boolean show_error;    //toast 网络错误
    protected boolean cancel = true; //cancel上次请求 默认true
    protected boolean create_queue = true; //cancel上次请求 默认true

    protected int url_type;
    protected int load_type;
    protected boolean request_type;
    protected Bundle bundle;

    public BasePresenter(IBaseView view) {
        this(view, false);
    }

    public BasePresenter(IBaseView view, boolean show_error) {
        this(view, show_error, true);
    }

    public BasePresenter(IBaseView view, boolean show_error, boolean cancel) {
        this.view = view;
        init();

        this.show_error = show_error;
        this.cancel = cancel;
        if (create_queue){
            this.requestQueue = NoHttp.newRequestQueue();

        }

    }

    protected void init() {
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public void reqData(final int url_type, final boolean request_type) {
        reqData(url_type, -1,request_type);
    }

    public void reqDataReTry() {
        reqData(url_type, load_type, request_type, bundle);
    }

    public void reqData(final int url_type, final int load_type, final boolean request_type, final Bundle... bundle) {

        this.url_type = url_type;
        this.load_type = load_type;
        final Bundle bundle1 = bundle.length > 0 ? bundle[0] : null;
        this.bundle = bundle1;

        if (cancel && request != null) {
            request.cancel();
        }

        if (view.getContext() != null && !view.getContext().isFinishing()) {
            view.showLoadingUI(url_type, load_type);
        }

        String url = view.getUrl(url_type);
        Map<String, String> params = view.getParams(url_type, load_type, bundle1);

        if (params == null) {
            params = new HashMap<>();
        }

        if (request_type){
            request = new JSONObejctRequest(url, RequestMethod.POST);
        }else {
            request = new JSONObejctRequest(url, RequestMethod.GET);
        }

        User user = DreamApplication.getUser();
        String token = user.getToken();
        if (!"".equals(token)){
            Headers headers = request.headers();
            headers.add("x-access-token", token);//为请求头部添加token

        }

        request.setConnectTimeout(8000);

        onBindParams(url, params, request);

        OnJsonResponse listener = new OnJsonResponse() {

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                int responseCode = response.getHeaders().getResponseCode();
                JSONObject jsonObject = response.get();
                showError(view.getContext(), jsonObject.toString());
                if (responseCode >= 400 || jsonObject == null || jsonObject.length() <= 0) {
                    CommonUtils.log("responseCode = " + responseCode, TAG);
                    String error = "服务器错误" + (Constants.DEBUG_LOG ? responseCode : "");
                   // showError(view.getContext(), error);

                    view.hideLoadingUI(url_type, load_type, false);
                    view.onError(url_type, load_type, error);

                } else {

                    if ((view instanceof BaseFragment) || (view.getContext() != null && !view.getContext().isFinishing())) {
                        view.hideLoadingUI(url_type, load_type, true);



                        if ("110".equals(jsonObject.optString("code"))) {
                            User user = DreamApplication.getUser();
                            SharePreferenceUtil.saveCacheUser(view.getContext(), user);
                            DreamApplication.refreshUser();
                        }
                        onSuccess(jsonObject, url_type, load_type, bundle1);

                    }
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                Exception exception = response.getException();

                try {//修改  需捕获异常，当网络很差差或者无网络报错
                    CommonUtils.log("code = " + response.getHeaders().getResponseCode() + ", msg = " + exception.getMessage(), TAG);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                String error = onCheckError(exception);
                showError(view.getContext(), error);

                view.hideLoadingUI(url_type, load_type, false);
                view.onError(url_type, load_type, error);
            }
        };

        requestQueue.add(url_type, request, listener);
    }


    protected abstract void onSuccess(JSONObject jsonObject, int url_type, int load_type, Bundle bundle);

    public static interface IBaseView {

        public Activity getContext();

        public String getUrl(int url_type);

        public Map<String, String> getParams(int url_type, int load_type, Bundle bundle);


        public void onError(int url_type, int load_type, String error);

        public void showLoadingUI(int url_type, int load_type);

        public void hideLoadingUI(int url_type, int load_type, boolean success);


    }

    // 统一绑定常用参数
    public void onBindParams(String url, Map<String, String> params, Request<?> request) {

        Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
        CommonUtils.log("params url = " + url, TAG);
        while (it.hasNext()) {

            Map.Entry<String, String> entry1 = it.next();
            CommonUtils.log("params " + entry1.getKey() + " = " + entry1.getValue(), TAG);
            if (entry1.getValue() != null) {
                request.add(entry1.getKey(), entry1.getValue());
            }
        }
    }

    protected void showError(Context context, String error) {
        if (show_error && context != null) {
            ToastUtil.show(context, error);
        }
    }

    protected static String onCheckError(Exception exception) {
        String error = null;

        if (exception instanceof NetworkError) {// 网络不好
            error = "没有网络";
        } else if (exception instanceof TimeoutError) {// 请求超时
            error = "网络请求超时";
        } else if (exception instanceof UnKnownHostError) {// 找不到服务器
            error = "找不到服务器";
        } else if (exception instanceof URLError) {// URL是错的
            error = "网址错误";
        } else if (exception instanceof NotFoundCacheError) {
            // 这个异常只会在仅仅查找缓存时没有找到缓存时返回
            error = "没有发现缓存";
        } else if (exception instanceof ProtocolException) {
            error = "系统不支持的请求方式";
        } else {
            error = "请求超时 ";
        }

        exception.printStackTrace();

        return error;
    }

    /**
     * 自定义json解析规则
     *
     * @author dzl 2016/10/31.
     */
    public static class JSONObejctRequest extends JsonObjectRequest {

        String tag;

        public JSONObejctRequest(String url, RequestMethod requestMethod, String... tag) {
            super(url, requestMethod);
            this.tag = tag.length > 0 ? tag[0] : "BasePresenter";
        }

        @Override
        public JSONObject parseResponse(Headers responseHeaders, byte[] responseBody) {


            JSONObject jsonObject = null;
            String jsonStr = StringRequest.parseResponseString(responseHeaders, responseBody);

            CommonUtils.log("msg = " + jsonStr, tag);
            if (!TextUtils.isEmpty(jsonStr)) {

                try {
                    jsonObject = new JSONObject(jsonStr);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            if (jsonObject == null) {
                try {
                    jsonObject = new JSONObject("{}");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return jsonObject;

        }

    }

    public static abstract class OnJsonResponse implements OnResponseListener<JSONObject> {

        @Override
        public void onStart(int what) {

        }

        @Override
        public void onFinish(int what) {

        }

    }

}
