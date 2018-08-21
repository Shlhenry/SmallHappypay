package com.example.administrator.smallhappypay.util;

import java.util.List;

/**
 * Created by Administrator on 2018/7/11.
 */

public class ChartBean {


    /**
     * code : 00
     * message : 成功
     * successMessage : 获取成功
     * isOK : true
     * map : {"toDayMap":{"wx":[{"tranmoney":24,"timequantum":2},{"tranmoney":24,"timequantum":3},{"tranmoney":12,"timequantum":4}],"zfb":[{"tranmoney":22,"timequantum":7},{"tranmoney":20,"timequantum":6},{"tranmoney":12,"timequantum":8}],"pos":[{"tranmoney":34,"timequantum":5},{"tranmoney":30,"timequantum":4},{"tranmoney":17,"timequantum":6},{"tranmoney":12,"timequantum":3}]},"monthMap":{"wx":[{"tranmoney":12896.91,"timequantum":26},{"tranmoney":45,"timequantum":14},{"tranmoney":45,"timequantum":6},{"tranmoney":30,"timequantum":13},{"tranmoney":20.1,"timequantum":8},{"tranmoney":20,"timequantum":7},{"tranmoney":5,"timequantum":11}],"zfb":[{"tranmoney":60,"timequantum":14},{"tranmoney":30,"timequantum":6},{"tranmoney":30,"timequantum":13},{"tranmoney":2.02,"timequantum":11}]},"allMap":{"wx":901,"jointime":446,"zfb":901,"todaywx":93,"todayzfb":93,"todaypos":93,"pos":901}}
     */

    private String code;
    private String message;
    private String successMessage;
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
         * toDayMap : {"wx":[{"tranmoney":24,"timequantum":2},{"tranmoney":24,"timequantum":3},{"tranmoney":12,"timequantum":4}],"zfb":[{"tranmoney":22,"timequantum":7},{"tranmoney":20,"timequantum":6},{"tranmoney":12,"timequantum":8}],"pos":[{"tranmoney":34,"timequantum":5},{"tranmoney":30,"timequantum":4},{"tranmoney":17,"timequantum":6},{"tranmoney":12,"timequantum":3}]}
         * monthMap : {"wx":[{"tranmoney":12896.91,"timequantum":26},{"tranmoney":45,"timequantum":14},{"tranmoney":45,"timequantum":6},{"tranmoney":30,"timequantum":13},{"tranmoney":20.1,"timequantum":8},{"tranmoney":20,"timequantum":7},{"tranmoney":5,"timequantum":11}],"zfb":[{"tranmoney":60,"timequantum":14},{"tranmoney":30,"timequantum":6},{"tranmoney":30,"timequantum":13},{"tranmoney":2.02,"timequantum":11}]}
         * allMap : {"wx":901,"jointime":446,"zfb":901,"todaywx":93,"todayzfb":93,"todaypos":93,"pos":901}
         */

        private ToDayMapBean toDayMap;
        private MonthMapBean monthMap;
        private AllMapBean allMap;

        public ToDayMapBean getToDayMap() {
            return toDayMap;
        }

        public void setToDayMap(ToDayMapBean toDayMap) {
            this.toDayMap = toDayMap;
        }

        public MonthMapBean getMonthMap() {
            return monthMap;
        }

        public void setMonthMap(MonthMapBean monthMap) {
            this.monthMap = monthMap;
        }

        public AllMapBean getAllMap() {
            return allMap;
        }

        public void setAllMap(AllMapBean allMap) {
            this.allMap = allMap;
        }

        public static class ToDayMapBean {
            private List<WxBean> wx;
            private List<ZfbBean> zfb;
            private List<PosBean> pos;

            public List<WxBean> getWx() {
                return wx;
            }

            public void setWx(List<WxBean> wx) {
                this.wx = wx;
            }

            public List<ZfbBean> getZfb() {
                return zfb;
            }

            public void setZfb(List<ZfbBean> zfb) {
                this.zfb = zfb;
            }

            public List<PosBean> getPos() {
                return pos;
            }

            public void setPos(List<PosBean> pos) {
                this.pos = pos;
            }

            public static class WxBean {
                /**
                 * tranmoney : 24
                 * timequantum : 2
                 */

                private double tranmoney;
                private int timequantum;

                public double getTranmoney() {
                    return tranmoney;
                }

                public void setTranmoney(double tranmoney) {
                    this.tranmoney = tranmoney;
                }

