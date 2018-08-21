package com.example.administrator.smallhappypay.activity.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.smallhappypay.App;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.adapter.ListviewAresAdapter;
import com.example.administrator.smallhappypay.constant.Constant;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.util.CityBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AreaActivity extends Activity {

    @BindView(R.id.arealist_lv_list)
    ListView arealistLvList;

    private int cityIdtwo = 0;
    private CityBean cityBean = null;
    private ListviewAresAdapter listviewAresAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_area);
        ButterKnife.bind(this);
        setData();

        arealistLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IN_City_ID, cityBean.getList().get(position).getCityId());
                intent.putExtra(Constant.IN_City_Name, cityBean.getList().get(position).getCityName());
                AreaActivity.this.setResult(Constant.AreaRESULT_OK, intent);
                AreaActivity.this.finish();
            }
        });
    }
    private void setData() {
        cityIdtwo = getIntent().getIntExtra(Constant.IN_ID, 0);
        getAreaBean();
    }
    private void initView() {
        listviewAresAdapter = new ListviewAresAdapter(this, cityBean.getList());
        arealistLvList.setAdapter(listviewAresAdapter);
    }
    private void getAreaBean() {
        Api.gethttpService().getAreaData(cityIdtwo+"").enqueue(new Callback<CityBean>() {
            @Override
            public void onResponse(Call<CityBean> call, Response<CityBean> response) {
                if ("00".equals(response.body().getCode())){
                    cityBean=response.body();
                    initView();
                }else {
                    App.getInstance().showToast(response.body().getFailMessage());
                }
            }
            @Override
            public void onFailure(Call<CityBean> call, Throwable t) {
                App.getInstance().showToast("服务器维护中");
            }
        });
    }
}
