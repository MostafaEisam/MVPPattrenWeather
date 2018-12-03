package com.example.mostafaeisam.mvppattrenweather.responses;


import com.example.mostafaeisam.mvppattrenweather.classes.Current;
import com.example.mostafaeisam.mvppattrenweather.classes.Location;
import com.google.gson.annotations.SerializedName;

public class GetWeatherResponseModel {
    @SerializedName("location")
    private Location location;
    @SerializedName("current")
    private Current current;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }


}
