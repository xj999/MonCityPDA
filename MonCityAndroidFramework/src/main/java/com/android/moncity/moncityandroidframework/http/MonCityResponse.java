package com.android.moncity.moncityandroidframework.http;

import java.io.Serializable;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class MonCityResponse<T> implements Serializable {


    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "MonCityResponse{\n" +//
                "\tcode=" + code + "\n" +//
                "\tmsg='" + msg + "\'\n" +//
                "\tdata=" + data + "\n" +//
                '}';
    }
}
