package com.example.administrator.smallhappypay.util;

import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class MachineInfoBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"two":[{"mode_remark":"类型","mode_type":2,"modename":"K90","mac_sn":"123","mac_state":"1"}],"one":[{"mode_remark":"类型","mode_type":1,"modename":"8110","mac_sn":"Ww","mac_state":"1"}],"three":[{"mode_remark":"扫码枪","mode_type":3,"modename":"SL58","mac_sn":"245","mac_state":"1"}]}
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
        private List<TwoBean> two;
        private List<OneBean> one;
        private List<ThreeBean> three;

        public List<TwoBean> getTwo() {
            return two;
        }

        public void setTwo(List<TwoBean> two) {
            this.two = two;
        }

        public List<OneBean> getOne() {
            return one;
        }

        public void setOne(List<OneBean> one) {
            this.one = one;
        }

        public List<ThreeBean> getThree() {
            return three;
        }

        public void setThree(List<ThreeBean> three) {
            this.three = three;
        }

        public static class TwoBean {
            /**
             * mode_remark : 类型
             * mode_type : 2
             * modename : K90
             * mac_sn : 123
             * mac_state : 1
             */

            private String mode_remark;
            private int mode_type;
            private String modename;
            private String mac_sn;
            private String mac_state;

            public String getMode_remark() {
                return mode_remark;
            }

            public void setMode_remark(String mode_remark) {
                this.mode_remark = mode_remark;
            }

            public int getMode_type() {
                return mode_type;
            }

            public void setMode_type(int mode_type) {
                this.mode_type = mode_type;
            }

            public String getModename() {
                return modename;
            }

            public void setModename(String modename) {
                this.modename = modename;
            }

            public String getMac_sn() {
                return mac_sn;
            }

            public void setMac_sn(String mac_sn) {
                this.mac_sn = mac_sn;
            }

            public String getMac_state() {
                return mac_state;
            }

            public void setMac_state(String mac_state) {
                this.mac_state = mac_state;
            }
        }

        public static class OneBean {
            /**
             * mode_remark : 类型
             * mode_type : 1
             * modename : 8110
             * mac_sn : Ww
             * mac_state : 1
             */

            private String mode_remark;
            private int mode_type;
            private String modename;
            private String mac_sn;
            private String mac_state;

            public String getMode_remark() {
                return mode_remark;
            }

            public void setMode_remark(String mode_remark) {
                this.mode_remark = mode_remark;
            }

            public int getMode_type() {
                return mode_type;
            }

            public void setMode_type(int mode_type) {
                this.mode_type = mode_type;
            }

            public String getModename() {
                return modename;
            }

            public void setModename(String modename) {
                this.modename = modename;
            }

            public String getMac_sn() {
                return mac_sn;
            }

            public void setMac_sn(String mac_sn) {
                this.mac_sn = mac_sn;
            }

            public String getMac_state() {
                return mac_state;
            }

            public void setMac_state(String mac_state) {
                this.mac_state = mac_state;
            }
        }

        public static class ThreeBean {
            /**
             * mode_remark : 扫码枪
             * mode_type : 3
             * modename : SL58
             * mac_sn : 245
             * mac_state : 1
             */

            private String mode_remark;
            private int mode_type;
            private String modename;
            private String mac_sn;
            private String mac_state;

            public String getMode_remark() {
                return mode_remark;
            }

            public void setMode_remark(String mode_remark) {
                this.mode_remark = mode_remark;
            }

            public int getMode_type() {
                return mode_type;
            }

            public void setMode_type(int mode_type) {
                this.mode_type = mode_type;
            }

            public String getModename() {
                return modename;
            }

            public void setModename(String modename) {
                this.modename = modename;
            }

            public String getMac_sn() {
                return mac_sn;
            }

            public void setMac_sn(String mac_sn) {
                this.mac_sn = mac_sn;
            }

            public String getMac_state() {
                return mac_state;
            }

            public void setMac_state(String mac_state) {
                this.mac_state = mac_state;
            }
        }
    }
}
