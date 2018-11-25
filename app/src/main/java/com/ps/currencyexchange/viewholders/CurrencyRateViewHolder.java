package com.ps.currencyexchange.viewholders;

import android.view.View;
import android.widget.TextView;

import com.ps.currencyexchange.R;
import com.ps.currencyexchange.data.vos.CurrencyRateVO;
import com.ps.currencyexchange.delegates.CurrencyRateItemDelegate;

import butterknife.BindView;

/**
 * Created by pyaesone on 11/25/18
 */
public class CurrencyRateViewHolder extends BaseViewHolder<CurrencyRateVO> {

//    @BindView(R.id.tv_time)
//    TextView tvTime;

    @BindView(R.id.tv_country)
    TextView tvCountry;

    @BindView(R.id.tv_currency_amount)
    TextView tvCurrencyAmount;

    private CurrencyRateItemDelegate mCurrencyRateItemDelegate;

    public CurrencyRateViewHolder(View itemView, CurrencyRateItemDelegate currencyRateItemDelegate) {
        super(itemView);
        mCurrencyRateItemDelegate = currencyRateItemDelegate;
    }

    @Override
    public void setData(CurrencyRateVO data) {
        if (data != null) {
            mData = data;

//            tvTime.setText(mData.);
            tvCountry.setText(mData.getCountryCode());
            tvCurrencyAmount.setText(mData.getCurrencyAmount());
        }
    }

    @Override
    public void onClick(View v) {

    }
}
