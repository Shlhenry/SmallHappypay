package com.example.administrator.smallhappypay.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.adapter.MachineAllInfoOneAdapter;
import com.example.administrator.smallhappypay.adapter.MachineAllInfoThreeAdapter;
import com.example.administrator.smallhappypay.adapter.MachineAllInfoTwoAdapter;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.MachineInfoBean;
import com.example.administrator.smallhappypay.util.NoBackBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MachineAllInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.machineAllInfo_amNumber_tv)
    TextView machineAllInfoAmNumberTv;
    @BindView(R.id.machineAllInfo_onejiantou_img)
    ImageView machineAllInfoOnejiantouImg;
    @BindView(R.id.machineAllInfo_onenumber_tv)
    TextView machineAllInfoOnenumberTv;
    @BindView(R.id.machineAllInfo_One_ll)
    LinearLayout machineAllInfoOneLl;
    @BindView(R.id.machineAllInfo_One_lv)
    ListView machineAllInfoOneLv;
    @BindView(R.id.machineAllInfo_twojiantou_img)
    ImageView machineAllInfoTwojiantouImg;
    @BindView(R.id.machineAllInfo_twonumber_tv)
    TextView machineAllInfoTwonumberTv;
    @BindView(R.id.machineAllInfo_Two_ll)
    LinearLayout machineAllInfoTwoLl;
    @BindView(R.id.machineAllInfo_Two_lv)
    ListView machineAllInfoTwoLv;
    @BindView(R.id.machineAllInfo_threejiantou_img)
    ImageView machineAllInfoThreejiantouImg;
    @BindView(R.id.machineAllInfo_threenumber_tv)
    TextView machineAllInfoThreenumberTv;
    @BindView(R.id.machineAllInfo_Three_ll)
    LinearLayout machineAllInfoThreeLl;
    @BindView(R.id.machineAllInfo_Three_lv)
    ListView machineAllInfoThreeLv;

    private MachineInfoBean machineInfoBean;
    private MachineAllInfoOneAdapter machineAllInfoOneAdapter;
    private MachineAllInfoTwoAdapter machineAllInfoTwoAdapter;
    private MachineAllInfoThreeAdapter machineAllInfoThreeAdapter;

    private String amNumber;

    private String one="0";
    private String two="0";
    private String three="0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_all_info);
        ButterKnife.bind(this);
        amNumber= SPUtils.getString(getApplication(),"amNumber");
        machineAllInfoAmNumberTv.setText("商户号:"+amNumber);
        toolbarTitleTv.setText("终端信息");

        LoadingDialog(MachineAllInfoActivity.this,"请稍后");
        getMachineInfoData(amNumber);

    }


    @OnClick({R.id.toolbar_back_ll, R.id.machineAllInfo_One_ll, R.id.machineAllInfo_Two_ll, R.id.machineAllInfo_Three_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_ll:
                finish();
                break;
            case R.id.machineAllInfo_One_ll:
                if ("0".equals(one)){
                    machineAllInfoOneLv.setVisibility(View.VISIBLE);
                    machineAllInfoOnejiantouImg.setBackgroundResource(R.drawable.jingyibu);
                    one="1";
                }else if ("1".equals(one)){
                    machineAllInfoOneLv.setVisibility(View.GONE);
                    one="0";
                    machineAllInfoOnejiantouImg.setBackgroundResource(R.drawable.jingyibuu);
                }
                break;
            case R.id.machineAllInfo_Two_ll:

                if ("0".equals(two)){
                    machineAllInfoTwoLv.setVisibility(View.VISIBLE);
                    machineAllInfoTwojiantouImg.setBackgroundResource(R.drawable.jingyibu);
                    two="1";
                }else if ("1".equals(two)){
                    machineAllInfoTwoLv.setVisibility(View.GONE);
                    two="0";
                    machineAllInfoTwojiantouImg.setBackgroundResource(R.drawable.jingyibuu);
                }
                break;
            case R.id.machineAllInfo_Three_ll:

                if ("0".equals(three)){
                    machineAllInfoThreeLv.setVisibility(View.VISIBLE);
                    machineAllInfoThreejiantouImg.setBackgroundResource(R.drawable.jingyibu);
                    three="1";
                }else if ("1".equals(three)){
                    machineAllInfoThreeLv.setVisibility(View.GONE);
                    three="0";
                    machineAllInfoThreejiantouImg.setBackgroundResource(R.drawable.jingyibuu);
                }
                break;
        }
    }

    private void getMachineInfoData(String amNumber){
        Api.gethttpService().getMachineInfoData(amNumber).enqueue(new Callback<MachineInfoBean>() {
            @Override
            public void onResponse(Call<MachineInfoBean> call, Response<MachineInfoBean> response) {
                machineInfoBean=response.body();
                dismiss();
                if ("00".equals(response.body().getCode())){
                    if (response.body().getMap()!=null){
                        if(response.body().getMap().getOne()!=null){
                            OneInitview();
                            machineAllInfoOnenumberTv.setText(response.body().getMap().getOne().size()+"");
                        }
                        if(response.body().getMap().getTwo()!=null){
                            TwoInitview();
                            machineAllInfoTwonumberTv.setText(response.body().getMap().getTwo().size()+"");
                        }
                        if(response.body().getMap().getThree()!=null){
                            ThreeInitview();
                            machineAllInfoThreenumberTv.setText(response.body().getMap().getThree().size()+"");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MachineInfoBean> call, Throwable t) {
                dismiss();
                mdialog(MachineAllInfoActivity.this,"服务器维护中");
            }
        });
    }

    private void OneInitview(){
        machineAllInfoOneAdapter = new MachineAllInfoOneAdapter(MachineAllInfoActivity.this, machineInfoBean.getMap().getOne());
        machineAllInfoOneLv.setAdapter(machineAllInfoOneAdapter);
        machineAllInfoOneAdapter.notifyDataSetChanged();
    }

    private void TwoInitview(){
        machineAllInfoTwoAdapter = new MachineAllInfoTwoAdapter(MachineAllInfoActivity.this, machineInfoBean.getMap().getTwo());
        machineAllInfoTwoLv.setAdapter(machineAllInfoTwoAdapter);
        machineAllInfoTwoAdapter.notifyDataSetChanged();
    }

    private void ThreeInitview(){
        machineAllInfoThreeAdapter = new MachineAllInfoThreeAdapter(MachineAllInfoActivity.this, machineInfoBean.getMap().getThree());
        machineAllInfoThreeLv.setAdapter(machineAllInfoThreeAdapter);
        machineAllInfoThreeAdapter.notifyDataSetChanged();
    }

}
