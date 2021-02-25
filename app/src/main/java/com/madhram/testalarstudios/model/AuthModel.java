package com.madhram.testalarstudios.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class AuthModel implements Serializable {

    @SerializedName("status")
    @Getter
    @Setter
    private String status;


    @SerializedName("code")
    @Getter
    @Setter
    private String code;

    @Override
    public String toString() {
        return "AuthModel{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
