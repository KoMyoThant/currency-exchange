package com.ps.currencyexchange.mvp.presenters;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.support.annotation.Nullable;

import com.ps.currencyexchange.data.model.CurrencyRateModel;
import com.ps.currencyexchange.data.vos.CurrencyRateVO;
import com.ps.currencyexchange.delegates.CurrencyRateItemDelegate;
import com.ps.currencyexchange.mvp.views.CurrencyRateView;

import java.util.List;

/**
 * Created by pyaesone on 11/25/18
 */
public class CurrencyRatePresenter extends BasePresenter<CurrencyRateView> implements CurrencyRateItemDelegate {

    private CurrencyRateModel mCurrencyRateModel;
    private LifecycleOwner mLifecycleOwner;

    public CurrencyRatePresenter(LifecycleOwner lifecycleOwner, CurrencyRateModel currencyRateModel) {
        mLifecycleOwner = lifecycleOwner;
        mCurrencyRateModel = currencyRateModel;
    }

    @Override
    public void onCreate(CurrencyRateView View) {
        super.onCreate(View);
    }

    @Override
    public void onStart() {
        mCurrencyRateModel.getCurrencyRates().observe(mLifecycleOwner, new Observer<List<CurrencyRateVO>>() {
            @Override
            public void onChanged(@Nullable final List<CurrencyRateVO> currencyRateList) {
                if (!currencyRateList.isEmpty()) {
                    mView.displayCurrencyRateList(currencyRateList);
                } else {
                    mView.refreshCurrencyRateList();
                    mCurrencyRateModel.startLoadingCurrencyRate().observe(mLifecycleOwner, new Observer<List<CurrencyRateVO>>() {
                        @Override
                        public void onChanged(@Nullable List<CurrencyRateVO> currencyRateVOS) {
                            mView.displayCurrencyRateList(currencyRateVOS);
                        }
                    });
                }
            }
        });
    }

    public void onLoadMoreCurrencyRaet(Context context) {
        mCurrencyRateModel.loadMoreCurrencyRate();
    }

    @Override
    public void onStop() {

    }

    public void onForceRefresh(Context context) {
        mCurrencyRateModel.forceRefreshNews(context);
    }

}
