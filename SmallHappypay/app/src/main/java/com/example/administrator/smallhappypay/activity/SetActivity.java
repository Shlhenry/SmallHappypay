package com.example.administrator.smallhappypay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.MainActivity;
import com.example.administrator.smallhappypay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {


    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.set_exit_tv)
    TextView setExitTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("设置");
    }

    @OnClick({R.id.toolbar_back_ll, R.id.set_exit_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_ll:
                startActivity(new Intent(SetActivity.this, MainActivity.class));
                finish();
                break;
            case R.id.set_exit_tv:
                startActivity(new Intent(SetActivity.this, LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
                break;
        }
    }
}
