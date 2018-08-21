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
import com.example.administrator.smallhappypay.adapter.ListviewCityAdapter;
import com.example.administrator.smallhappypay.constant.Constant;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.CityBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CityActivity extends Activity {


    @BindView(R.id.citylist_lv_list)
    ListView citylistLvList;
    private int cityIdone = 0;
    private CityBean cityBean = null;

    private ListviewCityAdapter listviewCityAdapter = null;

    private String judgeprovince;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city_list);
        ButterKnife.bind(this);
        setData();
        judgeprovince= SPUtils.getString(getApplication(),"judgeprovince");
        citylistLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra(Constant.IN_City_ID, cityBean.getList().get(position).getCityId());
                intent.putExtra(Constant.IN_City_Name, cityBean.getList().get(position).getCityName());
                if ("1".equals(judgeprovince)){
                    CityActivity.this.setResult(Constant.CityRESULT_OK, intent);
                }else if ("2".equals(judgeprovince)){
                    CityActivity.this.setResult(Constant.NewCityRESULT_OK, intent);
                }
                CityActivity.this.finish();
            }
        });
    }

    private void setData() {
        cityIdone = getIntent().getIntExtra(Constant.IN_ID, 0);
        getCityBean(cityIdone+"");
    }

    private void initView() {
        listviewCityAdapter = new ListviewCityAdapter(CityActivity.this, cityBean.getList());
        citylistLvList.setAdapter(listviewCityAdapter);
    }

    private void getCityBean(String cityIdone) {
        Api.gethttpService().getCityData(cityIdone+"").enqueue(new Callback<CityBean>() {
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
