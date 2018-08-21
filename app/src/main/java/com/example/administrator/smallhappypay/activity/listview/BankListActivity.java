package com.example.administrator.smallhappypay.activity.listview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.administrator.smallhappypay.App;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.adapter.ListviewBankAdapter;
import com.example.administrator.smallhappypay.constant.Constant;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.util.BankBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankListActivity extends Activity {


    @BindView(R.id.bank_lv_list)
    ListView bankLvList;
    private BankBean bankBean = null;
    private ListviewBankAdapter listviewBankAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_bank_list);
        ButterKnife.bind(this);
        allbanks();

        bankLvList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent();
                intent.putExtra(Constant.IN_BANK_ID, bankBean.getList().get(position).getBankId());
                intent.putExtra(Constant.IN_BANK_NAME, bankBean.getList().get(position).getBankName());
                BankListActivity.this.setResult(Constant.BankRESULT_OK, intent);
                BankListActivity.this.finish();
            }
        });
    }

    private void allbanks(){
        Api.gethttpService().getBankData("xxx").enqueue(new Callback<BankBean>() {
            @Override
            public void onResponse(Call<BankBean> call, Response<BankBean> response) {
                if ("00".equals(response.body().getCode())&&response.body().getList()!=null){
                    bankBean=response.body();

                    initView();
                }else{
                    App.getInstance().showToast(response.body().getFailMessage());
                }
            }

            @Override
            public void onFailure(Call<BankBean> call, Throwable t) {
                App.getInstance().showToast("服务器升级中");
            }
        });

    }
    private void initView() {
        listviewBankAdapter = new ListviewBankAdapter(BankListActivity.this, bankBean.getList());
        bankLvList.setAdapter(listviewBankAdapter);
    }

}
