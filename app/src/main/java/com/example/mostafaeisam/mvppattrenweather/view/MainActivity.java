package com.example.mostafaeisam.mvppattrenweather.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mostafaeisam.mvppattrenweather.presenter.WeatherPresenterImpl;
import com.example.mostafaeisam.mvppattrenweather.R;
import com.example.mostafaeisam.mvppattrenweather.responses.GetWeatherResponseModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements WeatherView {
    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.bt_syncWeatherData)
    Button mbtSyncWeatherData;
    @BindView(R.id.rv_weatherView)
    RelativeLayout mRvWeatherView;
    @BindView(R.id.tv_location)
    TextView mTvLocation;
    @BindView(R.id.tv_lastUpdate)
    TextView mTvLastUpdate;
    @BindView(R.id.tv_weatherStatus)
    TextView mTvWeatherStatus;
    @BindView(R.id.tv_humidity)
    TextView mTvHumidity;
    @BindView(R.id.tv_temp)
    TextView mTvTemp;
    @BindView(R.id.iv_clouds)
    ImageView mIvClouds;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private SimpleDateFormat simpleDateFormat;
    private Date date;

    private WeatherPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new WeatherPresenterImpl(this);

        mRvWeatherView.setVisibility(View.GONE);
        //Banner Ads


        mbtSyncWeatherData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getWeatherData();
            }
        });

    }

    @Override
    public void showprogressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideprogressBar() {
        mbtSyncWeatherData.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        mRvWeatherView.setVisibility(View.VISIBLE);

    }

    @Override
    public void onError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showWeatherData(final Object object) {
        final GetWeatherResponseModel mWeatherResponse = new Gson().fromJson((String) object, GetWeatherResponseModel.class);

        MobileAds.initialize(this,
                "ca-app-pub-3940256099942544~3347511713");

        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mTvHumidity.setText("Humidity : " + mWeatherResponse.getCurrent().getHumidity() + "%");
        mTvWeatherStatus.setText(mWeatherResponse.getCurrent().getCondition().getText());
        mTvTemp.setText(String.valueOf(mWeatherResponse.getCurrent().getTemp_c()) + " °C");
        mTvLocation.setText(mWeatherResponse.getLocation().getName());

        Long localtime_epoch = Long.valueOf(mWeatherResponse.getCurrent().getLast_updated_epoch());
        simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy  hh:mm:ss a");
        date = new Date(localtime_epoch * 1000);
        String Date = simpleDateFormat.format(date);
        mTvLastUpdate.setText("Last Update : " + Date);
        Picasso.get()
                .load("http:" + mWeatherResponse.getCurrent().getCondition().getIcon())
                .fit()
                .into(mIvClouds);
        hideprogressBar();

    }
}
