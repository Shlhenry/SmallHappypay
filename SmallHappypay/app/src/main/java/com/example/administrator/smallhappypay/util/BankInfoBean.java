package com.example.administrator.smallhappypay.util;

/**
 * Created by Administrator on 2018/6/28.
 */

public class BankInfoBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 查询成功
     * isOK : true
     * map : {"branch_number":"103301010245","account_number":"6228481778591517370","bankName":"农业银行","account_name":"邵亨林"}
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
         * branch_number : 103301010245
         * account_number : 6228481778591517370
         * bankName : 农业银行
         * account_name : 邵亨林
         */

        private String branch_number;
        private String account_number;
        private String bankName;
        private String account_name;

        public String getBranch_number() {
            return branch_number;
        }

        public void setBranch_number(String branch_number) {
            this.branch_number = branch_number;
        }

        public String getAccount_number() {
            return account_number;
        }

        public void setAccount_number(String account_number) {
            this.account_number = account_number;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }
    }
}
