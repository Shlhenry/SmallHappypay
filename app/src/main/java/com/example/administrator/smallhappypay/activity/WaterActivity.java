package com.example.administrator.smallhappypay.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidkun.xtablayout.XTabLayout;
import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.activity.water.AlipayWaterFragment;
import com.example.administrator.smallhappypay.activity.water.PosWaterFragment;
import com.example.administrator.smallhappypay.activity.water.WechatWaterFragment;
import com.example.administrator.smallhappypay.adapter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WaterActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.xTablayout)
    XTabLayout xTablayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("交易流水");
        initViewPager();

    }

    private void initViewPager() {
        List<String> titles = new ArrayList<>();
        titles.add("POS机流水");
        titles.add("微信流水");
        titles.add("支付宝流水");
        for (int i = 0; i < titles.size(); i++) {
            if (i == 0) {
                fragments.add(new PosWaterFragment());
            } else if (i == 1) {
                fragments.add(new WechatWaterFragment());
            } else if (i == 2) {
                fragments.add(new AlipayWaterFragment());
            }

        }
        FragmentAdapter adatper = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(adatper);
        viewPager.setOffscreenPageLimit(4);
        //将TabLayout和ViewPager关联起来。
        XTabLayout tabLayout = (XTabLayout) findViewById(R.id.xTablayout);
        tabLayout.setupWithViewPager(viewPager);
        //给TabLayout设置适配器
        tabLayout.setupWithViewPager(viewPager);
    }

    @OnClick(R.id.toolbar_back_ll)
    public void onViewClicked() {
        finish();
    }
}
