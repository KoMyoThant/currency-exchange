package com.ps.currencyexchange.mvp.views;

import com.ps.currencyexchange.data.vos.CurrencyRateVO;

import java.util.List;

/**
 * Created by pyaesone on 11/25/18
 */
public interface CurrencyRateView {

    void displayCurrencyRateList(List<CurrencyRateVO> currencyRateList);

    void refreshCurrencyRateList();
}
