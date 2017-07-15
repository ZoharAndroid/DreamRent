package com.leslie.dream.mxzlw.util;


import android.util.Base64;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Author dzl on 2017/7/10.
 * 校验工具类
 */
public class FormAuthUtil {

    /**
     * 校验phone
     */
    public static String checkPhone(String text) {
        if (CommonUtils.isEmpty(text)) {
            return "手机号不能为空";

        } else if (text.length() < 11) {
            return "手机号的字长度不足11位";

        } else if (text.matches("^1[3-9]\\\\d{9}$")) {
            return "手机号的格式不对";
        }

        return null;
    }

    /**
     * 校验密码
     */
    public static String checkPasswd(String text) {

        if (CommonUtils.isEmpty(text)) {
            return "密码不能为空";

        } else if (text.length() < 6) {
            return "密码的字长度不足6位";

        } else if (!text.matches("^[0-9a-zA-Z_]{6,16}$")) {
            return "密码的字含有非法字符";

        }

        return null;
    }

    /**
     * 校验验证码
     */
    public static String checkSms(String text) {

        if (CommonUtils.isEmpty(text)) {
            return "验证码不能为空";

        } else if (text.length() < 4) {
            return "验证码的字长度不足4位";

        } else if (!text.matches("^\\d{4,10}$")) {
            return "验证码的格式不对，要4到10位的纯数字";

        }

        return null;
    }

    /**
     * 校验相同字符
     */
    public static boolean checkEqual(String text, String text2) {

        if (text == null) {
            return false;
        }

        return text.equals(text2);
    }

    /**
     * 校验相同字符 返回msg
     */
    public static String checkEqual(String text, String text2, String msg) {

        if (!checkEqual(text, text2)) {
            return msg;
        }

        return null;
    }

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String str){
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++){
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public static String convertMD5(String inStr){

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++){
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }



}
