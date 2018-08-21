package com.example.administrator.smallhappypay.net;


import com.example.administrator.smallhappypay.util.BankBean;
import com.example.administrator.smallhappypay.util.BankInfoBean;
import com.example.administrator.smallhappypay.util.ChartBean;
import com.example.administrator.smallhappypay.util.CityBean;
import com.example.administrator.smallhappypay.util.IsEntryBean;
import com.example.administrator.smallhappypay.util.LoginBean;
import com.example.administrator.smallhappypay.util.MachineInfoBean;
import com.example.administrator.smallhappypay.util.MachineOneBean;
import com.example.administrator.smallhappypay.util.MccRateBean;
import com.example.administrator.smallhappypay.util.MineInfoBean;
import com.example.administrator.smallhappypay.util.NoBackBean;
import com.example.administrator.smallhappypay.util.QueryModelBean;
import com.example.administrator.smallhappypay.util.WaterBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/18.
 */

public interface HttpService {

    @FormUrlEncoded
    @POST("MicroplateInterface/login")//登入请求
    Call<LoginBean> getLoginData(@Field("acountNo") String acountNo,
                                 @Field("pwd") String pwd);

    @FormUrlEncoded
    @POST("MicroplateInterface/register")//注册请求
    Call<NoBackBean> getRegisterData(@Field("phone") String phone,
                                     @Field("pwd") String pwd,
                                     @Field("code") String code,
                                     @Field("recomPhone") String recomPhone);

    @FormUrlEncoded
    @POST("MicroplateInterface/getMcc")//获取费率
    Call<MccRateBean> getMccData(@Field("am_number") String am_number);

    @FormUrlEncoded
    @POST("MicroplateInterface/modifyPwd")//忘记密码
    Call<NoBackBean> getForgetPwdData(@Field("phone") String phone,
                                      @Field("pwd") String pwd,
                                      @Field("code") String code,
                                      @Field("reNewPwd") String reNewPwd);

    @FormUrlEncoded
    @POST("MicroplateInterface/queryBank")//获取银行列表请求
    Call<BankBean> getBankData(@Field("xxx")String xxx);

    @FormUrlEncoded
    @POST("MicroplateInterface/queryCity")//获取省请求
    Call<CityBean> getPrivinceData(@Field("parentId")String cityId);

    @FormUrlEncoded
    @POST("MicroplateInterface/queryCity")//获取市请求
    Call<CityBean> getCityData(@Field("parentId")String cityIdone);

    @FormUrlEncoded
    @POST("MicroplateInterface/queryCity")//获取区请求
    Call<CityBean> getAreaData(@Field("parentId")String cityIdtwo);

    @FormUrlEncoded
    @POST("MicroplateInterface/getTrans")//获取流水
    Call<WaterBean> getWaterData(@Field("am_number") String am_number,
                                     @Field("type") String type,
                                     @Field("pageSize") int pageSize,
                                     @Field("pageNumber") String pageNumber);

    @FormUrlEncoded
    @POST("MicroplateInterface/getSummary")//根据商户号获取账户余额及当天的t0、t1交易汇总
    Call<MineInfoBean> getSummaryData(@Field("am_number")String am_number);

    @FormUrlEncoded
    @POST("MicroplateInterface/getAccount")//获取银行卡账户信息
    Call<BankInfoBean> getBankInfoData(@Field("am_number")String am_number);

    @FormUrlEncoded
    @POST("MicroplateInterface/queryModel")//获取终端设备信息
    Call<QueryModelBean> getqueryModelData(@Field("type")String type);

    @FormUrlEncoded
    @POST("MicroplateInterface/machine")//终端商户绑定
    Call<NoBackBean> getMachineData(@Field("amNumber") String amNumber,
                                 @Field("sn") String sn,
                                 @Field("mmName") String mmName);

    @FormUrlEncoded
    @POST("MicroplateInterface/untie")//终端解绑
    Call<NoBackBean> getuntieData(@Field("sn")String sn);

    @FormUrlEncoded
    @POST("MicroplateInterface/getMachine")//获取终端信息
    Call<MachineInfoBean> getMachineInfoData(@Field("am_number")String am_number);

    @FormUrlEncoded
    @POST("MicroplateInterface/getMachine")//获取终端信息
    Call<MachineOneBean> getmachineoneData(@Field("am_number")String am_number,
                                           @Field("type")String type);

    @FormUrlEncoded
    @POST("MicroplateInterface/face")//人脸认证
    Call<NoBackBean> getfaceData(@Field("phone")String phone,
                                 @Field("pface")String pface);

    @FormUrlEncoded
    @POST("MicroplateInterface/isentry")//判断是否人脸认证和进件
    Call<IsEntryBean> getisentryData(@Field("phone")String phone);

    @FormUrlEncoded
    @POST("MicroplateInterface/authen")//实名认证商户进件
    Call<NoBackBean> getAuthData(@Field("phone")String phone,
                                 @Field("amPerson")String amPerson,
                                 @Field("accountNumber")String accountNumber,
                                 @Field("amIdNumber")String amIdNumber,
                                 @Field("amName")String amName,
                                 @Field("proId")String proId,
                                 @Field("cityId")String cityId,
                                 @Field("areaId")String areaId,
                                 @Field("amAddress")String amAddress,
                                 @Field("accountName")String accountName,
                                 @Field("accountType")String accountType,
                                 @Field("bank")String bank,
                                 @Field("bankBranchName")String bankBranchName,
                                 @Field("bankBranchNumber")String bankBranchNumber,
                                 @Field("prov")String prov,
                                 @Field("city")String city,
                                 @Field("positiveidcard")String positiveidcard,
                                 @Field("reverseidcard")String reverseidcard,
                                 @Field("positivecard")String positivecard,
                                 @Field("handidcard")String handidcard);

    @FormUrlEncoded
    @POST("MicroplateInterface/getCode")//获取验证码请求
    Call<NoBackBean> getCodeData(@Field("phone") String phone,
                                 @Field("operator") String operator);

    @FormUrlEncoded
    @POST("MicroplateInterface/getReport")//获取验证码请求
    Call<ChartBean> getReportData(@Field("am_number") String am_number,
                                  @Field("month") String month);

//    @FormUrlEncoded
//    @POST("PhoneInterface/getVersion")//版本更新
//    Call<AppVersionBean> GetAppVersionData(@Field("packageName") String packageName);



}
