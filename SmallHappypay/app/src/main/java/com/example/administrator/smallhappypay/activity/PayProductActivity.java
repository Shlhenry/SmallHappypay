package com.example.administrator.smallhappypay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.tool.SPUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayProductActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.payproduct_me_ll)
    LinearLayout payproductMeLl;
    @BindView(R.id.payproduct_ha_ll)
    LinearLayout payproductHaLl;
    @BindView(R.id.payproduct_jsft_ll)
    LinearLayout payproductJsftLl;
    @BindView(R.id.payproduct_gun_ll)
    LinearLayout payproductGunLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_product);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("支付产品");

    }

    @OnClick({R.id.toolbar_back_ll, R.id.payproduct_me_ll, R.id.payproduct_ha_ll, R.id.payproduct_jsft_ll, R.id.payproduct_gun_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_ll:
                finish();
                break;
            case R.id.payproduct_me_ll:
                SPUtils.put(getApplication(),"producttype","新大陆ME31");
                startActivity(new Intent(PayProductActivity.this,PayProductChildActivity.class));
                break;
            case R.id.payproduct_ha_ll:
                SPUtils.put(getApplication(),"producttype","锦弘霖H60-A");
                startActivity(new Intent(PayProductActivity.this,PayProductChildActivity.class));
                break;
            case R.id.payproduct_jsft_ll:
                SPUtils.put(getApplication(),"producttype","付腾K90");
                startActivity(new Intent(PayProductActivity.this,PayProductChildActivity.class));
                break;
            case R.id.payproduct_gun_ll:
                SPUtils.put(getApplication(),"producttype","波普SL-58");
                startActivity(new Intent(PayProductActivity.this,PayProductChildActivity.class));
                break;
        }
    }
}
