package com.leslie.dream.mxzlw.config;

/**
 * Created by Quinn on 2017/7/10.
 */

public class UrlApi {

    public static final String HOST = "http://192.168.1.220:8000"; // 域名
    public static final String USER_REG = HOST + "/users/reg"; // 用户注册
    public static final String USER_GET_SMS_NUM = HOST + ""; // 短信验证
    public static final String USER_LOGIN = HOST + "/users/login"; // 用户登录
    public static final String EDIT_MY_USER_INFOR = HOST + "/users/editprofile"; // 修改个人信息


    public static final String MAIN_HOST = "http://192.168.1.220:8080";//Page 主页地址
    public static final String HOSUE_DETAIL = MAIN_HOST +"/page/details";//Page 房屋详情页地址
    public static final String MAIN_PAGER = MAIN_HOST + "/page/main/";//Page 主页数据

}
