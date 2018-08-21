package com.example.administrator.smallhappypay.activity;

import android.media.session.PlaybackState;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.adapter.MccRateDAdapter;
import com.example.administrator.smallhappypay.adapter.MccRateTAdapter;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.MccRateBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MccRateActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.mccrate_amnumber_tv)
    TextView mccrateAmnumberTv;
    @BindView(R.id.mccrate_d_lv)
    ListView mccrateDLv;
    @BindView(R.id.mccrate_t_lv)
    ListView mccrateTLv;

    private MccRateTAdapter mccRateTAdapter;
    private MccRateDAdapter mccRateDAdapter;
    private MccRateBean.MapBean mapBean;
//    private MccRateBean.MapBean.DBean dBean;

    private String amNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mcc_rate);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("进件费率");
        amNumber= SPUtils.getString(getApplication(),"amNumber");
        mccrateAmnumberTv.setText("商户号:"+amNumber);
        LoadingDialog(MccRateActivity.this,"请稍后");
        getMcc(amNumber);
    }

    @OnClick(R.id.toolbar_back_ll)
    public void onViewClicked() {
        finish();
    }

    private void TinitView() {
        mccRateTAdapter = new MccRateTAdapter(this,mapBean.getT());
        mccrateTLv.setAdapter(mccRateTAdapter);
        mccRateTAdapter.notifyDataSetChanged();
    }
    private void DinitView() {
        mccRateDAdapter = new MccRateDAdapter(this,mapBean.getD());
        mccrateDLv.setAdapter(mccRateDAdapter);
        mccRateDAdapter.notifyDataSetChanged();
    }


    private void getMcc(String am_number){
        Api.gethttpService().getMccData(am_number).enqueue(new Callback<MccRateBean>() {
            @Override
            public void onResponse(Call<MccRateBean> call, Response<MccRateBean> response) {
                dismiss();

                if ("00".equals(response.body().getCode())){
                    mapBean=response.body().getMap();
                    if (response.body().getMap()!=null){
                    if (response.body().getMap().getT()!=null){
                        TinitView();
                        Log.w("tttt","tttt");
                    }
                    if (response.body().getMap().getD()!=null){
                        DinitView();
                        Log.w("dddd","dddd");
                    }
                    }
                }
            }
            @Override
            public void onFailure(Call<MccRateBean> call, Throwable t) {
                dismiss();
                mdialog(MccRateActivity.this,"服务器维护中");
            }
        });
    }

}
