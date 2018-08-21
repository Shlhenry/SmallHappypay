package com.example.administrator.smallhappypay.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.MainActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.face.DetectLoginActivity;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.LoginBean;
import com.qmuiteam.qmui.util.QMUIStatusBarHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_forgetpw_tv)
    TextView loginForgetpwTv;
    @BindView(R.id.login_face_img)
    ImageView loginFaceImg;
    @BindView(R.id.login_phone_tv)
    EditText loginPhoneTv;
    @BindView(R.id.login_pw_et)
    EditText loginPwEt;
    @BindView(R.id.login_login_imgbtn)
    ImageButton loginLoginImgbtn;
    @BindView(R.id.login_register_imgbtn)
    ImageButton loginRegisterImgbtn;

    private String phone;
    private String pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        QMUIStatusBarHelper.translucent(this); // 沉浸式状态栏
        loginForgetpwTv.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        loginForgetpwTv.getPaint().setAntiAlias(true);//抗锯齿

        loginPhoneTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ChangeLoginbg();
            }
        });

        loginPwEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ChangeLoginbg();
            }
        });

        String savephone = SPUtils.getString(LoginActivity.this, "phone");
        String savepwd = SPUtils.getString(LoginActivity.this, "pwd");
        loginPhoneTv.setText(savephone);
        loginPwEt.setText(savepwd);
    }

    @OnClick({R.id.login_face_img, R.id.login_login_imgbtn, R.id.login_register_imgbtn,R.id.login_forgetpw_tv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_face_img:
                startActivity(new Intent(LoginActivity.this, DetectLoginActivity.class));
                break;
            case R.id.login_forgetpw_tv:
                startActivity(new Intent(LoginActivity.this, ForgetActivity.class));
                break;
            case R.id.login_login_imgbtn:
                if (loginPhoneTv.length()>0&&loginPwEt.length()>0){
                    phone=loginPhoneTv.getText().toString();
                    pwd=loginPwEt.getText().toString();
                    LoadingDialog(LoginActivity.this,"请稍后");
                    SPUtils.put(LoginActivity.this, "phone", phone);
                    SPUtils.put(LoginActivity.this, "pwd", pwd);
                    SPUtils.put(getApplication(),"amNumber","");
                    LoginRequest(phone,pwd);
                }else {
                    mdialog(LoginActivity.this,"账号/密码未填写");
                }
                break;
            case R.id.login_register_imgbtn:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
    private void LoginRequest(String acountNo,String pwd){
        Api.gethttpService().getLoginData(acountNo, pwd).enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                dismiss();
                if ("00".equals(response.body().getCode())){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    SPUtils.put(getApplication(),"amNumber",response.body().getMap().getAmNumber());
                    finish();
                }else if ("99".equals(response.body().getCode())){
                    mdialog(LoginActivity.this,response.body().getFailMessage());
                }
            }
            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {
                dismiss();
                mdialog(LoginActivity.this,"服务器维护中");
            }
        });
    }

    private void ChangeLoginbg(){
        if (loginPhoneTv.length()>0&&loginPwEt.length()>0){
            loginLoginImgbtn.setBackgroundResource(R.drawable.dlbutton);
        }else{
            loginLoginImgbtn.setBackgroundResource(R.drawable.dlbuttonwdj);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        String savephone = SPUtils.getString(LoginActivity.this, "phone");
        String savepwd = SPUtils.getString(LoginActivity.this, "pwd");
        loginPhoneTv.setText(savephone);
        loginPwEt.setText(savepwd);
    }
}