                public int getTimequantum() {
                    return timequantum;
                }

                public void setTimequantum(int timequantum) {
                    this.timequantum = timequantum;
                }
            }

            public static class ZfbBean {
                /**
                 * tranmoney : 22
                 * timequantum : 7
                 */

                private double tranmoney;
                private int timequantum;

                public double getTranmoney() {
                    return tranmoney;
                }

                public void setTranmoney(double tranmoney) {
                    this.tranmoney = tranmoney;
                }

                public int getTimequantum() {
                    return timequantum;
                }

                public void setTimequantum(int timequantum) {
                    this.timequantum = timequantum;
                }
            }

            public static class PosBean {
                /**
                 * tranmoney : 34
                 * timequantum : 5
                 */

                private double tranmoney;
                private int timequantum;

                public double getTranmoney() {
                    return tranmoney;
                }

                public void setTranmoney(double tranmoney) {
                    this.tranmoney = tranmoney;
                }

                public int getTimequantum() {
                    return timequantum;
                }

                public void setTimequantum(int timequantum) {
                    this.timequantum = timequantum;
                }
            }
        }

        public static class MonthMapBean {
            private List<WxBeanX> wx;
            private List<ZfbBeanX> zfb;
            private List<PosBeanX> pos;


            public List<WxBeanX> getWx() {
                return wx;
            }

            public void setWx(List<WxBeanX> wx) {
                this.wx = wx;
            }

            public List<ZfbBeanX> getZfb() {
                return zfb;
            }

            public void setZfb(List<ZfbBeanX> zfb) {
                this.zfb = zfb;
            }

            public List<PosBeanX> getPos() {
                return pos;
            }

            public void setPos(List<PosBeanX> pos) {
                this.pos = pos;
            }

            public static class WxBeanX {
                /**
                 * tranmoney : 12896.91
                 * timequantum : 26
                 */

                private double tranmoney;
                private int timequantum;

                public double getTranmoney() {
                    return tranmoney;
                }

                public void setTranmoney(double tranmoney) {
                    this.tranmoney = tranmoney;
                }

                public int getTimequantum() {
                    return timequantum;
                }

                public void setTimequantum(int timequantum) {
                    this.timequantum = timequantum;
                }
            }

            public static class ZfbBeanX {
                /**
                 * tranmoney : 60
                 * timequantum : 14
                 */

                private double tranmoney;
                private int timequantum;

                public double getTranmoney() {
                    return tranmoney;
                }

                public void setTranmoney(double tranmoney) {
                    this.tranmoney = tranmoney;
                }

                public int getTimequantum() {
                    return timequantum;
                }

                public void setTimequantum(int timequantum) {
                    this.timequantum = timequantum;
                }
            }

            public static class PosBeanX {
                /**
                 * tranmoney : 60
                 * timequantum : 14
                 */

                private double tranmoney;
                private int timequantum;

                public double getTranmoney() {
                    return tranmoney;
                }

                public void setTranmoney(double tranmoney) {
                    this.tranmoney = tranmoney;
                }

                public int getTimequantum() {
                    return timequantum;
                }

                public void setTimequantum(int timequantum) {
                    this.timequantum = timequantum;
                }
            }

        }

        public static class AllMapBean {
            /**
             * wx : 901
             * jointime : 446
             * zfb : 901
             * todaywx : 93
             * todayzfb : 93
             * todaypos : 93
             * pos : 901
             */

            private int wx;
            private int jointime;
            private int zfb;
            private int todaywx;
            private int todayzfb;
            private int todaypos;
            private int pos;

            public int getWx() {
                return wx;
            }

            public void setWx(int wx) {
                this.wx = wx;
            }

            public int getJointime() {
                return jointime;
            }

            public void setJointime(int jointime) {
                this.jointime = jointime;
            }

            public int getZfb() {
                return zfb;
            }

            public void setZfb(int zfb) {
                this.zfb = zfb;
            }

            public int getTodaywx() {
                return todaywx;
            }

            public void setTodaywx(int todaywx) {
                this.todaywx = todaywx;
            }

            public int getTodayzfb() {
                return todayzfb;
            }

            public void setTodayzfb(int todayzfb) {
                this.todayzfb = todayzfb;
            }

            public int getTodaypos() {
                return todaypos;
            }

            public void setTodaypos(int todaypos) {
                this.todaypos = todaypos;
            }

            public int getPos() {
                return pos;
            }

            public void setPos(int pos) {
                this.pos = pos;
            }
        }
    }
}
