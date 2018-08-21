package com.example.administrator.smallhappypay.util;

/**
 * Created by Administrator on 2018/6/20.
 */

public class LoginBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * map : {"amAddress":"花神大道一号","amState":1,"gradeName":"伯爵","pState":1,"amPerson":"邵亨林","gradeId":3,"pPhone":"18795883130","amName":"邵亨林","amNumber":"883000000000105","amIdNumber":"362322199705068435"}
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
         * amAddress : 花神大道一号
         * amState : 1
         * gradeName : 伯爵
         * pState : 1
         * amPerson : 邵亨林
         * gradeId : 3
         * pPhone : 18795883130
         * amName : 邵亨林
         * amNumber : 883000000000105
         * amIdNumber : 362322199705068435
         */

        private String amAddress;
        private int amState;
        private String gradeName;
        private int pState;
        private String amPerson;
        private int gradeId;
        private String pPhone;
        private String amName;
        private String amNumber;
        private String amIdNumber;

        public String getAmAddress() {
            return amAddress;
        }

        public void setAmAddress(String amAddress) {
            this.amAddress = amAddress;
        }

        public int getAmState() {
            return amState;
        }

        public void setAmState(int amState) {
            this.amState = amState;
        }

        public String getGradeName() {
            return gradeName;
        }

        public void setGradeName(String gradeName) {
            this.gradeName = gradeName;
        }

        public int getPState() {
            return pState;
        }

        public void setPState(int pState) {
            this.pState = pState;
        }

        public String getAmPerson() {
            return amPerson;
        }

        public void setAmPerson(String amPerson) {
            this.amPerson = amPerson;
        }

        public int getGradeId() {
            return gradeId;
        }

        public void setGradeId(int gradeId) {
            this.gradeId = gradeId;
        }

        public String getPPhone() {
            return pPhone;
        }

        public void setPPhone(String pPhone) {
            this.pPhone = pPhone;
        }

        public String getAmName() {
            return amName;
        }

        public void setAmName(String amName) {
            this.amName = amName;
        }

        public String getAmNumber() {
            return amNumber;
        }

        public void setAmNumber(String amNumber) {
            this.amNumber = amNumber;
        }

        public String getAmIdNumber() {
            return amIdNumber;
        }

        public void setAmIdNumber(String amIdNumber) {
            this.amIdNumber = amIdNumber;
        }
    }
}
