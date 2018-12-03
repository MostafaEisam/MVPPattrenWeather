package com.example.mostafaeisam.mvppattrenweather.presenter;

public interface OnWeatherLisener {
    void onSuccess(Object object);
    void  onFailure(int errorCode);
}
