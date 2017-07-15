package com.leslie.dream.mxzlw.config;

import android.os.Environment;

/**
 * 常量 路径配置（eg:db、img、sp）
 *
 * @Author dzl on 2017/7/5.
 */
public class Constants {
    public static final String ROOT_DIR_NAME = "dream";

    public static final String SD_STORAGE_PATH = Environment.getExternalStorageDirectory().getPath();

    public static final String SD_FILE_PATH = SD_STORAGE_PATH + "/" + ROOT_DIR_NAME;

    public static final String SD_DOWNLOAD_PATH = SD_FILE_PATH + "/download";
    public static final String SD_IMG_PATH = SD_FILE_PATH + "/img";

    public static final String USER_LOGO_FILE_NAME = "user_avatar.jpg"; // 头像上传名称
    public static final String USER_INFO = "user_info";







    //测试环境请改成true   (发布外网时请设置BEBUG=false和 BEBUG_SERVER = false)
    public static final boolean BEBUG = true;

    public static final boolean BEBUG_SERVER =true;

    public static final boolean BD_LOG = !BEBUG && !BEBUG_SERVER; 	//百度统计
    public static final boolean BD_LOCATION = false; 		//百度定位功能



    /**
     * 调试参数
     */
    public static final boolean DEBUG_LOG = BEBUG; //
    public static final boolean DEBUG_URL_PARAMETER = BEBUG; //请求参数
    public static final boolean DEBUG_URL_RESULT = BEBUG;	//请求结果


    /**
     * 界面跳转传递的key
     */
    public static final String HOUSE_DETAIL_DATA="house_detail_data";//房屋详情数据key

    public static final String HOME_TOP_BANNER="home_top_banner";//首页广告数据key

    /**
     * 搜索记录数据库名
     */
    public static final String DB_SEARCH_RECORD="seach_record";//搜索数据库名


}
