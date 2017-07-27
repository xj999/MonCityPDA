package com.android.moncity.pda.dbview;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.moncity.moncityandroidframework.base.BaseActivity;
import com.android.moncity.pda.R;

import org.litepal.crud.DataSupport;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DbActivity extends BaseActivity {

    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.code)
    EditText code;
    @Bind(R.id.show)
    TextView show;
    @Bind(R.id.save_data)
    Button saveData;
    @Bind(R.id.query_data)
    Button queryData;
    @Bind(R.id.delete_data)
    Button deleteData;
    String nameStr;
    String codeStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db);
        ButterKnife.bind(this);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.save_data, R.id.query_data, R.id.delete_data, R.id.query_all_data, R.id.delete_all_data})
    public void onViewClicked(View view) {
        nameStr = name.getText().toString();
        codeStr = code.getText().toString();
        switch (view.getId()) {
            case R.id.save_data:   //保存数据
                if (!nameStr.isEmpty() && !codeStr.isEmpty()) {
                    boolean bb = new OrderBean(nameStr, codeStr).saveOrUpdate("pad_name=?", nameStr);
                    if (bb) {
                        showToast("保存成功");
                    }
                } else {
                    showToast("请输入完整数据");
                }
                break;
            case R.id.query_data://查询指定数据
                show.setText("");
                List<OrderBean> list = DataSupport.where("pad_name=?", nameStr).find(OrderBean.class);
                for (OrderBean b : list) {
                    show.setText(show.getText() + b.toString());
                }
                break;
            case R.id.query_all_data://查询全部数据
                show.setText("");
                List<OrderBean> listb = DataSupport.findAll(OrderBean.class);
                for (OrderBean b : listb) {
                    show.setText(show.getText() + b.toString());
                }
                break;
            case R.id.delete_data://删除指定数据
                if (DataSupport.deleteAll(OrderBean.class, "pad_name=?", nameStr) > 0)
                    showToast("删除数据成功");
                break;
            case R.id.delete_all_data://删除全部数据
                if (DataSupport.deleteAll(OrderBean.class) > 0)
                    showToast("删除数据成功");
                break;
        }
    }

}
