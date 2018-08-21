package com.example.administrator.smallhappypay.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.smallhappypay.App;
import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.TimerButton;
import com.example.administrator.smallhappypay.util.NoBackBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.register_tel_et)
    EditText registerTelEt;
    @BindView(R.id.register_inputcode_et)
    EditText registerInputcodeEt;
    @BindView(R.id.register_getcode_btn)
    TimerButton registerGetcodeBtn;
    @BindView(R.id.register_pwd_et)
    EditText registerPwdEt;
    @BindView(R.id.register_pwdagain_et)
    EditText registerPwdagainEt;
    @BindView(R.id.register_recommend_et)
    EditText registerRecommendEt;
    @BindView(R.id.register_register_btn)
    Button registerRegisterBtn;
    @BindView(R.id.register_msg_tx)
    TextView registerMsgTx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("注册");

        //修改颜色
        SpannableStringBuilder style = new SpannableStringBuilder("注册即代表您同意网络使用协议和隐私条款");
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.homecolor)), 8, 14, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        style.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.homecolor)), 15, 19, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        registerMsgTx.setText(style);

    }

    @OnClick({R.id.toolbar_back_ll, R.id.register_getcode_btn, R.id.register_register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_ll:
                finish();
                break;
            case R.id.register_getcode_btn:
                if (TextUtils.isEmpty(registerTelEt.getText())) {
                    mdialog(RegisterActivity.this, "请输入手机号");
                } else {
                    LoadingDialog(RegisterActivity.this,"请稍后");
                    //获取验证码
                    String codetel = registerTelEt.getText().toString();
                    CodeRegisterRequster(codetel,"1");
                }
                break;
            case R.id.register_register_btn:
                String phone=registerTelEt.getText().toString();
                String pwd=registerPwdEt.getText().toString();
                String code=registerInputcodeEt.getText().toString();
                String recomPhone=registerRecommendEt.getText().toString();
                LoadingDialog(RegisterActivity.this,"请稍后");
                RegisterRequster(phone,pwd,code,recomPhone);
                break;
        }
    }

    //获取验证码请求
    private void CodeRegisterRequster(String phone,String operator){

        Api.gethttpService().getCodeData(phone,operator).enqueue(new Callback<NoBackBean>() {
            @Override
            public void onResponse(Call<NoBackBean> call, Response<NoBackBean> response) {
                dismiss();
                if ("00".equals(response.body().getCode())){
                    //成功之后开始倒计时
                    registerGetcodeBtn.setCallback(new TimerButton.Callback() {
                        @Override
                        public String getTickerText() {
                            return "已发送%ds";
                        }

                        @Override
                        public int getMaxTime() {
                            return 60;
                        }
                    });
                    registerGetcodeBtn.start();

                }else if ("99".equals(response.body().getCode())){
                    mdialog(RegisterActivity.this,response.body().getFailMessage());
                }
            }
            @Override
            public void onFailure(Call<NoBackBean> call, Throwable t) {
                dismiss();
                mdialog(RegisterActivity.this,"服务器维护中");
            }
        });
    }

    //注册请求
    private void RegisterRequster(String phone,String pwd,String code,String recomPhone){

        Api.gethttpService().getRegisterData(phone,pwd,code,recomPhone).enqueue(new Callback<NoBackBean>() {
            @Override
            public void onResponse(Call<NoBackBean> call, Response<NoBackBean> response) {
                if ("00".equals(response.body().getCode())){
                    dismiss();
                    startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                    App.getInstance().showToast(response.body().getSuccessMessage());
                    finish();
                }else if ("99".equals(response.body().getCode())){
                    dismiss();
                    mdialog(RegisterActivity.this,response.body().getFailMessage());
                }
            }
            @Override
            public void onFailure(Call<NoBackBean> call, Throwable t) {
                mdialog(RegisterActivity.this,"服务器维护中");
            }
        });
    }
}
