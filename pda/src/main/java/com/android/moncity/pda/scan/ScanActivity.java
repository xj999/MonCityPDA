package com.android.moncity.pda.scan;

import android.jb.barcode.BarcodeManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.moncity.moncityandroidframework.base.BaseActivity;
import com.android.moncity.moncityandroidframework.utils.MonCityLog;
import com.android.moncity.pda.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScanActivity extends BaseActivity implements BarcodeManager.Callback {
    @Bind(R.id.show)
    TextView show;
    BarcodeManager barcodeManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        ButterKnife.bind(this);
        barcodeManager = BarcodeManager.getInstance();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.open, R.id.close, R.id.single_scan, R.id.close_single_scan, R.id.continuous_scan, R.id.close_continuous_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.open:
                barcodeManager.Barcode_Open(ScanActivity.this, ScanActivity.this);
                break;
            case R.id.close:
                barcodeManager.Barcode_Close();
                break;
            case R.id.single_scan:
                barcodeManager.Barcode_Start();
                break;
            case R.id.close_single_scan:
                barcodeManager.Barcode_Stop();
                break;
            case R.id.continuous_scan:
                break;
            case R.id.close_continuous_scan:
                break;
        }
    }

    @Override
    public void Barcode_Read(byte[] bytes, String s, int i) {
        Log.e("bbbb", "Barcode_Read: " + s + "====" + bytes);
        MonCityLog.e("Barcode_Read" + s + "====" + bytes);
    }
}
