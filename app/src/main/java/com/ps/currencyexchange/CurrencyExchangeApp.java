package com.ps.currencyexchange;

import android.app.Application;
import android.os.SystemClock;

import java.util.concurrent.TimeUnit;

/**
 * Created by pyaesone on 11/25/18
 */
public class CurrencyExchangeApp extends Application {
    public static final String TAG = CurrencyExchangeApp.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(1));
    }
}
