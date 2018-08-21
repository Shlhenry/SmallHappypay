package com.example.administrator.smallhappypay.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.activity.AuthActivity;
import com.example.administrator.smallhappypay.activity.FaceLoginNewActivity;
import com.example.administrator.smallhappypay.activity.QuestionActivity;
import com.example.administrator.smallhappypay.activity.SetActivity;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.IsEntryBean;
import com.example.administrator.smallhappypay.util.MineInfoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineFragment extends Fragment {

    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.mine_face_rl)
    RelativeLayout mineFaceRl;
    @BindView(R.id.mine_merchant_rl)
    RelativeLayout mineMerchantRl;
    @BindView(R.id.mine_changeInfo_rl)
    RelativeLayout mineChangeInfoRl;
    @BindView(R.id.mine_questions_rl)
    RelativeLayout mineQuestionsRl;
    @BindView(R.id.mine_set_rl)
    RelativeLayout mineSetRl;
    Unbinder unbinder;
    @BindView(R.id.mine_state_img)
    ImageView mineStateImg;
    @BindView(R.id.mine_name_tv)
    TextView mineNameTv;
    @BindView(R.id.mine_money_tv)
    TextView mineMoneyTv;
    @BindView(R.id.mine_tOne_tv)
    TextView mineTOneTv;
    @BindView(R.id.mine_dZero)
    TextView mineDZero;
    @BindView(R.id.mine_isface_tv)
    TextView mineIsfaceTv;
    @BindView(R.id.mine_isjju_tv)
    TextView mineIsjjuTv;
    @BindView(R.id.mine_auth_img)
    ImageView mineAuthImg;
    private View view;

    private String amNumber;

    private String phone;
    private String pwd;
    private String isface;
    private String isjj;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_mine, container, false);
        }
        unbinder = ButterKnife.bind(this, view);
        amNumber = SPUtils.getString(getActivity(), "amNumber");
        phone = SPUtils.getString(getActivity(), "phone");
        pwd = SPUtils.getString(getActivity(), "pwd");

        BaseActivity.LoadingDialog(getActivity(), "请稍后");
        mineInfo(amNumber);
        isentry(phone);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.mine_face_rl, R.id.mine_merchant_rl, R.id.mine_changeInfo_rl, R.id.mine_questions_rl, R.id.mine_set_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mine_face_rl:
//                startActivity(new Intent(getActivity(), RegActivity.class));
                if (isface.equals("true")) {
                    BaseActivity.mdialog(getActivity(), "您已认证,请勿重复认证");
                } else {
                    startActivity(new Intent(getActivity(), FaceLoginNewActivity.class));
                }
                break;
            case R.id.mine_merchant_rl:
                if (isjj.equals("true")) {
                    BaseActivity.mdialog(getActivity(), "您已进件,请勿重复进件");
                } else {
                    startActivity(new Intent(getActivity(), AuthActivity.class));
                }
                break;
            case R.id.mine_changeInfo_rl:

                break;
            case R.id.mine_questions_rl:
                startActivity(new Intent(getActivity(), QuestionActivity.class));
                break;
            case R.id.mine_set_rl:
                startActivity(new Intent(getActivity(), SetActivity.class));
                getActivity().finish();
                break;
        }
    }

    private void mineInfo(String amNumber) {
        Api.gethttpService().getSummaryData(amNumber).enqueue(new Callback<MineInfoBean>() {
            @Override
            public void onResponse(Call<MineInfoBean> call, Response<MineInfoBean> response) {
                BaseActivity.dismiss();
                if ("00".equals(response.body().getCode())) {
                    if (response.body().getMap() != null) {
                        //                    if (response.body().getMap().getState().equals("正常")){
//                        mineStateImg.setBackgroundResource();
//                    }else {
//                        mineStateImg.setBackgroundResource();
//                    }
                        mineNameTv.setText(response.body().getMap().getName());
                        mineMoneyTv.setText(response.body().getMap().getMoney());
                        mineTOneTv.setText(response.body().getMap().getT1());
                        mineDZero.setText(response.body().getMap().getT0());
                    }
                }
            }

            @Override
            public void onFailure(Call<MineInfoBean> call, Throwable t) {
                BaseActivity.dismiss();
                BaseActivity.mdialog(getActivity(), "服务器维护中");
            }
        });
    }

    private void isentry(String phone) {
        Api.gethttpService().getisentryData(phone).enqueue(new Callback<IsEntryBean>() {
            @Override
            public void onResponse(Call<IsEntryBean> call, Response<IsEntryBean> response) {
                if ("00".equals(response.body().getCode())) {
                    if (response.body().getMap() != null) {
                        isface = response.body().getMap().getIsface();
                        isjj = response.body().getMap().getIsjj();
                        if (isface.equals("true")) {
                            mineIsfaceTv.setText("已认证");
                            mineAuthImg.setBackgroundResource(R.drawable.wodeyrz);
                        } else {
                            mineIsfaceTv.setText("未认证");
                            mineAuthImg.setBackgroundResource(R.drawable.wodewrz);
                        }
                        if (isjj.equals("true")) {
                            mineIsjjuTv.setText("已进件");
                        } else {
                            mineIsjjuTv.setText("未进件");
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<IsEntryBean> call, Throwable t) {
                BaseActivity.mdialog(getActivity(), "服务器维护中");
            }
        });
    }

    @Override
    public void onResume() {
        isentry(phone);
        super.onResume();
    }
}
