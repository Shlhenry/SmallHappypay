package com.example.administrator.smallhappypay.tool;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by Administrator on 2018/5/24.
 */

public class IpAddress {

    public static String GetInetAddress(String  host){
        String IPAddress = "";
        InetAddress ReturnStr1 = null;
        try {
            ReturnStr1 = InetAddress.getByName(host);
            IPAddress = ReturnStr1.getHostAddress();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return  IPAddress;
        }
        return IPAddress;
    }

}
