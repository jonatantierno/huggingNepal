package com.katmandu.katmandu;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jonatan on 5/05/15.
 */
public class PasswordStore {
    private static final String PASSWORD_STORE = "PASSWORD_STORE";
    private static final String PASSWORD = "PASSWORD";

    public static void store(String password, Context context) {
        SharedPreferences settings = context.getSharedPreferences(PASSWORD_STORE, 0);
        settings.edit().putString(PASSWORD, password).commit();
    }

    public static String get(Context context) {
        return context.getSharedPreferences(PASSWORD_STORE, 0).getString(PASSWORD,null);
    }

    public static boolean exists(Context context) {
        return context.getSharedPreferences(PASSWORD_STORE, 0).contains(PASSWORD);
    }
}
