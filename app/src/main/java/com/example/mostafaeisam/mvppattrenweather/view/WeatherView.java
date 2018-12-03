package com.example.mostafaeisam.mvppattrenweather.view;

public interface WeatherView {
    void showprogressBar();
    void hideprogressBar();
    void onError();
    void showWeatherData(Object object);
}
