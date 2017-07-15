package com.leslie.dream.mxzlw.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.leslie.dream.mxzlw.R;
import com.leslie.dream.mxzlw.adapter.PersonalAdapter;
import com.leslie.dream.mxzlw.base.BaseActivity;
import com.leslie.dream.mxzlw.base.BaseAdapter;
import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.config.DreamApplication;
import com.leslie.dream.mxzlw.config.UrlApi;
import com.leslie.dream.mxzlw.dialog.LockUiDialog;
import com.leslie.dream.mxzlw.dialog.SecondCommitDialog;
import com.leslie.dream.mxzlw.dialog.SelectDialog;
import com.leslie.dream.mxzlw.model.User;
import com.leslie.dream.mxzlw.presenter.PersonalPresenter;
import com.leslie.dream.mxzlw.util.QRCodeUtil;
import com.leslie.dream.mxzlw.util.SharePreferenceUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.leslie.dream.mxzlw.util.FormAuthUtil.convertMD5;
import static com.leslie.dream.mxzlw.util.FormAuthUtil.string2MD5;


public class PersonalActivity extends BaseActivity implements PersonalPresenter.IPersonalView{

    private static final int EDIT_AVATAR = 1;   //修改头像
    private static final int EDIT_MY_USER_INFOR_UNAME = 2;  //修改昵称
    private static final int EDIT_MY_USER_INFOR_SEX = 3;    //修改性别
    private LockUiDialog dialog;
    private PersonalPresenter presenter;

    private LinearLayout ll_personal_head;
    private LinearLayout ll_personal_uname;
    private LinearLayout ll_personal_phone;
    private LinearLayout ll_personal_sex;//
    private LinearLayout ll_personal_code;//推荐码
    private LinearLayout ll_personal_address;
    private LinearLayout ll_personal_realname;
    private LinearLayout ll_personal_password;

    private LinearLayout ll_login_out;

    private RelativeLayout actionbar_back_rl;

    private ImageView img_personl_head;
    private TextView tv_personl_uname;
    private TextView tv_personl_phone;
    private TextView tv_personl_sex;
    private TextView tv_personal_address;

    //修改昵称
    private Dialog dialog_uname;
    private EditText dialog_et_uname;
    private TextView dialog_tv_uname_confirm;


