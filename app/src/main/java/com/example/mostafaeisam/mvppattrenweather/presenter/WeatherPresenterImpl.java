package com.example.mostafaeisam.mvppattrenweather.presenter;

import android.view.View;

import com.example.mostafaeisam.mvppattrenweather.responses.GetWeatherResponseModel;
import com.example.mostafaeisam.mvppattrenweather.services.ServiceFactory;
import com.example.mostafaeisam.mvppattrenweather.view.MainActivity;
import com.example.mostafaeisam.mvppattrenweather.view.WeatherView;
import com.google.gson.Gson;

public class WeatherPresenterImpl implements OnWeatherLisener  {
    WeatherView weatherView;

    public WeatherPresenterImpl(WeatherView weatherView)
    {
        this.weatherView = weatherView;
    }


    public void getWeatherData(){
        weatherView.showprogressBar();
        ServiceFactory.getData(this,"http://api.apixu.com/v1/current.json?key=adbe3cafcb5743a4aa5104339181508&q=cairo",this);
    }

    @Override
    public void onSuccess(Object object) {
        //final GetWeatherResponseModel mWeatherResponse = new Gson().fromJson((String) object, GetWeatherResponseModel.class);
        weatherView.hideprogressBar();
        weatherView.showWeatherData(object);
    }

    @Override
    public void onFailure(int errorCode) {
        weatherView.onError();
    }

}
