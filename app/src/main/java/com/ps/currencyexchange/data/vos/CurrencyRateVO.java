package com.ps.currencyexchange.data.vos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import io.reactivex.annotations.NonNull;

/**
 * Created by pyaesone on 11/25/18
 */
@Entity(tableName = "currency_rate",indices={@Index(value="country_code", unique=true)})
public class CurrencyRateVO {

    @PrimaryKey(autoGenerate = true)
    private long _id;

    @ColumnInfo(name = "country_code")
    @NonNull
    private String countryCode;

    @ColumnInfo(name = "currency_amount")
    private String currencyAmount;

    @ColumnInfo(name = "currency_time")
    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public void setCurrencyAmount(String currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public long get_id() {
        return _id;
    }

    public String getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }
}
