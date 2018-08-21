package com.example.administrator.smallhappypay.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.BankInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankInfoActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.bankinfo_bankname_tv)
    TextView bankinfoBanknameTv;
    @BindView(R.id.bankinfo_banknumber_tv)
    TextView bankinfoBanknumberTv;
    @BindView(R.id.bankinfo_accountname_tv)
    TextView bankinfoAccountnameTv;

    private String amNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_info);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("收款账户");

        amNumber= SPUtils.getString(getApplication(),"amNumber");
        LoadingDialog(BankInfoActivity.this,"请稍后");
        Bankinfo(amNumber);
    }


    @OnClick(R.id.toolbar_back_ll)
    public void onViewClicked() {
        finish();
    }

    private void Bankinfo(String amNumber){
        Api.gethttpService().getBankInfoData(amNumber).enqueue(new Callback<BankInfoBean>() {
            @Override
            public void onResponse(Call<BankInfoBean> call, Response<BankInfoBean> response) {
                dismiss();
                if ("00".equals(response.body().getCode())){
                    if (response.body().getMap()!=null){
                         bankinfoBanknameTv.setText(response.body().getMap().getBankName());
                         bankinfoBanknumberTv.setText(response.body().getMap().getAccount_number());
                         bankinfoAccountnameTv.setText(response.body().getMap().getAccount_name());
                    }
                }else {
                    mdialog(BankInfoActivity.this,response.body().getFailMessage());
                }
            }
            @Override
            public void onFailure(Call<BankInfoBean> call, Throwable t) {
                dismiss();
                mdialog(BankInfoActivity.this,"服务器维护中");
            }
        });
    }
}
