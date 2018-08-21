package com.example.administrator.smallhappypay;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.smallhappypay.fragment.BusinessFragment;
import com.example.administrator.smallhappypay.fragment.MineFragment;
import com.example.administrator.smallhappypay.fragment.TerminalFragment;
import com.silencezwm.libs.app.Comm;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


    @BindView(R.id.cont)
    FrameLayout cont;
    @BindView(R.id.main_business_img)
    ImageView mainBusinessImg;
    @BindView(R.id.main_business_txt)
    TextView mainBusinessTxt;
    @BindView(R.id.LinearLayout_business)
    LinearLayout LinearLayoutBusiness;
    @BindView(R.id.main_terminal_img)
    ImageView mainTerminalImg;
    @BindView(R.id.main_terminal_txt)
    TextView mainTerminalTxt;
    @BindView(R.id.LinearLayout_terminal)
    LinearLayout LinearLayoutTerminal;
    @BindView(R.id.main_mine_img)
    ImageView mainMineImg;
    @BindView(R.id.main_mine_txt)
    TextView mainMineTxt;
    @BindView(R.id.LinearLayout_mine)
    LinearLayout LinearLayoutMine;

    private BusinessFragment businessFragment;
    private TerminalFragment terminalFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setDefaultFragment();

    }

    @OnClick({R.id.LinearLayout_business, R.id.LinearLayout_terminal, R.id.LinearLayout_mine})
    public void onViewClicked(View view) {

        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();

        switch (view.getId()) {
            case R.id.LinearLayout_business:
                if (Comm.isFastDoubleClick(1000)) {
                    return;
                }
                if (businessFragment==null){
                    businessFragment=new BusinessFragment();
                }
                transaction.replace(R.id.cont,new BusinessFragment());

                mainBusinessImg.setImageDrawable(getResources().getDrawable(R.drawable.ywdjt));
                mainTerminalImg.setImageDrawable(getResources().getDrawable(R.drawable.zd));
                mainMineImg.setImageDrawable(getResources().getDrawable(R.drawable.wode));

                mainBusinessTxt.setTextColor(getResources().getColor(R.color.homecolor));
                mainTerminalTxt.setTextColor(getResources().getColor(R.color.hui));
                mainMineTxt.setTextColor(getResources().getColor(R.color.hui));
                break;
            case R.id.LinearLayout_terminal:
                if (Comm.isFastDoubleClick(1000)) {
                    return;
                }
                if (terminalFragment==null){
                    terminalFragment=new TerminalFragment();
                }
                transaction.replace(R.id.cont,new TerminalFragment());

                mainBusinessImg.setImageDrawable(getResources().getDrawable(R.drawable.yw));
                mainTerminalImg.setImageDrawable(getResources().getDrawable(R.drawable.zddjt));
                mainMineImg.setImageDrawable(getResources().getDrawable(R.drawable.wode));

                mainBusinessTxt.setTextColor(getResources().getColor(R.color.hui));
                mainTerminalTxt.setTextColor(getResources().getColor(R.color.homecolor));
                mainMineTxt.setTextColor(getResources().getColor(R.color.hui));
                break;
            case R.id.LinearLayout_mine:
                if (Comm.isFastDoubleClick(1000)) {
                    return;
                }
                if (mineFragment==null){
                    mineFragment=new MineFragment();
                }
                transaction.replace(R.id.cont,new MineFragment());

                mainBusinessImg.setImageDrawable(getResources().getDrawable(R.drawable.yw));
                mainTerminalImg.setImageDrawable(getResources().getDrawable(R.drawable.zd));
                mainMineImg.setImageDrawable(getResources().getDrawable(R.drawable.wodedjt));

                mainBusinessTxt.setTextColor(getResources().getColor(R.color.hui));
                mainTerminalTxt.setTextColor(getResources().getColor(R.color.hui));
                mainMineTxt.setTextColor(getResources().getColor(R.color.homecolor));
                break;
        }
        //事物提交
        transaction.commit();
    }

    private void setDefaultFragment(){
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction transaction=fm.beginTransaction();

        businessFragment=new BusinessFragment();
        transaction.replace(R.id.cont,new BusinessFragment());
        transaction.commit();

        mainBusinessImg.setImageDrawable(getResources().getDrawable(R.drawable.ywdjt));
        mainTerminalImg.setImageDrawable(getResources().getDrawable(R.drawable.zd));
        mainMineImg.setImageDrawable(getResources().getDrawable(R.drawable.wode));

        mainBusinessTxt.setTextColor(getResources().getColor(R.color.homecolor));
        mainTerminalTxt.setTextColor(getResources().getColor(R.color.hui));
        mainMineTxt.setTextColor(getResources().getColor(R.color.hui));

    }

}
