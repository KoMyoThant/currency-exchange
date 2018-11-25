package com.ps.currencyexchange.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ps.currencyexchange.R;
import com.ps.currencyexchange.data.vos.CurrencyRateVO;
import com.ps.currencyexchange.delegates.CurrencyRateItemDelegate;
import com.ps.currencyexchange.viewholders.CurrencyRateViewHolder;

import butterknife.BindView;

/**
 * Created by pyaesone on 11/25/18
 */
public class CurrencyRateAdapter extends BaseRecyclerAdapter<CurrencyRateViewHolder, CurrencyRateVO> {

    private CurrencyRateItemDelegate mCurrencyRateItemDelegate;

    public CurrencyRateAdapter(Context context, CurrencyRateItemDelegate currencyRateItemDelegate) {
        super(context);
        mCurrencyRateItemDelegate = currencyRateItemDelegate;
    }

    @NonNull
    @Override
    public CurrencyRateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_currency_rate, parent, false);

        return new CurrencyRateViewHolder(view, mCurrencyRateItemDelegate);
    }

//    @Override
//    public void onBindViewHolder(CurrencyRateViewHolder holder, int position) {
//
//    }

//    @Override
//    public int getItemCount() {
//        return  20;
//    }
}
