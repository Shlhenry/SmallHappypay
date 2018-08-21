package com.example.administrator.smallhappypay;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.smallhappypay.activity.LoginActivity;
import com.example.administrator.smallhappypay.activity.RegisterActivity;
import com.example.administrator.smallhappypay.tool.StatusBarUtil;
import com.qmuiteam.qmui.widget.dialog.QMUITipDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/18.
 */

public class BaseActivity extends FragmentActivity {

    private List<TurnBackListener> mTurnBackListeners = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StatusBarUtil.setColor(BaseActivity.this,getResources().getColor(R.color.homecolor),0);
    }

    static QMUITipDialog tipDialog;

    public static void mdialog(Activity activity,String msg){
        final QMUITipDialog dialog;
        dialog = new QMUITipDialog.CustomBuilder(activity)
                .setContent(R.layout.messagebox)
                .create();
        dialog.show();
        ImageView close=dialog.findViewById(R.id.messagebox_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        TextView message=dialog.findViewById(R.id.messagebox_text);
        message.setText(msg);
    }

    public static void LoadingDialog(Activity activity,String msg){

        tipDialog = new QMUITipDialog.Builder(activity)
                .setIconType(QMUITipDialog.Builder.ICON_TYPE_LOADING)
                .setTipWord(msg)
                .create();
        tipDialog.show();
    }

    public static void dismiss(){
        tipDialog.dismiss();
    }

    public interface TurnBackListener {
        boolean onTurnBack();
    }

    private long mBackPressedTime;
    @Override
    public void onBackPressed() {
        for (TurnBackListener l : mTurnBackListeners) {
            if (l.onTurnBack()) return;
        }

        if (this instanceof MainActivity) {
            long curTime = SystemClock.uptimeMillis();
            if ((curTime - mBackPressedTime) < (3 * 1000)) {
                finish();
            } else {
                mBackPressedTime = curTime;
                Toast.makeText(getApplicationContext(),"再按一次退出程序",Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onBackPressed();
        }
    }

}
