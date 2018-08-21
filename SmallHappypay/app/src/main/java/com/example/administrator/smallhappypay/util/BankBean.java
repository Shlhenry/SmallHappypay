package com.example.administrator.smallhappypay.util;

import java.util.List;

/**
 * Created by Administrator on 2018/6/25.
 */

public class BankBean {

    /**
     * code : 00
     * message : 成功
     * successMessage : 操作成功
     * isOK : true
     * list : [{"bankName":"宁波银行股份有限公司","bankSimpleName":"宁波银行","bankId":30},{"bankName":"安徽长丰农村商业银行","bankSimpleName":"安徽长丰农村商业银行","bankId":31},{"bankName":"广州农村商业银行股份有限公司","bankSimpleName":"广州农村商业银行股份有限公司","bankId":33},{"bankName":"广东发展银行","bankSimpleName":"广东发展银行","bankId":1},{"bankName":"华夏银行股份有限公司","bankSimpleName":"华夏银行","bankId":2},{"bankName":"浙江泰隆商业银行","bankSimpleName":"浙江泰隆商业银行","bankId":3},{"bankName":"杭州银行","bankSimpleName":"杭州银行","bankId":4},{"bankName":"平安银行股份有限公司","bankSimpleName":"平安银行","bankId":5},{"bankName":"青岛银行股份有限公司","bankSimpleName":"青岛银行","bankId":6},{"bankName":"浙江省农村信用社联合社","bankSimpleName":"浙江省农村信用社联合社","bankId":7},{"bankName":"龙江银行","bankSimpleName":"龙江银行","bankId":9},{"bankName":"中国民生银行","bankSimpleName":"民生银行","bankId":10},{"bankName":"光大银行","bankSimpleName":"光大银行","bankId":11},{"bankName":"哈尔滨银行","bankSimpleName":"哈尔滨银行","bankId":12},{"bankName":"中国建设银行股份有限公司总行","bankSimpleName":"建设银行","bankId":13},{"bankName":"中国农业银行股份有限公司","bankSimpleName":"农业银行","bankId":14},{"bankName":"招商银行股份有限公司","bankSimpleName":"招商银行","bankId":15},{"bankName":"北京银行","bankSimpleName":"北京银行","bankId":16},{"bankName":"交通银行","bankSimpleName":"交通银行","bankId":17},{"bankName":"中国银行总行","bankSimpleName":"中国银行","bankId":18},{"bankName":"兴业银行总行","bankSimpleName":"兴业银行","bankId":19},{"bankName":"中信银行股份有限公司","bankSimpleName":"中信银行","bankId":20},{"bankName":"浦发银行","bankSimpleName":"浦发银行","bankId":21},{"bankName":"中国邮政储蓄银行有限责任公司","bankSimpleName":"邮政储蓄银行","bankId":23},{"bankName":"中国工商银行股份有限公司","bankSimpleName":"工商银行","bankId":24},{"bankName":"利津舜丰村镇银行","bankSimpleName":"利津舜丰村镇银行","bankId":25},{"bankName":"杭州联合农村商业银行股份有限公司","bankSimpleName":"杭州联合农村商业银行","bankId":26},{"bankName":"恒丰银行","bankSimpleName":"恒丰银行","bankId":27},{"bankName":"浙江民泰商业银行股份有限公司","bankSimpleName":"浙江民泰商业银行","bankId":28},{"bankName":"北京农村商业银行","bankSimpleName":"北京农村商业银行","bankId":32},{"bankName":"深圳发展银行","bankSimpleName":"深圳发展银行","bankId":36},{"bankName":"江苏银行股份有限公司","bankSimpleName":"江苏银行","bankId":38},{"bankName":"上海银行","bankSimpleName":"上海银行","bankId":35},{"bankName":"珠海华润银行股份有限公司","bankSimpleName":"华润银行","bankId":39},{"bankName":"深圳福田银座村镇银行股份有限公司","bankSimpleName":"深圳福田银座村镇银行","bankId":29},{"bankName":"广州市商业银行","bankSimpleName":"广州市商业银行","bankId":34},{"bankName":"郑州银行","bankSimpleName":"郑州银行","bankId":37}]
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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * bankName : 宁波银行股份有限公司
         * bankSimpleName : 宁波银行
         * bankId : 30
         */

        private String bankName;
        private String bankSimpleName;
        private int bankId;

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getBankSimpleName() {
            return bankSimpleName;
        }

        public void setBankSimpleName(String bankSimpleName) {
            this.bankSimpleName = bankSimpleName;
        }

        public int getBankId() {
            return bankId;
        }

        public void setBankId(int bankId) {
            this.bankId = bankId;
        }
    }
}
