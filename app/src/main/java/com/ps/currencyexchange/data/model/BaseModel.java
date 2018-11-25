package com.ps.currencyexchange.data.model;

import android.arch.lifecycle.ViewModel;
import android.content.Context;

import com.ps.currencyexchange.data.db.AppDatabase;
import com.ps.currencyexchange.network.RetrofitDataAgent;
import com.ps.currencyexchange.utils.ConfigUtils;
/**
 * Created by pyaesone on 1/31/18.
 */

public class BaseModel extends ViewModel {

    protected AppDatabase mAppDatabase;

    protected RetrofitDataAgent retrofitDataAgent;

    protected ConfigUtils configUtils;

    public BaseModel() {
        retrofitDataAgent = RetrofitDataAgent.getInstance();
    }

    public void initDatabase(Context context) {
        mAppDatabase = AppDatabase.getInMemoryDatabase(context);
        configUtils = ConfigUtils.getInstance(context);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        AppDatabase.destroyInstance();
    }
}
