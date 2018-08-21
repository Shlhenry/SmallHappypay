package com.example.administrator.smallhappypay.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.activity.listview.AreaActivity;
import com.example.administrator.smallhappypay.activity.listview.BankListActivity;
import com.example.administrator.smallhappypay.activity.listview.CityActivity;
import com.example.administrator.smallhappypay.activity.listview.ProvinceListActivity;
import com.example.administrator.smallhappypay.constant.Constant;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.CropHandler;
import com.example.administrator.smallhappypay.tool.CropParams;
import com.example.administrator.smallhappypay.tool.FileUtil;
import com.example.administrator.smallhappypay.tool.ImageLoaderUtil;
import com.example.administrator.smallhappypay.tool.PhotoCropUtil;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.NoBackBean;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthActivity extends BaseActivity {


    @BindView(R.id.infochange_back_ll)
    LinearLayout infochangeBackLl;
    @BindView(R.id.infochange_commit_tv)
    TextView infochangeCommitTv;
    @BindView(R.id.infochange_sz_img)
    ImageView infochangeSzImg;
    @BindView(R.id.infochange_sf_img)
    ImageView infochangeSfImg;
    @BindView(R.id.infochange_scz_img)
    ImageView infochangeSczImg;
    @BindView(R.id.infochange_yf_img)
    ImageView infochangeYfImg;
    @BindView(R.id.infochange_phone_et)
    EditText infochangePhoneEt;
    @BindView(R.id.infochange_merchantname_et)
    EditText infochangeMerchantnameEt;
    @BindView(R.id.infochange_idname_et)
    EditText infochangeIdnameEt;
    @BindView(R.id.infochange_idnum_et)
    EditText infochangeIdnumEt;
    @BindView(R.id.infochange_address_et)
    EditText infochangeAddressEt;
    @BindView(R.id.infochange_province_tx)
    TextView infochangeProvinceTx;
    @BindView(R.id.infochange_city_tx)
    TextView infochangeCityTx;
    @BindView(R.id.infochange_county_tx)
    TextView infochangeCountyTx;
    @BindView(R.id.infochange_cardnum_et)
    EditText infochangeCardnumEt;
    @BindView(R.id.infochange_cardname_et)
    EditText infochangeCardnameEt;
    @BindView(R.id.infochange_publice_img)
    ImageView infochangePubliceImg;
    @BindView(R.id.infochange_provate_img)
    ImageView infochangeProvateImg;
    @BindView(R.id.infochange_cardname_tv)
    TextView infochangeCardnameTv;
    @BindView(R.id.infochange_kaihuhang_et)
    EditText infochangeKaihuhangEt;
    @BindView(R.id.infochange_lianhanghao_et)
    EditText infochangeLianhanghaoEt;
    @BindView(R.id.infochange_kaihuhangprovince_tx)
    TextView infochangeKaihuhangprovinceTx;
    @BindView(R.id.infochange_kaihuhangcity_tx)
    TextView infochangeKaihuhangcityTx;
    private int ProvinceId = 0;//省份id
    private int CityId = 0;//城市id
    private int AreaId = 0;//区id
    private int Bankid = 0;//银行id

    private int NewProvinceId = 0;//开户行省份id
    private int NewCityId = 0;//开户行城市id

    private String imageStr1;
    private String imageStr2;
    private String imageStr3;
    private String imageStr4;
    private int i;
    CropParams mCropParams;

    private static final int REQUEST_CODE_CAMERA = 102;
    private static final int REQUEST_CODE_DRIVING_LICENSE = 103;
    private static final int REQUEST_CODE_VEHICLE_LICENSE = 104;

    private int type = 2;//判断账户类型对公还是对私

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_change);
        ButterKnife.bind(this);
    }

    private void getauthdata(){
        String phone =infochangePhoneEt.getText().toString();
        String amPerson =infochangeIdnameEt.getText().toString();
        String accountNumber =infochangeCardnumEt.getText().toString();
        String amIdNumber =infochangeIdnumEt.getText().toString();
        String amName =infochangeMerchantnameEt.getText().toString();
        String proId =ProvinceId+"";
        String cityId =CityId+"";
        String areaId =AreaId+"";
        String amAddress =infochangeAddressEt.getText().toString();
        String accountName =infochangeCardnameEt.getText().toString();
        String accountType =type+"";
        String bank =Bankid+"";
        String bankBranchName =infochangeKaihuhangEt.getText().toString();
        String bankBranchNumber =infochangeLianhanghaoEt.getText().toString();
        String prov =NewProvinceId+"";
        String city =NewCityId+"";
        String positiveidcard =imageStr1;
        String reverseidcard =imageStr2;
        String positivecard =imageStr3;
        String handidcard =imageStr4;

        auth(phone,amPerson,accountNumber,amIdNumber,amName,proId,cityId,areaId,amAddress,accountName,accountType,bank,
                 bankBranchName,bankBranchNumber,prov,city,positiveidcard,reverseidcard,positivecard,handidcard);
    }


    @OnClick({R.id.infochange_commit_tv, R.id.infochange_back_ll, R.id.infochange_province_tx, R.id.infochange_city_tx, R.id.infochange_county_tx, R.id.infochange_cardname_tv, R.id.infochange_provate_img, R.id.infochange_publice_img, R.id.infochange_sz_img, R.id.infochange_sf_img, R.id.infochange_scz_img, R.id.infochange_yf_img, R.id.infochange_kaihuhangprovince_tx, R.id.infochange_kaihuhangcity_tx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.infochange_commit_tv:
                getauthdata();
                break;
            case R.id.infochange_back_ll:
                finish();
                break;
            case R.id.infochange_province_tx:
                SPUtils.put(getApplication(), "judgeprovince", "1");
                Intent intent = new Intent(AuthActivity.this, ProvinceListActivity.class);
                startActivityForResult(intent, Constant.REQUEST_OK);
                break;
            case R.id.infochange_city_tx:
                if (ProvinceId == 0) {
                    Toast.makeText(this, "请先选择省份", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent1 = new Intent(AuthActivity.this, CityActivity.class);
                intent1.putExtra(Constant.IN_ID, ProvinceId);
                startActivityForResult(intent1, Constant.REQUEST_OK);
                break;
            case R.id.infochange_county_tx:
                if (CityId == 0) {
                    Toast.makeText(this, "请先选择城市", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent2 = new Intent(AuthActivity.this, AreaActivity.class);
                intent2.putExtra(Constant.IN_ID, CityId);
                startActivityForResult(intent2, Constant.REQUEST_OK);
                break;
            case R.id.infochange_cardname_tv:
                Intent intent4 = new Intent(AuthActivity.this, BankListActivity.class);
                intent4.putExtra(Constant.IN_BANK_ID, Bankid);
                startActivityForResult(intent4, Constant.REQUEST_OK);
                break;
            case R.id.infochange_provate_img:
                type = 1;
                infochangeProvateImg.setBackgroundResource(R.drawable.xxxglxl);
                infochangePubliceImg.setBackgroundResource(R.drawable.xxxglxo);
                break;
            case R.id.infochange_publice_img:
                type = 0;
                infochangeProvateImg.setBackgroundResource(R.drawable.xxxglxt);
                infochangePubliceImg.setBackgroundResource(R.drawable.xxxglxw);
                break;
            case R.id.infochange_sz_img:
                showMenuDialog();
                i = 1;
                break;
            case R.id.infochange_sf_img:
                showMenuDialog();
                i = 2;
                break;
            case R.id.infochange_scz_img:
                showMenuDialog();
                i = 3;
                break;
            case R.id.infochange_yf_img:
                showMenuDialog();
                i = 4;
                break;
            case R.id.infochange_kaihuhangprovince_tx:
                SPUtils.put(getApplication(), "judgeprovince", "2");
                Intent intent5 = new Intent(AuthActivity.this, ProvinceListActivity.class);
                startActivityForResult(intent5, Constant.REQUEST_OK);
                break;
            case R.id.infochange_kaihuhangcity_tx:
                if (NewProvinceId == 0) {
                    Toast.makeText(this, "请先选择省份", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent6 = new Intent(AuthActivity.this, CityActivity.class);
                intent6.putExtra(Constant.IN_ID, NewProvinceId);
                startActivityForResult(intent6, Constant.REQUEST_OK);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.REQUEST_OK) {
            switch (resultCode) {
                case Constant.ProvinceRESULT_OK:
                    ProvinceId = data.getIntExtra(Constant.IN_City_ID, 0);
                    infochangeProvinceTx.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.CityRESULT_OK:
                    CityId = data.getIntExtra(Constant.IN_City_ID, 0);
                    infochangeCityTx.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.AreaRESULT_OK:
                    AreaId = data.getIntExtra(Constant.IN_City_ID, 0);
                    infochangeCountyTx.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.BankRESULT_OK:
                    Bankid = data.getIntExtra(Constant.IN_BANK_ID, 0);
                    infochangeCardnameTv.setText(data.getStringExtra(Constant.IN_BANK_NAME));
                    break;
                case Constant.NewProvinceRESULT_OK:
                    NewProvinceId = data.getIntExtra(Constant.IN_City_ID, 0);
                    infochangeKaihuhangprovinceTx.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
                case Constant.NewCityRESULT_OK:
                    NewCityId = data.getIntExtra(Constant.IN_City_ID, 0);
                    infochangeKaihuhangcityTx.setText(data.getStringExtra(Constant.IN_City_Name));
                    break;
            }
        }

        switch (requestCode) {
            case PhotoCropUtil.REQUEST_CAMERA:
            case PhotoCropUtil.REQUEST_PICK:
                PhotoCropUtil.handleResult(new CropHandler() {
                    @Override
                    public void onPhotoCropped(Uri uri) {
                        if (i == 1) {
                            String path = uri.toString();
                            ImageLoaderUtil.loadImage(path, infochangeSzImg, null);
                            imageStr1 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                            Log.e("******", imageStr1);
                        } else if (i == 2) {
                            String path = uri.toString();
                            ImageLoaderUtil.loadImage(path, infochangeSfImg, null);
                            imageStr2 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                            Log.e("******", imageStr2);
                        } else if (i == 3) {
                            String path = uri.toString();
                            ImageLoaderUtil.loadImage(path, infochangeSczImg, null);
                            imageStr3 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                            Log.e("******", imageStr3);
                        } else if (i == 4) {
                            String path = uri.toString();
                            ImageLoaderUtil.loadImage(path, infochangeYfImg, null);
                            imageStr4 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                            Log.e("******", imageStr4);
                        }
                    }

                    @Override
                    public void onCompressed(Uri uri) {
                        if (i == 1) {
                            String path = uri.toString();
                            ImageLoaderUtil.loadImage(path, infochangeSzImg, null);
                            imageStr1 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                            Log.e("******", imageStr1);
                        } else if (i == 2) {
                            String path = uri.toString();
                            ImageLoaderUtil.loadImage(path, infochangeSfImg, null);
                            imageStr2 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                            Log.e("******", imageStr2);
                        } else if (i == 3) {
                            String path = uri.toString();
                            ImageLoaderUtil.loadImage(path, infochangeSczImg, null);
                            imageStr3 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                            Log.e("******", imageStr3);
                        } else if (i == 4) {
                            String path = uri.toString();
                            ImageLoaderUtil.loadImage(path, infochangeYfImg, null);
                            imageStr4 = FileUtil.path2StrByBase64(Uri.parse(path).getPath());
                            Log.e("******", imageStr4);
                        }
                    }

                    @Override
                    public void onCancel() {
                        Log.w("拍照取消", "拍照取消");
                    }

                    @Override
                    public void onFailed(String message) {
                        Log.w("拍照失败", "拍照失败");
                    }

                    @Override
                    public void handleIntent(Intent intent, int requestCode) {
                        startActivityForResult(intent, requestCode);
                    }

                    @Override
                    public CropParams getCropParams() {
                        return mCropParams;
                    }
                }, requestCode, resultCode, data);
                break;
        }
    }

    private void showMenuDialog() {
        final String[] items = new String[]{"拍照上传", "相册选择"};
        new QMUIDialog.MenuDialogBuilder(this)
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                takePhoto(false);
                                break;
                            case 1:
                                galleryPhoto(false);
                                break;
                        }
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void takePhoto(boolean isCrop) {
        mCropParams = new CropParams(this);
        mCropParams.enable = isCrop;
        mCropParams.compress = true;
        mCropParams.refreshUri();
        Intent intent = PhotoCropUtil.buildCameraIntent(mCropParams);
        startActivityForResult(intent, PhotoCropUtil.REQUEST_CAMERA);
    }

    //相册
    private void galleryPhoto(boolean isCrop) {
        mCropParams = new CropParams(this);
        mCropParams.enable = isCrop;
        mCropParams.compress = true;

        mCropParams.refreshUri();
        Intent intent = PhotoCropUtil.buildGalleryIntent(mCropParams);
        startActivityForResult(intent, PhotoCropUtil.REQUEST_PICK);
    }

    //实名认证商户进件
    protected void auth(String phone, String amPerson, String accountNumber, String amIdNumber, String amName, String proId,
                        String cityId, String areaId, String amAddress, String accountName, String accountType, String bank,
                        String bankBranchName, String bankBranchNumber, String prov, String city, String positiveidcard,
                        String reverseidcard, String positivecard, String handidcard) {
        Api.gethttpService().getAuthData(phone, amPerson, accountNumber, amIdNumber, amName, proId, cityId,
                areaId, amAddress, accountName, accountType, bank, bankBranchName, bankBranchNumber, prov,
                city, positiveidcard, reverseidcard, positivecard, handidcard).enqueue(new Callback<NoBackBean>() {
            @Override
            public void onResponse(Call<NoBackBean> call, Response<NoBackBean> response) {
                if ("00".equals(response.body().getCode())){
                    mdialog(AuthActivity.this, response.body().getSuccessMessage());
                }else {
                    mdialog(AuthActivity.this, response.body().getFailMessage());
                }
            }

            @Override
            public void onFailure(Call<NoBackBean> call, Throwable t) {
                mdialog(AuthActivity.this, "服务器维护中");
            }
        });
    }

}
