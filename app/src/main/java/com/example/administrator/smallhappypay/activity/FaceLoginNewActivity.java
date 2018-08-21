package com.example.administrator.smallhappypay.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.aip.FaceSDKManager;
import com.example.administrator.smallhappypay.App;
import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.face.APIService;
import com.example.administrator.smallhappypay.face.DetectLoginActivity;
import com.example.administrator.smallhappypay.face.FaceDetectActivity;
import com.example.administrator.smallhappypay.face.exception.FaceError;
import com.example.administrator.smallhappypay.face.model.RegResult;
import com.example.administrator.smallhappypay.face.utils.ImageSaveUtil;
import com.example.administrator.smallhappypay.face.utils.OnResultListener;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.tool.StatusBarUtil;
import com.example.administrator.smallhappypay.util.NoBackBean;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FaceLoginNewActivity extends BaseActivity {

    @BindView(R.id.facenewlogin_back_ll)
    LinearLayout facenewloginBackLl;
    @BindView(R.id.avatar_iv)
    ImageView avatarIv;
    @BindView(R.id.detect_btn)
    TextView detectBtn;
    @BindView(R.id.submit_btn)
    TextView submitBtn;
    @BindView(R.id.faceloginnew_tel_tv)
    TextView faceloginnewTelTv;

    private String facePath;
    private Bitmap mHeadBmp;
    private static final int REQUEST_CODE_DETECT_FACE = 1000;
    private static final int REQUEST_CODE_PICK_IMAGE = 1001;

    private String phone;
    private String pwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_login_new);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(FaceLoginNewActivity.this,getResources().getColor(R.color.facecolor),0);
        phone=SPUtils.getString(getApplication(),"phone");
        pwd=SPUtils.getString(getApplication(),"pwd");
        faceloginnewTelTv.setText(phone);
        init();
    }

    private void init() {
        // 如果图片中的人脸小于200*200个像素，将不能检测出人脸，可以根据需求在100-400间调节大小
        FaceSDKManager.getInstance().getFaceTracker(this).set_min_face_size(200);
        FaceSDKManager.getInstance().getFaceTracker(this).set_isCheckQuality(true);
        // 该角度为商学，左右，偏头的角度的阀值，大于将无法检测出人脸，为了在1：n的时候分数高，注册尽量使用比较正的人脸，可自行条件角度
        FaceSDKManager.getInstance().getFaceTracker(this).set_eulur_angle_thr(45, 45, 45);
        FaceSDKManager.getInstance().getFaceTracker(this).set_isVerifyLive(true);
    }

    @OnClick({R.id.facenewlogin_back_ll, R.id.detect_btn, R.id.submit_btn})
    public void onViewClicked(View view) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 100);
            return;
        }

        switch (view.getId()) {
            case R.id.facenewlogin_back_ll:
                finish();
                break;
            case R.id.detect_btn:
                Intent it = new Intent(FaceLoginNewActivity.this, FaceDetectActivity.class);
                startActivityForResult(it, REQUEST_CODE_DETECT_FACE);
                break;
            case R.id.submit_btn:
                // 人脸注册和1：n属于在线接口，需要通过ak，sk获得token后进行调用，此方法为获取token，为了防止你得ak，sk泄露，
                // 建议把此调用放在您的服务端

                if (TextUtils.isEmpty(facePath)) {
                    App.getInstance().showToast("请先进行人脸采集");
                } else {
                    // 您可以使用在线活体检测后在进行注册，这样安全性更高，也可以直接注册。（在线活体请在官网控制台提交申请工单）
                    LoadingDialog(FaceLoginNewActivity.this,"请稍后");
                    reg(facePath);
                    // onlineLiveness(facePath);
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DETECT_FACE && resultCode == Activity.RESULT_OK) {

            facePath = ImageSaveUtil.loadCameraBitmapPath(this, "head_tmp.jpg");
            if (mHeadBmp != null) {
                mHeadBmp.recycle();
            }
            mHeadBmp = ImageSaveUtil.loadBitmapFromPath(this, facePath);
            if (mHeadBmp != null) {
                avatarIv.setImageBitmap(mHeadBmp);
            }
        } else if (requestCode == REQUEST_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            facePath = getRealPathFromURI(uri);

            if (mHeadBmp != null) {
                mHeadBmp.recycle();
            }
            mHeadBmp = ImageSaveUtil.loadBitmapFromPath(this, facePath);
            if (mHeadBmp != null) {
                avatarIv.setImageBitmap(mHeadBmp);
            }
        }
    }

    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    private void reg(String filePath) {
        final File file = new File(filePath);
        if (!file.exists()) {
            App.getInstance().showToast("文件不存在");
            return;
        }
        // TODO 人脸注册说明 https://aip.baidubce.com/rest/2.0/face/v2/faceset/user/add
        // 模拟注册，先提交信息注册获取uid，再使用人脸+uid到百度人脸库注册，
        // TODO 实际使用中，建议注册放到您的服务端进行（这样可以有效防止ak，sk泄露） 把注册信息包括人脸一次性提交到您的服务端，
        // TODO 注册获得uid，然后uid+人脸调用百度人脸注册接口，进行注册。

        // 每个开发者账号只能创建一个人脸库；
        // 每个人脸库下，用户组（group）数量没有限制；
        // 每个用户组（group）下，可添加最多300000张人脸，如每个uid注册一张人脸，则最多300000个用户uid；
        // 每个用户（uid）所能注册的最大人脸数量没有限制；
        // 说明：人脸注册完毕后，生效时间最长为35s，之后便可以进行识别或认证操作。
        // 说明：注册的人脸，建议为用户正面人脸。
        // 说明：uid在库中已经存在时，对此uid重复注册时，新注册的图片默认会追加到该uid下，如果手动选择action_type:replace，
        // 则会用新图替换库中该uid下所有图片。
        // uid          是	string	用户id（由数字、字母、下划线组成），长度限制128B
        // user_info    是	string	用户资料，长度限制256B
        // group_id	    是	string	用户组id，标识一组用户（由数字、字母、下划线组成），长度限制128B。
        // 如果需要将一个uid注册到多个group下，group_id,需要用多个逗号分隔，每个group_id长度限制为48个英文字符
        // image	    是	string	图像base64编码，每次仅支持单张图片，图片编码后大小不超过10M
        // action_type	否	string	参数包含append、replace。如果为“replace”，则每次注册时进行替换replace（新增或更新）操作，
        // 默认为append操作
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                faceReg(file);
            }
        }, 1000);

    }

    private void faceReg(File file) {

        // 用户id（由数字、字母、下划线组成），长度限制128B
        // uid为用户的id,百度对uid不做限制和处理，应该与您的帐号系统中的用户id对应。

        //   String uid = UUID.randomUUID().toString().substring(0, 8) + "_123";
        // String uid = 修改为自己用户系统中用户的id;
        // 模拟使用username替代
        String username = phone;
//        String uid = Md5.MD5(username, "utf-8");
        String uid = username;
        String user_id = pwd;

        Log.w("+++人脸注册+++", "账号密码" + username + "//" + user_id);

        APIService.getInstance().reg(new OnResultListener<RegResult>() {
            @Override
            public void onResult(RegResult result) {
                Log.i("wtf", "orientation->" + result.getJsonRes());
                pface(phone,"1");
            }

            @Override
            public void onError(FaceError error) {
                dismiss();
                App.getInstance().showToast("注册失败！");
            }
        }, file, uid, username, user_id);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHeadBmp != null) {
            mHeadBmp.recycle();
        }
    }

    private void pface(String phone,String pface){
        Api.gethttpService().getfaceData(phone, pface).enqueue(new Callback<NoBackBean>() {
            @Override
            public void onResponse(Call<NoBackBean> call, Response<NoBackBean> response) {
                dismiss();
                if ("00".equals(response.body().getCode())){
                    App.getInstance().showToast("注册成功！");
                    finish();
                }
            }
            @Override
            public void onFailure(Call<NoBackBean> call, Throwable t) {
                dismiss();
                mdialog(FaceLoginNewActivity.this,"请稍后");
            }
        });
    }

}
