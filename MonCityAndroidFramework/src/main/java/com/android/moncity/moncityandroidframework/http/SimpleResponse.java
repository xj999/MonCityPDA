package com.android.moncity.moncityandroidframework.http;

import java.io.Serializable;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class SimpleResponse implements Serializable {


    public int code;
    public String msg;

    public MonCityResponse toResponse() {
        MonCityResponse lzyResponse = new MonCityResponse();
        lzyResponse.code = code;
        lzyResponse.msg = msg;
        return lzyResponse;
    }
}
