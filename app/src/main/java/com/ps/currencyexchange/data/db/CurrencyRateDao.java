package com.ps.currencyexchange.data.db;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.ps.currencyexchange.data.vos.CurrencyRateVO;

import java.util.List;

/**
 * Created by pyaesone on 1/26/18.
 */

@Dao
public interface CurrencyRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertCurrencyRate(CurrencyRateVO currencyRate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertCurrencyRates(CurrencyRateVO... currencyRates);

    @Query("SELECT * FROM currency_rate")
    LiveData<List<CurrencyRateVO>> getAllCurrencyRates();

    @Query("DELETE FROM currency_rate")
    void deleteAll();
}
