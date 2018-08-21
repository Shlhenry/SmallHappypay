package com.example.administrator.smallhappypay.tool;

import android.annotation.SuppressLint;
import android.os.StrictMode;

/**
 * Created by Administrator on 2018/6/13.
 */

public class AuthorityManager {

    @SuppressLint("NewApi")
    public static void initPhotoError(){
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

    }
}
