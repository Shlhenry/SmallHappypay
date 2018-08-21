package com.example.administrator.smallhappypay.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.tool.SPUtils;

import org.apache.log4j.lf5.util.Resource;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayProductChildActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.payproductchild_img)
    ImageView payproductchildImg;

    private String producttype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_product_child);
        ButterKnife.bind(this);
        producttype= SPUtils.getString(getApplication(),"producttype");
        toolbarTitleTv.setText(producttype);
        Log.w("+++++++",producttype+"");
        if (producttype.equals("新大陆ME31")){
            payproductchildImg.setImageResource(R.drawable.me);
        }else if (producttype.equals("锦弘霖H60-A")){
            payproductchildImg.setImageResource(R.drawable.ha);
        }else if (producttype.equals("付腾K90")){
            payproductchildImg.setImageResource(R.drawable.jsft);
        }else if (producttype.equals("波普SL-58")){
            payproductchildImg.setImageResource(R.drawable.gun);
        }
    }
    @OnClick(R.id.toolbar_back_ll)
    public void onViewClicked() {
        finish();
    }
}
