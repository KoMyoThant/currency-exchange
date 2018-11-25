package com.ps.currencyexchange.network;

import com.ps.currencyexchange.network.responses.GetCurrencyRateResponse;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;

/**
 * Created by pyaesone on 11/25/18
 */
public interface CurrencyExchangeApi {

    @GET("api/latest")
    Observable<GetCurrencyRateResponse> loadCurrencyRate();
}
