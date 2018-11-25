package com.ps.currencyexchange.network.responses;

import com.google.gson.annotations.SerializedName;
import com.ps.currencyexchange.data.vos.CurrencyRateVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pyaesone on 11/25/18
 */
public class GetCurrencyRateResponse {

    @SerializedName("info")
    private String info;

    @SerializedName("description")
    private String description;

    @SerializedName("timestamp")

    private String timestamp;

    @SerializedName("rates")
    private Map<String, String> currencyRateList;


    public String getInfo() {
        return info;
    }

    public String getDescription() {
        return description;
    }

    public String getTimestamp() {
        return timestamp;
    }

//    public List<CurrencyRateVO> getCurrencyRateList() {
//        if (currencyRateList == null) {
//            currencyRateList = new ArrayList<>();
//        }
//
//        return currencyRateList;    }

    public Map<String, String> getCurrencyRateList() {
        return currencyRateList;
    }
}
