package com.ps.currencyexchange.mvp.presenters;

/**
 * Created by pyaesone on 1/31/18.
 */

public abstract class BasePresenter<T> {

    protected T mView;

    public void onCreate(T View) {
        mView = View;
    }

    public abstract void onStart();

    public void onResume(){}

    public void onPause(){}

    public abstract void onStop();

    public void onDestroy(){}
}
