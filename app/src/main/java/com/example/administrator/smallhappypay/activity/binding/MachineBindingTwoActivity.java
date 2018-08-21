package com.example.administrator.smallhappypay.activity.binding;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.smallhappypay.App;
import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.adapter.QueryModleAdapter;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.NoBackBean;
import com.example.administrator.smallhappypay.util.QueryModelBean;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MachineBindingTwoActivity extends BaseActivity {

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.machine_amNumber_tv)
    TextView machineAmNumberTv;
    @BindView(R.id.machine_sn_et)
    EditText machineSnEt;
    @BindView(R.id.machine_select_tv)
    TextView machineSelectTv;
    @BindView(R.id.machine_select_rl)
    LinearLayout machineSelectRl;
    @BindView(R.id.machine_okbinding_imgbtn)
    ImageButton machineOkbindingImgbtn;

    private QMUIListPopup mListPopup;

    private QueryModelBean queryModelBean = null;
    private QueryModleAdapter queryModleAdapter;

    private View view1;
    private String amNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_machine_binding_two);
        ButterKnife.bind(this);

        toolbarTitleTv.setText("智能POS终端绑定");
        amNumber= SPUtils.getString(getApplication(),"amNumber");
        machineAmNumberTv.setText(amNumber);

        machineSnEt.addTextChangedListener(new TextWatcher() {
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

    @OnClick({R.id.toolbar_back_ll, R.id.machine_okbinding_imgbtn,R.id.machine_select_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar_back_ll:
                finish();
                break;
            case R.id.machine_okbinding_imgbtn:
                if (machineSnEt.length()<=0||"请选择型号".equals(machineSelectTv.getText().toString())){
                    mdialog(MachineBindingTwoActivity.this,"请先完善信息");
                }else {
                    LoadingDialog(MachineBindingTwoActivity.this,"请稍等");
                    Machine(amNumber,machineSnEt.getText().toString(),machineSelectTv.getText().toString());
                }
                break;
            case R.id.machine_select_rl:
                LoadingDialog(MachineBindingTwoActivity.this,"请稍等");
                QueryModel("2");
                view1 = view;
                break;
        }
    }

    private void initListPopupIfNeed() {
        if (mListPopup == null) {

            queryModleAdapter = new QueryModleAdapter(this, queryModelBean.getList());
            mListPopup = new QMUIListPopup(MachineBindingTwoActivity.this, QMUIPopup.DIRECTION_NONE, queryModleAdapter);
            mListPopup.create(QMUIDisplayHelper.dp2px(MachineBindingTwoActivity.this, 150), QMUIDisplayHelper.dp2px(MachineBindingTwoActivity.this, 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    machineSelectTv.setText(queryModelBean.getList().get(i).getName());
                    mListPopup.dismiss();
                    changebg();
                }
            });
            mListPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                }
            });
        }
    }

    private void QueryModel(String type) {
        Api.gethttpService().getqueryModelData(type).enqueue(new Callback<QueryModelBean>() {
            @Override
            public void onResponse(Call<QueryModelBean> call, Response<QueryModelBean> response) {
                dismiss();
                queryModelBean = response.body();
                if ("00".equals(queryModelBean.getCode())){
                    if (queryModelBean.getList()!=null){
                        initListPopupIfNeed();
                        mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                        mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_BOTTOM);
                        mListPopup.show(view1);
                    }else {
                        mdialog(MachineBindingTwoActivity.this,"暂无机器");
                    }
                }else {
                    mdialog(MachineBindingTwoActivity.this,response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<QueryModelBean> call, Throwable t) {
                dismiss();
                mdialog(MachineBindingTwoActivity.this,"服务器维护中");
            }
        });
    }

    private void changebg(){
        if (machineSnEt.length()<=0||"请选择型号".equals(machineSelectTv.getText().toString())){
            machineOkbindingImgbtn.setBackgroundResource(R.drawable.zdbdbuttonwdj);
        }else {
            machineOkbindingImgbtn.setBackgroundResource(R.drawable.zdbdbutton);
        }
    }

    private void Machine(String amNumber,String sn,String mmName){
        Api.gethttpService().getMachineData(amNumber, sn, mmName).enqueue(new Callback<NoBackBean>() {
            @Override
            public void onResponse(Call<NoBackBean> call, Response<NoBackBean> response) {
                dismiss();
                if ("00".equals(response.body().getCode())){
                    App.getInstance().showToast(response.body().getSuccessMessage());
                }else {
                    mdialog(MachineBindingTwoActivity.this,response.body().getFailMessage());
                }
            }
            @Override
            public void onFailure(Call<NoBackBean> call, Throwable t) {
                dismiss();
                mdialog(MachineBindingTwoActivity.this,"服务器维护中");
            }
        });
    }
}
