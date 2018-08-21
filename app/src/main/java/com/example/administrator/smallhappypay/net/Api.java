package com.example.administrator.smallhappypay.net;


import cn.droidlover.xdroidmvp.net.XApi;

/**
 * Created by Administrator on 2017/4/1.
 * 接口存放
 */
public class Api {
    public static String URL = "http://120.27.194.146:8081/";// 测试地址  http://218.240.148.252:8081/
    public static String POSP_IP = "218.240.148.252";//Socket地址
    public static String POSP_PORT = "13079";//Socket端口

    private static HttpService httpService;

    private static final String BASE_URL = "https://aip.baidubce.com";

//    private static final String ACCESS_TOEKN_URL = BASE_URL + "/oauth/2.0/token?";

    private static final String Register_URL = BASE_URL + "/rest/2.0/face/v3/faceset/user/add";//人脸注册

    private static final String Search_URL = BASE_URL + "/rest/2.0/face/v3/search";//人脸查找


    /**
     * @return
     */
    public static HttpService gethttpService() {
        if (httpService == null) {
            synchronized (Api.class) {
                if (httpService == null) {
                    httpService = XApi.getInstance().getRetrofit(URL, true).create(HttpService.class);
                }
            }
        }
        return httpService;
    }
}