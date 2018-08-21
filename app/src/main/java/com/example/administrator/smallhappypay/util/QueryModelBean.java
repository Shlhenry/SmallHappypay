package com.example.administrator.smallhappypay.util;

import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class QueryModelBean {

    /**
     * code : 00
     * message : 成功
     * isOK : true
     * list : [{"id":"21","remark":"类型","name":"8110","type":"1"},{"id":"1","remark":"类型","name":"H80","type":"1"},{"id":"41","remark":"类型","name":"M60","type":"1"},{"id":"2","remark":"类型","name":"S98","type":"1"},{"id":"3","remark":"类型","name":"S58","type":"1"},{"id":"4","remark":"类型","name":"A500","type":"1"},{"id":"5","remark":"类型","name":"E330","type":"1"},{"id":"6","remark":"类型","name":"E550","type":"1"},{"id":"7","remark":"类型","name":"ME31","type":"1"}]
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
         * id : 21
         * remark : 类型
         * name : 8110
         * type : 1
         */

        private String id;
        private String remark;
        private String name;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
