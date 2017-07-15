package com.leslie.dream.mxzlw.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextPaint;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dzl on 17/7/5.
 */

public class Util_0 extends CommonUtils {


    public static String getValue(int index, String... key) {
        if (key.length > index) {
            return key[index];
        }
        return null;
    }

    /**
     * 解析成对象 根据key值
     */
    public static <T> T parseObject(JSONObject object, Class<T> clz, String... key) {
        String four = getValue(3, key);
        String root = getValue(2, key);
        String parent = getValue(1, key);
        String child = getValue(0, key);

        try {

            String json;

            if (four != null) {
                json = object.getJSONObject(four).getJSONObject(root).getJSONObject(parent).getJSONObject(child).toString();
            } else if (root != null) {
                json = object.getJSONObject(root).getJSONObject(parent).getJSONObject(child).toString();
            } else if (parent != null) {
                json = object.getJSONObject(parent).getJSONObject(child).toString();
            } else if (child != null) {
                json = object.getJSONObject(child).toString();
            } else {
                json = object.toString();
            }
            return JSON.parseObject(json, clz);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析成集合 根据key值
     */
    public static <T> List<T> parseArray(JSONObject object, Class<T> clz, String... key) {
        String four = getValue(3, key);
        String root = getValue(2, key);
        String parent = getValue(1, key);
        String child = getValue(0, key);

        try {

            String json;
            if (four != null) {
                json = object.getJSONObject(four).getJSONObject(root).getJSONObject(parent).getJSONArray(child).toString();
            } else if (root != null) {
                json = object.getJSONObject(root).getJSONObject(parent).getJSONArray(child).toString();
            } else if (parent != null) {
                json = object.getJSONObject(parent).getJSONArray(child).toString();
            } else if (child != null) {
                json = object.getJSONArray(child).toString();
            } else {
                json = object.toString();
            }
            return JSON.parseArray(json, clz);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取json中 key的value
     */
    public static String getJsonStringValue(JSONObject object, String... key) {

        String parent = getValue(1, key);
        String child = getValue(0, key);

        String json = null;
        try {

            if (parent != null) {
                json = object.getJSONObject(parent).optString(child);
            } else {
                json = object.optString(child);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

    /**
     * 获取json中 key的value
     */
    public static int getJsonIntValue(JSONObject object, String... key) {
        try {
            return Integer.parseInt(getJsonStringValue(object, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取json中 key的value
     */
    public static double getJsonDoubleValue(JSONObject object, String... key) {
        try {
            return Double.parseDouble(getJsonStringValue(object, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取json中 key的value
     */
    public static long getJsonLongValue(JSONObject object, String... key) {

        try {
            return Long.parseLong(getJsonStringValue(object, key));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 解析成集合 根据key值（处于第三层结构）
     *
     * @return
     */
    public static Map<String, List<?>> parseArrays(JSONObject object, Class<?>[] clzs, String[] keys,
                                                   String keyParent) {

        Map<String, List<?>> map = new HashMap<String, List<?>>();

        JSONObject parent = null;
        if (keyParent != null) {
            try {
                parent = object.getJSONObject(keyParent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            parent = object;
        }


        for (int i = 0, length = keys.length; i < length; i++) {
            List<?> list2 = null;
            try {
                list2 = JSON.parseArray(parent.getJSONArray(keys[i]).toString(), clzs[i]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (list2 != null) {
                map.put(keys[i], list2);
            }

        }

        return map;

    }

    /**
     * 获取assets文件文本
     */
    public static String getAssetsText(Context context, String file_name) {

        String text = null;

        BufferedReader br = null;

        try {
            StringBuffer sb = new StringBuffer();
            br = new BufferedReader(new InputStreamReader(context.getAssets().open(file_name)));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            text = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }

        return text;

    }

    /**
     * 减法
     */
    public static double minus(double d1, double d2) {

        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);

        return b1.subtract(b2).doubleValue();
    }

    public static void setActionbarTranslate(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static int getStatusBarHeight(Context context) {

        if (TOP_BAR_HEIGHT == 0) {
            int resourceId = context.getResources().getIdentifier("status_bar_height",
                    "dimen", "android");
            if (resourceId > 0) {
                TOP_BAR_HEIGHT = context.getResources().getDimensionPixelSize(resourceId);
            }
        }

        if (TOP_BAR_HEIGHT <= 0) {
            TOP_BAR_HEIGHT = 38;
        }

        return TOP_BAR_HEIGHT;
    }

    public static void setActionbarHolderViewHeight(Context context, View view_holder) {
        if (view_holder == null) {
            return;
        }
        setViewVisible(view_holder, true);
        int height = getStatusBarHeight(context);
        ViewGroup.LayoutParams params = view_holder.getLayoutParams();
        params.height = height;
        view_holder.setLayoutParams(params);
    }

    private static int TOP_BAR_HEIGHT = 0;

    public static void setCenterLine(TextView textView) {
        if (textView == null) {
            return;
        }
        TextPaint paint = textView.getPaint();
        paint.setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        paint.setAntiAlias(true);
    }

    public static int[] getViewLocation(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return location;
    }
}