    private Bitmap head;//头像Bitmap

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        initView();

    }

    private void initView() {
        actionbar_back_rl = fv(R.id.back_rl);

        ll_personal_head = fv(R.id.ll_personal_head);
        ll_personal_uname = fv(R.id.ll_personal_uname);
        ll_personal_phone = fv(R.id.ll_personal_phone);
        ll_personal_sex = fv(R.id.ll_personal_sex);
        ll_personal_code = fv(R.id.ll_personal_code);
        ll_personal_address = fv(R.id.ll_personal_address);
        ll_personal_realname = fv(R.id.ll_personal_realname);
        ll_personal_password = fv(R.id.ll_personal_password);
        ll_login_out = fv(R.id.ll_login_out);
        img_personl_head = fv(R.id.img_personl_head);

        tv_personl_sex = fv(R.id.tv_personl_sex);
        tv_personl_uname = fv(R.id.tv_personl_uname);
        tv_personl_phone = fv(R.id.tv_personl_phone);
        tv_personal_address = fv(R.id.tv_personal_address);

        //昵称
        dialog_uname = createDialog(R.layout.dialog_uname, R.style.LoadingDialog, true);
        dialog_et_uname = (EditText) dialog_uname.findViewById(R.id.dialog_et_uname);
        dialog_tv_uname_confirm = (TextView) dialog_uname.findViewById(R.id.dialog_tv_uname_confirm);

        User user = DreamApplication.getUser();
        String token = user.getToken();
        if (!"".equals(token)){
            tv_personl_phone.setText(user.getUsername());
            String sex = user.getSex();
            String name = user.getNickname();
            if (sex == null || sex.length() <= 0){
                tv_personl_sex.setText("未填写");
            }else {
                tv_personl_sex.setText(sex);
            }
            if (name == null || name.length() <= 0){
                tv_personl_uname.setText("未填写");
            }else {
                tv_personl_uname.setText(name);
            }
        }

        setOnClickListener(ll_personal_head);
        setOnClickListener(ll_personal_uname);
        setOnClickListener(ll_personal_phone);
        setOnClickListener(ll_personal_sex);
        setOnClickListener(ll_personal_code);
        setOnClickListener(ll_personal_address);
        setOnClickListener(ll_personal_realname);
        setOnClickListener(ll_personal_password);
        setOnClickListener(actionbar_back_rl);

        setOnClickListener(ll_login_out);
        setOnClickListener(dialog_tv_uname_confirm);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.ll_personal_head: //修改头像
                edit_avatar();
                break;
            case R.id.ll_personal_uname: //昵称
                edit_name();
                break;
            case R.id.ll_personal_phone: //手机号

                break;
            case R.id.ll_personal_sex: //性别
                edit_sex();
                break;
            case R.id.ll_personal_code: //推荐码
                myInfoQrCode();
                break;
            case R.id.ll_personal_realname: //实名

                break;
            case R.id.ll_personal_address: //地址

                break;
            case R.id.ll_personal_password: //修改密码
                startActivity(ForgetActivity.class);
                break;
            case R.id.back_rl:   //左关闭
                finish();
                break;
            case R.id.ll_login_out:   //退出登录
                login_out();
                break;

        }

    }

    //修改昵称
    private void edit_name() {

        dialog_et_uname.setText(tv_personl_uname.getText().toString().trim());//原有的昵称
        dialog_tv_uname_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = dialog_et_uname.getText().toString().trim();
                if (name != null && name.length() > 0){
                    setText(tv_personl_uname,name);
                    reqData(EDIT_MY_USER_INFOR_UNAME, LOAD_AUTO);
                    dialog_uname.dismiss();
                }else {
                    toast("请输入昵称");
                }

            }
        });

        dialog_uname.show();


    }

    //修改性别
    private void edit_sex() {
        final List<String> sex_datas = new ArrayList<>();
        sex_datas.add("保密");
        sex_datas.add("男");
        sex_datas.add("女");
        PersonalAdapter sex_adapter = new PersonalAdapter(context, sex_datas, 2);

        final SelectDialog sex_selectDialog = new SelectDialog(context, R.style.LoadingDialog);
        sex_selectDialog.setContentText("修改性别");
        sex_selectDialog.dialog_recyclerview.setIAdapter(sex_adapter);
        sex_adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position, int... what) {
                if (position == 0) {
                    setText(tv_personl_sex, "保密");
                } else if (position == 1) {
                    setText(tv_personl_sex, "男");
                } else if (position == 2) {
                    setText(tv_personl_sex, "女");
                }
                reqData(EDIT_MY_USER_INFOR_SEX, LOAD_AUTO);
                sex_selectDialog.dismiss();
            }
        });
        sex_selectDialog.show();
    }

    //修改头像
    private void edit_avatar() {

        final List<String> head_datas = new ArrayList<>();
        head_datas.add("相册");
        head_datas.add("拍照");
        PersonalAdapter head_adapter = new PersonalAdapter(this, head_datas, 1);
        final SelectDialog head_selectDialog = new SelectDialog(this, R.style.LoadingDialog);
        head_selectDialog.setContentText("修改头像");
        head_selectDialog.dialog_recyclerview.setIAdapter(head_adapter);
        head_adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View itemView, int position, int... what) {
                switch (position) {
                    case 0: //相册
                        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                        startActivityForResult(intent1, 1);
                        head_selectDialog.dismiss();
                        break;
                    case 1: //拍照
                        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                                "/" + Constants.USER_LOGO_FILE_NAME)));
                        startActivityForResult(intent2, 2);//采用ForResult打开
                        head_selectDialog.dismiss();
                        break;
                }
            }
        });
        head_selectDialog.show();

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }

                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/" + Constants.USER_LOGO_FILE_NAME);
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }

                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if (head != null) {
                        String path = setPicToView(head);//保存在SD卡中
                        img_personl_head.setImageBitmap(head);
                        /**
                         * 上传头像
                         */

                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 调用系统的裁剪
     *
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 350);
        intent.putExtra("outputY", 350);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private String setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return null;
        }
        FileOutputStream b = null;
        File file = new File(Constants.SD_IMG_PATH);
        file.mkdirs();// 创建文件夹
        SimpleDateFormat formatter   =   new SimpleDateFormat("yyyyMMddHHmmss");
        Date curDate =  new Date(System.currentTimeMillis());
        String   time   =   formatter.format(curDate);
        String fileName = Constants.SD_IMG_PATH + "/" + time+".jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return fileName;
    }

    //退出登录
    private void login_out() {
        SecondCommitDialog secondCommitDialog = new SecondCommitDialog(context, R.style.LoadingDialog);
        secondCommitDialog.setContentText("确定退出?");
        secondCommitDialog.setDialogItemClickListener(new SecondCommitDialog.OnDialogItemClickListener() {
            @Override
            public void onSecondCommitListener(boolean is_commit) {
                if (is_commit) {
                    User user = DreamApplication.getUser();
                    user.setToken("");
                    SharePreferenceUtil.saveCacheUser(context, user);
                    DreamApplication.refreshUser();

                    finish();
                }
            }
        });
        secondCommitDialog.show();
    }

    //我的二维码弹窗
    private void myInfoQrCode() {
        User user = DreamApplication.getUser();
        String phone = user.getUsername();

        Dialog dialog = new Dialog(context);
        //dialog.setTitle("我的二维码");
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dialog.setContentView(R.layout.dialog_qrcode);

        //Resources r = getContext().getResources();
        ImageView image = (ImageView) dialog.findViewById(R.id.iv_myQrcode);
        //Bitmap logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.portrait);
        //String path = Constants.ROOT_URL + "invite_user_reg?uid=" + uid + "&type=client-info&time=" + TimeUtil.getTimestamp();
        image.setImageBitmap(QRCodeUtil.createImage(phone, 300, 300, null));

        dialog.show();


    }

    @Override
    public void onSuccessUser(int url_type, int load_type, int success, String msg, String data) {
        if (success == 1){
            toast("修改成功");
            User user = DreamApplication.getUser();
            if (url_type == EDIT_AVATAR) {
                //头像
            }else if (url_type == EDIT_MY_USER_INFOR_UNAME){
                user.setNickname(data);
            }else if (url_type == EDIT_MY_USER_INFOR_SEX){
                user.setSex(data);
            }
            SharePreferenceUtil.saveCacheUser(context, user);
            DreamApplication.refreshUser();
        }else {
            toast(msg);
        }

    }

    public void reqData(int url_type, int load_type) {
        if (presenter == null) {
            presenter = new PersonalPresenter(this, true);
        }
        presenter.reqData(url_type, load_type,true);
    }

    @Override
    public String getUrl(int url_type) {
        return UrlApi.EDIT_MY_USER_INFOR;
    }

    @Override
    public Map<String, String> getParams(int url_type, int load_type, Bundle bundle) {
        Map<String, String> params = new HashMap<>();
        User user = DreamApplication.getUser();
        params.put("_id", user.get_id());
        if (url_type == EDIT_MY_USER_INFOR_UNAME) { //昵称
            params.put("nickname", dialog_et_uname.getText().toString().trim());

        } else if (url_type == EDIT_MY_USER_INFOR_SEX) { //性别
            String s = tv_personl_sex.getText().toString().trim();
            params.put("sex", s);
        } else if (url_type == EDIT_AVATAR){//头像

        }

        return params;
    }

    @Override
    public void showLoadingUI(int url_type, int load_type) {
        if (url_type == EDIT_AVATAR) {
            if (dialog == null) {
                dialog = new LockUiDialog(context, R.style.LoadingDialog);
                dialog.setContentText("修改头像");
            }
            dialog.show();
        } else if (url_type == EDIT_MY_USER_INFOR_UNAME) {
            if (dialog == null) {
                dialog = new LockUiDialog(context, R.style.LoadingDialog);
                dialog.setContentText("修改昵称");
            }
            dialog.show();
        } else if (url_type == EDIT_MY_USER_INFOR_SEX) {
            if (dialog == null) {
                dialog = new LockUiDialog(context, R.style.LoadingDialog);
                dialog.setContentText("修改性别");
            }
            dialog.show();
        }
    }

    @Override
    public void hideLoadingUI(int url_type, int load_type, boolean success) {
        if (url_type == EDIT_AVATAR) {
            dialog.dismiss();
        } else if (url_type == EDIT_MY_USER_INFOR_UNAME) {
            dialog.dismiss();
        } else if (url_type == EDIT_MY_USER_INFOR_SEX) {
            dialog.dismiss();
        }
    }
}
