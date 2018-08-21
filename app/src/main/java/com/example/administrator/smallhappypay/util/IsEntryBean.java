package com.example.administrator.smallhappypay.util;

/**
 * Created by Administrator on 2018/7/5.
 */

public class IsEntryBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"isface":false,"isjj":false}
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
        /**
         * isface : false
         * isjj : false
         */

        private String isface;
        private String isjj;

        public String getIsface() {
            return isface;
        }

        public void setIsface(String isface) {
            this.isface = isface;
        }

        public String getIsjj() {
            return isjj;
        }

        public void setIsjj(String isjj) {
            this.isjj = isjj;
        }
    }
}
