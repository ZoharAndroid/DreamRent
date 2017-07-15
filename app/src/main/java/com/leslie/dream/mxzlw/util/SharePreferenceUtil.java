package com.leslie.dream.mxzlw.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.alibaba.fastjson.JSON;
import com.leslie.dream.mxzlw.base.BaseModel;
import com.leslie.dream.mxzlw.config.Constants;
import com.leslie.dream.mxzlw.model.User;

import java.util.Map;

/**
 * SharePreference
 * <p/>
 * 封装工具
 * <pre>
 * SharePreferenceUtil.cache(context, "key", "value");
 */
public class SharePreferenceUtil {

    private static final String SP_NAME = "dream" + "_ut_sp";

    private SharePreferenceUtil() {
    }

    public static SharedPreferences getSharePreference(Context context) {
        return context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    /**
     * 缓存数据
     * <p/>
     * obj.length == 1 && obj[0] instanceof String 则是删除数据
     */
    public static boolean cache(Context context, Object... obj) {

        if (obj.length <= 0) {
            return false;
        }

        Editor edit = getSharePreference(context).edit();
        if (obj.length == 1) {

            if (!(obj[0] instanceof String)) {
                return false;
            }
            return edit.remove((String) obj[0]).commit();
        }

        for (int i = 0, len = obj.length; i < len; i += 2) {

            if (obj[i] instanceof String) {
                cacheObject((String) obj[i], edit, obj[i + 1]);
            }
        }

        return edit.commit();

    }

    /**
     * 获取String
     */
    public static String getString(Context context, String key, String defalutValue) {
        return getSharePreference(context).getString(key, defalutValue);
    }

    /**
     * 获取 int
     */
    public static int getInt(Context context, String key, int defalutValue) {
        return getSharePreference(context).getInt(key, defalutValue);
    }

    /**
     * 获取 float
     */
    public static float getFloat(Context context, String key, float defalutValue) {
        return getSharePreference(context).getFloat(key, defalutValue);
    }

    /**
     * 获取 long
     */
    public static long getLong(Context context, String key, long defalutValue) {
        return getSharePreference(context).getLong(key, defalutValue);
    }

    /**
     * 获取 boolean
     */
    public static boolean getBoolean(Context context, String key, boolean defalutValue) {
        return getSharePreference(context).getBoolean(key, defalutValue);
    }

    /**
     * 获取 所有
     */
    public static Map<String, ?> getAll(Context context) {
        return getSharePreference(context).getAll();
    }

    /**
     * 设置值
     */
    public static void cacheObject(String key, Editor edit, Object o) {
        if (o instanceof Integer) {
            edit.putInt(key, (Integer) o);

        } else if (o instanceof String) {
            edit.putString(key, (String) o);

        } else if (o instanceof Float) {
            edit.putFloat(key, (Float) o);

        } else if (o instanceof Long) {
            edit.putLong(key, (Long) o);

        } else if (o instanceof Boolean) {
            edit.putBoolean(key, (Boolean) o);

        } else {
            edit.putString(key, o.toString());
        }
    }

    /**
     * 获取缓存user
     */
    public static User getCacheUser(Context context) {

        User user = new User();

        String user_info = getString(context, Constants.USER_INFO, null);
        User user_sp = null;
        if (!CommonUtils.isEmpty(user_info)) {
            try {
                user_sp = JSON.parseObject(user_info, User.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (user_sp != null) {

            user.set_id(user_sp.get_id());
            user.setSex(user_sp.getSex());
            user.setRealname(user_sp.getRealname());
            user.setProfession(user_sp.getProfession());
            user.setNickname(user_sp.getNickname());
            user.setActive(user_sp.isActive());
            user.setAge(user_sp.getAge());
            user.setUsername(user_sp.getUsername());
            user.setToken(user_sp.getToken());


            //继续添加
        }


        return user;

    }

    /**
     * 保存缓存user
     */
    public static void saveCacheUser(Context context, User user) {
        cache(context, Constants.USER_INFO, JSON.toJSONString(user));
    }

    /**
     * 保存缓存
     */
    public static void saveCache(Context context,String key, BaseModel model) {
        cache(context, key, JSON.toJSONString(model));
    }
    /**
     * 获取缓存
     */
    public static BaseModel getCache(Context context, String key, Class cla){
        String info = getString(context, key, null);
        BaseModel model = null;
        if (!CommonUtils.isEmpty(info)) {
            try {
                model = (BaseModel)JSON.parseObject(info, cla);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return model;
    }


}
