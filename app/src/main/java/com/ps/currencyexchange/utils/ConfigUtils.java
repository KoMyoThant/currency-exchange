package com.ps.currencyexchange.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by pyaesone on 12/24/17.
 */

public class ConfigUtils {

    private static final String KEY_PAGE_INDEX = "KEY_PAGE_INDEX";

    private SharedPreferences mSharedPreferences;

    private static int NEWS_DETAILS_LOADER_ID = 1002;

    private static ConfigUtils mObjInstance;


    private ConfigUtils(Context context) {
        mSharedPreferences = context.getSharedPreferences("ConfigUtils", Context.MODE_PRIVATE);
        savePageIndex(1);
    }

    public static ConfigUtils getInstance(Context context) {
        if (mObjInstance == null) {
            mObjInstance = new ConfigUtils(context);
        }
        return mObjInstance;
    }

    public void savePageIndex(int pageIndex) {
        mSharedPreferences.edit().putInt(KEY_PAGE_INDEX, pageIndex).apply();
    }

    public int loadPageIndex() {
        return mSharedPreferences.getInt(KEY_PAGE_INDEX, 1);
    }
}
