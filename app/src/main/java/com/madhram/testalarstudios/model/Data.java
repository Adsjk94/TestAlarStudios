package com.madhram.testalarstudios.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class Data implements Serializable {

    @SerializedName("data")
    @Getter
    @Setter
    private List<Datum> data;
    @SerializedName("page")
    @Getter
    @Setter
    private long page;
    @SerializedName("status")
    @Getter
    @Setter
    private String status;

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                ", page=" + page +
                ", status='" + status + '\'' +
                '}';
    }
}