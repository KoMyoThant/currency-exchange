package com.ps.currencyexchange.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.TextView;

import com.ps.currencyexchange.R;
import com.ps.currencyexchange.adapters.CurrencyRateAdapter;
import com.ps.currencyexchange.components.EmptyViewPod;
import com.ps.currencyexchange.components.rvset.SmartRecyclerView;
import com.ps.currencyexchange.components.rvset.SmartScrollListener;
import com.ps.currencyexchange.data.model.CurrencyRateModel;
import com.ps.currencyexchange.data.vos.CurrencyRateVO;
import com.ps.currencyexchange.mvp.presenters.CurrencyRatePresenter;
import com.ps.currencyexchange.mvp.views.CurrencyRateView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CurrencyRateView {

    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.rv_currency_rate_list)
    SmartRecyclerView rvCurrencyRateList;

    @BindView(R.id.vp_currency_rate_list)
    EmptyViewPod vpCurrencyRateList;

    private CurrencyRateAdapter currencyRateAdapter;

    private SmartScrollListener mSmartScrollListener;

    private CurrencyRateModel currencyRateModel;

    private CurrencyRatePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);

//        CurrencyRateModel currencyRateModel = new CurrencyRateModel();
//        currencyRateModel.loadCurrencyRate(2);

        currencyRateModel = ViewModelProviders.of(this).get(CurrencyRateModel.class);
        currencyRateModel.initDatabase(getBaseContext());

        mPresenter = new CurrencyRatePresenter(this, currencyRateModel);
        mPresenter.onCreate(this);

        vpCurrencyRateList.setEmptyData("Loading...");
        rvCurrencyRateList.setEmptyView(vpCurrencyRateList);
        rvCurrencyRateList.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        currencyRateAdapter = new CurrencyRateAdapter(getApplicationContext(), mPresenter);
        rvCurrencyRateList.setAdapter(currencyRateAdapter);


        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.ControllerSmartScroll() {
            @Override
            public void onListEndReached() {
                Snackbar.make(rvCurrencyRateList, "Loading data...", Snackbar.LENGTH_LONG).show();
                mPresenter.onLoadMoreCurrencyRaet(getApplicationContext());
            }
        });
        rvCurrencyRateList.addOnScrollListener(mSmartScrollListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.onForceRefresh(getBaseContext());
            }
        });


    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @Override
    public void displayCurrencyRateList(List<CurrencyRateVO> currencyRateList) {
        currencyRateAdapter.setNewData(currencyRateList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void refreshCurrencyRateList() {
        swipeRefreshLayout.setRefreshing(true);
    }
}
