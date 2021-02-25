package com.madhram.testalarstudios.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class Datum implements Serializable {

    @SerializedName("country")
    @Getter
    @Setter
    private String country;
    @SerializedName("id")
    @Getter
    @Setter
    private String id;
    @SerializedName("lat")
    @Getter
    @Setter
    private double lat;
    @SerializedName("lon")
    @Getter
    @Setter
    private double lon;
    @SerializedName("name")
    @Getter
    @Setter
    private String name;

    @Override
    public String toString() {
        return "Datum{" +
                "country='" + country + '\'' +
                ", id='" + id + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", name='" + name + '\'' +
                '}';
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }
}