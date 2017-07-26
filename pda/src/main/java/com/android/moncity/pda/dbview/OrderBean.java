package com.android.moncity.pda.dbview;

import org.litepal.crud.DataSupport;

/**
 * @author Luxj
 * @date create 2017/7/26
 * @description
 */
public class OrderBean extends DataSupport {

    private String pad_name;
    private String pad_code;

    public String getPad_name() {
        return pad_name;
    }

    public void setPad_name(String pad_name) {
        this.pad_name = pad_name;
    }

    public String getPad_code() {
        return pad_code;
    }

    public void setPad_code(String pad_code) {
        this.pad_code = pad_code;
    }

    public OrderBean(String name, String code) {
        this.pad_name = name;
        this.pad_code = code;
    }

    @Override
    public String toString() {
        return "name=" + pad_name + "   code=" + pad_code + "\n";
    }
}
