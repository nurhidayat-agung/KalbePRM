package edu.swu.pulltorefreshswipemenulistview.library.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class RefreshTime {
    private final static String PRE_NAME = "refresh_time";
    private final static String SET_FRESHTIME = "set_refresh_time";

    public static String getRefreshTime(Context context) {
        @SuppressLint("WrongConstant") SharedPreferences preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
        return preferences.getString(SET_FRESHTIME, "");
    }

    public static void setRefreshTime(Context context, String newPasswd) {
        @SuppressLint("WrongConstant") SharedPreferences preferences = context.getSharedPreferences(PRE_NAME, Context.MODE_APPEND);
        Editor editor = preferences.edit();
        editor.putString(SET_FRESHTIME, newPasswd);
        editor.commit();
    }

}