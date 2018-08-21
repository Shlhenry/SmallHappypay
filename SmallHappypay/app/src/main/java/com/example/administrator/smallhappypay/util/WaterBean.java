package com.example.administrator.smallhappypay.util;

import java.util.List;

/**
 * Created by Administrator on 2018/6/26.
 */

public class WaterBean {


    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"tranTime":"2017-06-26 09:55:13","tran2":"6230200022520279","tranFee":0.08,"tran37":"236621449627","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-06-17 10:16:19","tran2":"6230200022520279","tranFee":0.08,"tran37":"245681478817","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-06-17 10:14:49","tran2":"6230200022520279","tranFee":0.08,"tran37":"275681478807","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-06-17 10:08:44","tran2":"6230200022520279","tranFee":0.08,"tran37":"235681478747","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-06-17 10:05:15","tran2":"6230200022520279","tranFee":0.08,"tran37":"265681478727","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-06-09 10:05:25","tran2":"6230200022520279","tranFee":0.01,"tran37":"245651408527","mcc_id":41,"mccName":"借记卡T1","tranMoney":1},{"tranTime":"2017-06-08 17:08:19","tran2":"6230200022520279","tranFee":0.01,"tran37":"215601498297","mcc_id":41,"mccName":"借记卡T1","tranMoney":1},{"tranTime":"2017-06-07 17:40:26","tran2":"6230200022520279","tranFee":0.01,"tran37":"285601488687","mcc_id":41,"mccName":"借记卡T1","tranMoney":1},{"tranTime":"2017-06-07 17:33:26","tran2":"6230200022520279","tranFee":0.01,"tran37":"205691488557","mcc_id":41,"mccName":"借记卡T1","tranMoney":1},{"tranTime":"2017-06-05 11:54:11","tran2":"6230200022520279","tranFee":0.01,"tran37":"285641447897","mcc_id":41,"mccName":"借记卡T1","tranMoney":1},{"tranTime":"2017-06-01 11:15:27","tran2":"6230200022520279","tranFee":0.08,"tran37":"215681487757","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-06-01 11:14:28","tran2":"6230200022520279","tranFee":0.08,"tran37":"235681487737","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-06-01 11:13:19","tran2":"6230200022520279","tranFee":0.08,"tran37":"275681487717","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-05-31 18:59:39","tran2":"6230200022520279","tranFee":0.08,"tran37":"265681476397","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-05-31 18:54:40","tran2":"6230200022520279","tranFee":0.08,"tran37":"245681476237","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-05-31 17:35:47","tran2":"6230200022520279","tranFee":0.08,"tran37":"285651476327","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-05-31 17:35:05","tran2":"6230200022520279","tranFee":0.08,"tran37":"255651476307","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-05-31 17:33:59","tran2":"6230200022520279","tranFee":0.08,"tran37":"255651476267","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-05-31 17:30:17","tran2":"6230200022520279","tranFee":0.08,"tran37":"275651476097","mcc_id":41,"mccName":"借记卡T1","tranMoney":15},{"tranTime":"2017-05-31 17:26:33","tran2":"6230200022520279","tranFee":0.08,"tran37":"225651476917","mcc_id":41,"mccName":"借记卡T1","tranMoney":15}]
     */

    private String code;
    private String message;
    private boolean isOK;
    private List<ListBean> list;

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

    public boolean isIsOK() {
        return isOK;
    }

    public void setIsOK(boolean isOK) {
        this.isOK = isOK;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * tranTime : 2017-06-26 09:55:13
         * tran2 : 6230200022520279
         * tranFee : 0.08
         * tran37 : 236621449627
         * mcc_id : 41
         * mccName : 借记卡T1
         * tranMoney : 15
         */

        private String tranTime;
        private String tran2;
        private String tranFee;
        private String tran37;
        private String mcc_id;
        private String mccName;
        private String tranMoney;

        public String getTranTime() {
            return tranTime;
        }

        public void setTranTime(String tranTime) {
            this.tranTime = tranTime;
        }

        public String getTran2() {
            return tran2;
        }

        public void setTran2(String tran2) {
            this.tran2 = tran2;
        }

        public String getTranFee() {
            return tranFee;
        }

        public void setTranFee(String tranFee) {
            this.tranFee = tranFee;
        }

        public String getTran37() {
            return tran37;
        }

        public void setTran37(String tran37) {
            this.tran37 = tran37;
        }

        public String getMcc_id() {
            return mcc_id;
        }

        public void setMcc_id(String mcc_id) {
            this.mcc_id = mcc_id;
        }

        public String getMccName() {
            return mccName;
        }

        public void setMccName(String mccName) {
            this.mccName = mccName;
        }

        public String getTranMoney() {
            return tranMoney;
        }

        public void setTranMoney(String tranMoney) {
            this.tranMoney = tranMoney;
        }
    }
}
