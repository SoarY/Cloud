package com.soar.cloud.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.soar.cloud.MyApplication;


public class SPUtils {

    /**
     * 应用名默认SP  context.getPackageName() + "_preferences"
     */
    private static SharedPreferences getSharedPreferences() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return sp;
    }

    /**
     * 用户自定义SP文件名
     */
    private static SharedPreferences getSharedPreferences(String spName) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp;
    }

    /**
     * 清除SP
     */
    public static void clearSP() {
        SharedPreferences.Editor editor = getSharedPreferences().edit();
        editor.clear();
        editor.apply();
    }

    public static void clearSP(String spName) {
        SharedPreferences.Editor editor = getSharedPreferences(spName).edit();
        editor.clear();
        editor.apply();
    }

    /**
     * putString
     */
    public static void putString(String key, String value) {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void putString(String spName, String key, String value) {
        SharedPreferences sp = getSharedPreferences(spName);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /**
     * getString
     */
    public static String getString(String key) {
        SharedPreferences sp = getSharedPreferences();
        String result = sp.getString(key, null);
        return result;
    }

    public static String getString(String spName, String key) {
        SharedPreferences sp = getSharedPreferences(spName);
        String result = sp.getString(key, null);
        return result;
    }

    /**
     * putBoolean
     */
    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = getSharedPreferences();
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putBoolean(String spName, String key, boolean value) {
        SharedPreferences sp = getSharedPreferences(spName);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * getBoolean
     */
    public static boolean getBoolean(String key) {
        SharedPreferences sp = getSharedPreferences();
        boolean result = sp.getBoolean(key, false);
        return result;
    }

    public static boolean getBoolean(String spName, String key) {
        SharedPreferences sp = getSharedPreferences(spName);
        boolean result = sp.getBoolean(key, false);
        return result;
    }

    /**
     * SPConstant
     */
    public static class SPkey {
        public static final String TOKEN = "TOKEN";

        public static final String HOME_ONE = "home_one";
        public static final String HOME_TWO = "home_two";
        public static final String HOME_SIX = "home_six";
    }
}
