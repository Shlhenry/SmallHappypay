package com.example.administrator.smallhappypay.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuestionActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.question_img)
    ImageView questionImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("常见问题");
    }

    @OnClick(R.id.toolbar_back_ll)
    public void onViewClicked() {
        finish();
    }
}
