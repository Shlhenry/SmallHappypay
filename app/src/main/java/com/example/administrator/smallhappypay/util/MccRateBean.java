package com.example.administrator.smallhappypay.util;

import java.util.List;

/**
 * Created by Administrator on 2018/6/20.
 */

public class MccRateBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"D":[{"mcc_name":"微信D0","mcc_rate":0.0037,"mcc_id":"46","mcc_cap":999999},{"mcc_name":"支付宝D0","mcc_rate":0.0035,"mcc_id":"48","mcc_cap":999999}],"T":[{"mcc_name":"借记卡T1","mcc_rate":0.0055,"mcc_id":"41","mcc_cap":50},{"mcc_name":"信用卡T1","mcc_rate":0.0055,"mcc_id":"42","mcc_cap":999999},{"mcc_name":"微信T1","mcc_rate":0.0034,"mcc_id":"45","mcc_cap":999999},{"mcc_name":"支付宝T1","mcc_rate":0.0045,"mcc_id":"47","mcc_cap":999999}]}
     */

    private String code;
    private String message;
    private String successMessage;

    public String getFailMessage() {
        return failMessage;
    }

    public void setFailMessage(String failMessage) {
        this.failMessage = failMessage;
    }

    private String failMessage;
    private boolean isOK;
    private MapBean map;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSuccessMessage() {
        return successMessage;
    }

    public void setSuccessMessage(String successMessage) {
        this.successMessage = successMessage;
    }

    public boolean isIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }

    public MapBean getMap() {
        return map;
    }

    public void setMap(MapBean map) {
        this.map = map;
    }

    public static class MapBean {
        private List<DBean> D;
        private List<TBean> T;

        public List<DBean> getD() {
            return D;
        }

        public void setD(List<DBean> D) {
            this.D = D;
        }

        public List<TBean> getT() {
            return T;
        }

        public void setT(List<TBean> T) {
            this.T = T;
        }

        public static class DBean {
            /**
             * mcc_name : 微信D0
             * mcc_rate : 0.0037
             * mcc_id : 46
             * mcc_cap : 999999
             */

            private String mcc_name;
            private String mcc_rate;
            private String mcc_id;
            private String mcc_cap;

            public String getMcc_name() {
                return mcc_name;
            }

            public void setMcc_name(String mcc_name) {
                this.mcc_name = mcc_name;
            }

            public String getMcc_rate() {
                return mcc_rate;
            }

            public void setMcc_rate(String mcc_rate) {
                this.mcc_rate = mcc_rate;
            }

            public String getMcc_id() {
                return mcc_id;
            }

            public void setMcc_id(String mcc_id) {
                this.mcc_id = mcc_id;
            }

            public String getMcc_cap() {
                return mcc_cap;
            }

            public void setMcc_cap(String mcc_cap) {
                this.mcc_cap = mcc_cap;
            }
        }

        public static class TBean {
            /**
             * mcc_name : 借记卡T1
             * mcc_rate : 0.0055
             * mcc_id : 41
             * mcc_cap : 50
             */

            private String mcc_name;
            private String mcc_rate;
            private String mcc_id;
            private String mcc_cap;

            public String getMcc_name() {
                return mcc_name;
            }

            public void setMcc_name(String mcc_name) {
                this.mcc_name = mcc_name;
            }

            public String getMcc_rate() {
                return mcc_rate;
            }

            public void setMcc_rate(String mcc_rate) {
                this.mcc_rate = mcc_rate;
            }

            public String getMcc_id() {
                return mcc_id;
            }

            public void setMcc_id(String mcc_id) {
                this.mcc_id = mcc_id;
            }

            public String getMcc_cap() {
                return mcc_cap;
            }

            public void setMcc_cap(String mcc_cap) {
                this.mcc_cap = mcc_cap;
            }
        }
    }
}
