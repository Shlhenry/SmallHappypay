package com.example.administrator.smallhappypay.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.CommonAdapter;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.tool.ViewHolder;
import com.example.administrator.smallhappypay.util.MachineInfoBean;
import com.example.administrator.smallhappypay.util.NoBackBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UnbindThreeActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.unbind_machine_lv)
    ListView unbindMachineLv;

    private CommonAdapter<MachineInfoBean.MapBean.ThreeBean> commonAdapter;
    private MachineInfoBean machineInfoBean;

    private String amNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unbind_three);
        ButterKnife.bind(this);
        amNumber= SPUtils.getString(getApplication(),"amNumber");
        toolbarTitleTv.setText("移动POS终端解绑");

        LoadingDialog(UnbindThreeActivity.this,"请稍后");
        getmachineinfo(amNumber);
    }

    @OnClick({R.id.toolbar_back_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_ll:
                finish();
                break;
        }
    }
    private void init(){
        commonAdapter=new CommonAdapter<MachineInfoBean.MapBean.ThreeBean>(UnbindThreeActivity.this,machineInfoBean.getMap().getThree(),R.layout.item_unbing) {
            @Override
            public void convert(final ViewHolder mHolder, final MachineInfoBean.MapBean.ThreeBean item, final int position) {
                mHolder.setText(R.id.itemunbind_name_tv,item.getModename());
                mHolder.setText(R.id.itemunbind_snnumber_tv,item.getMac_sn());
                mHolder.setonClick(R.id.item_unbing_tv, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoadingDialog(UnbindThreeActivity.this,"请稍后");
                        unbing(item.getMac_sn());
                    }
                });
            }
        };
        unbindMachineLv.setAdapter(commonAdapter);
    }

    private void getmachineinfo(String amNumber){
        Api.gethttpService().getMachineInfoData(amNumber).enqueue(new Callback<MachineInfoBean>() {
            @Override
            public void onResponse(Call<MachineInfoBean> call, Response<MachineInfoBean> response) {
                dismiss();
                machineInfoBean=response.body();
                if ("00".equals(response.body().getCode())){
                    if (machineInfoBean.getMap().getThree()!=null){
                        init();
                    }else {
                        unbindMachineLv.setVisibility(View.GONE);
                    }
                }
            }
            @Override
            public void onFailure(Call<MachineInfoBean> call, Throwable t) {
                dismiss();
                mdialog(UnbindThreeActivity.this,"服务器维护中");
            }
        });
    }

    private void unbing(String sn){
        Api.gethttpService().getuntieData(sn).enqueue(new Callback<NoBackBean>() {
            @Override
            public void onResponse(Call<NoBackBean> call, Response<NoBackBean> response) {
                dismiss();
                if ("00".equals(response.body().getCode())){
                    getmachineinfo(amNumber);
                }
            }
            @Override
            public void onFailure(Call<NoBackBean> call, Throwable t) {
                dismiss();
                mdialog(UnbindThreeActivity.this,"服务器维护中");
            }
        });
    }}