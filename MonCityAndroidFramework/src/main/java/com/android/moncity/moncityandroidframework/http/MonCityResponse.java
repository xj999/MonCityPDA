package com.android.moncity.moncityandroidframework.http;

import java.io.Serializable;

/**
 * @author Luxj
 * @date create 2017/7/24
 * @description
 */
public class MonCityResponse<T> implements Serializable {


    public int status;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "MonCityResponse{\n" +//
                "\tstatus=" + status + "\n" +//
                "\tmsg='" + msg + "\'\n" +//
                "\tdata=" + data + "\n" +//
                '}';
    }
}
