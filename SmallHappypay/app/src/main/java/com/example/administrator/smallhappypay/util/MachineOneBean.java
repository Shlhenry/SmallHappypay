package com.example.administrator.smallhappypay.util;

import java.util.List;

/**
 * Created by Administrator on 2018/7/3.
 */

public class MachineOneBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"one":[{"mode_remark":"类型","mode_type":1,"modename":"8110","mac_sn":"134556","mac_state":"1"},{"mode_remark":"类型","mode_type":1,"modename":"S58","mac_sn":"Fggggg","mac_state":"1"},{"mode_remark":"类型","mode_type":1,"modename":"ME31","mac_sn":"Fog","mac_state":"1"},{"mode_remark":"类型","mode_type":1,"modename":"M60","mac_sn":"1010101010010","mac_state":"1"},{"mode_remark":"类型","mode_type":1,"modename":"S58","mac_sn":"10101001010011001","mac_state":"1"},{"mode_remark":"类型","mode_type":1,"modename":"E330","mac_sn":"91199293993929","mac_state":"1"}]}
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
        private List<OneBean> one;

        public List<OneBean> getOne() {
            return one;
        }

        public void setOne(List<OneBean> one) {
            this.one = one;
        }

        public static class OneBean {
            /**
             * mode_remark : 类型
             * mode_type : 1
             * modename : 8110
             * mac_sn : 134556
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
