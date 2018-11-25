package com.ps.currencyexchange.data.model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.util.Log;

import com.ps.currencyexchange.CurrencyExchangeApp;
import com.ps.currencyexchange.data.vos.CurrencyRateVO;
import com.ps.currencyexchange.events.RestApiEvents;
import com.ps.currencyexchange.network.responses.GetCurrencyRateResponse;
import com.ps.currencyexchange.utils.NetworkUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pyaesone on 1/26/18.
 */

public class CurrencyRateModel extends BaseModel {

    //TODO to delete
    public MutableLiveData<List<CurrencyRateVO>> currencyRateList;

    public CurrencyRateModel() {
        super();
        currencyRateList = new MutableLiveData<>();
    }

    public LiveData<List<CurrencyRateVO>> getCurrencyRates() {
        return mAppDatabase.currencyRateDao().getAllCurrencyRates();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<CurrencyRateVO>> startLoadingCurrencyRate(Context context) {
        return loadCurrencyRate(context,configUtils.loadPageIndex());
    }

    public LiveData<List<CurrencyRateVO>> loadCurrencyRate(Context context, int pageNo) {
        if (NetworkUtils.getInstance().checkConnection(context)) {
            Observable<GetCurrencyRateResponse> currencyRateResponseObservable = retrofitDataAgent.getCurrencyExchangeApi().loadCurrencyRate();
            currencyRateResponseObservable
                    .subscribeOn(Schedulers.io()) //run value creation code on a specific thread (non-UI thread)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GetCurrencyRateResponse>() {

                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onNext(@NonNull GetCurrencyRateResponse getCurrencyRateResponse) {
                            Map<String, String> currencyRateMap = getCurrencyRateResponse.getCurrencyRateList();

                            List<String> countryCodeList = new ArrayList<String>(currencyRateMap.keySet());

                            List<CurrencyRateVO> currencyRateVOList = new ArrayList<>();


                            for (int index = 0; index < countryCodeList.size(); index++) {
                                CurrencyRateVO currencyRate = new CurrencyRateVO();
                                currencyRate.setTime(getCurrencyRateResponse.getTimestamp());
                                currencyRate.setCountryCode(countryCodeList.get(index));
                                currencyRate.setCurrencyAmount(currencyRateMap.get(countryCodeList.get(index)));
                                currencyRateVOList.add(currencyRate);
                            }
                            currencyRateList.setValue(currencyRateVOList);
//                        configUtils.savePageIndex(1);

                            Log.d(CurrencyExchangeApp.TAG, "currencyRateList size onNext : " + currencyRateList.getValue().size());

                        long[] insertedIds = mAppDatabase.currencyRateDao().insertCurrencyRates(currencyRateVOList.toArray(new CurrencyRateVO[0]));
                        Log.d(CurrencyExchangeApp.TAG, "insertedIds currency rates : " + insertedIds.length);
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            RestApiEvents.ErrorInvokingAPIEvent errorInvokingAPIEvent = new RestApiEvents.ErrorInvokingAPIEvent("No Internet Connection");
            EventBus.getDefault().post(errorInvokingAPIEvent);

        }

        return currencyRateList;

    }

    public LiveData<List<CurrencyRateVO>> loadMoreCurrencyRate(Context context) {
        return loadCurrencyRate(context,configUtils.loadPageIndex());
    }

    public void forceRefreshNews(Context context) {
        List<CurrencyRateVO> currencyRateVOList = new ArrayList<>();
        currencyRateList.setValue(currencyRateVOList);
        configUtils.savePageIndex(1);
        startLoadingCurrencyRate(context);
    }

}
