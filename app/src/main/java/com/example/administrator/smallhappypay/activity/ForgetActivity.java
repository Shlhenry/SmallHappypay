package com.example.administrator.smallhappypay.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.smallhappypay.App;
import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.tool.TimerButton;
import com.example.administrator.smallhappypay.util.NoBackBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.forgetpwd_tel_et)
    EditText forgetpwdTelEt;
    @BindView(R.id.forgetpwd_code_et)
    EditText forgetpwdCodeEt;
    @BindView(R.id.register_getcode_btn)
    TimerButton registerGetcodeBtn;
    @BindView(R.id.forgetpwd_newpwd_et)
    EditText forgetpwdNewpwdEt;
    @BindView(R.id.forgetpwd_newpwdagain_et)
    EditText forgetpwdNewpwdagainEt;
    @BindView(R.id.forgetpwd_commit_btn)
    Button forgetpwdCommitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("忘记密码");

        forgetpwdTelEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changebg();
            }
        });
        forgetpwdCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changebg();
            }
        });
        forgetpwdNewpwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changebg();
            }
        });
        forgetpwdNewpwdagainEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                changebg();
            }
        });

    }

    @OnClick({R.id.toolbar_back_ll, R.id.register_getcode_btn, R.id.forgetpwd_commit_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_ll:
                finish();
                break;
            case R.id.register_getcode_btn:
                if (TextUtils.isEmpty(forgetpwdTelEt.getText())) {
                    mdialog(ForgetActivity.this, "请输入手机号");
                } else {
                    LoadingDialog(ForgetActivity.this,"请稍后");
                    //获取验证码
                    String codetel = forgetpwdTelEt.getText().toString();
                    CodeForgetRequster(codetel,"2");
                }
                break;
            case R.id.forgetpwd_commit_btn:
                if (forgetpwdTelEt.length()>0&forgetpwdCodeEt.length()>0&forgetpwdNewpwdEt.length()>0&forgetpwdNewpwdagainEt.length()>0){
                    String phone = forgetpwdTelEt.getText().toString();
                    String code = forgetpwdCodeEt.getText().toString();
                    String pwd = forgetpwdNewpwdEt.getText().toString();
                    String reNewPwd = forgetpwdNewpwdagainEt.getText().toString();
                    LoadingDialog(ForgetActivity.this,"请稍后");
                    ForgetPwd(phone, pwd, code, reNewPwd);
                }
                break;
        }
    }

    private void ForgetPwd(String phone,String pwd,String code,String reNewPwd){
        Api.gethttpService().getForgetPwdData(phone, pwd, code, reNewPwd).enqueue(new Callback<NoBackBean>() {
            @Override
            public void onResponse(Call<NoBackBean> call, Response<NoBackBean> response) {
                dismiss();
                if ("00".equals(response.body().getCode())){
                    finish();
                    SPUtils.put(getApplication(),"pwd","");
                    App.getInstance().showToast(response.body().getSuccessMessage());
                }else {
                    mdialog(ForgetActivity.this,response.body().getFailMessage());
                }
            }
            @Override
            public void onFailure(Call<NoBackBean> call, Throwable t) {
                dismiss();
                mdialog(ForgetActivity.this,"服务器维护中");
            }
        });
    }

    private void changebg(){
        if (forgetpwdTelEt.length()>0&forgetpwdCodeEt.length()>0&forgetpwdNewpwdEt.length()>0&forgetpwdNewpwdagainEt.length()>0){
            forgetpwdCommitBtn.setBackgroundResource(R.drawable.xgmmqrxg);
        }else {
            forgetpwdCommitBtn.setBackgroundResource(R.drawable.xgmmqrxgwdj);
        }
    }

    //获取验证码请求
    private void CodeForgetRequster(String phone,String operator){

        Api.gethttpService().getCodeData(phone,operator).enqueue(new Callback<NoBackBean>() {
            @Override
            public void onResponse(Call<NoBackBean> call, Response<NoBackBean> response) {
                dismiss();
                if ("00".equals(response.body().getCode())){
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
                    mdialog(ForgetActivity.this,response.body().getFailMessage());
                }
            }
            @Override
            public void onFailure(Call<NoBackBean> call, Throwable t) {
                dismiss();
                mdialog(ForgetActivity.this,"服务器维护中");
            }
        });
    }

}
