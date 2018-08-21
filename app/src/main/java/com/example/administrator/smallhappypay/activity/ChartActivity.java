package com.example.administrator.smallhappypay.activity;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.util.ChartBean;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.MPPointF;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.QMUIProgressBar;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.lang.ref.WeakReference;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.administrator.smallhappypay.App.getContext;

public class ChartActivity extends BaseActivity {

    @BindView(R.id.linechart)
    LineChart mLineChart;

    List<Entry> valsComp1 = new ArrayList<Entry>();
    List<Entry> valsComp2 = new ArrayList<Entry>();
    List<Entry> valsComp3 = new ArrayList<Entry>();

    // 使用ILineDataSet接口
    List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();

    @BindView(R.id.toolbar_back_ll)
    LinearLayout toolbarBackLl;
    @BindView(R.id.toolbar_title_tv)
    TextView toolbarTitleTv;
    @BindView(R.id.posProgressBar)
    QMUIProgressBar posProgressBar;
    @BindView(R.id.chart_allpos_tv)
    TextView chartAllposTv;
    @BindView(R.id.chart_allwechat_tx)
    TextView chartAllwechatTx;
    @BindView(R.id.wechatProgressBar)
    QMUIProgressBar wechatProgressBar;
    @BindView(R.id.chart_alipay_tx)
    TextView chartAlipayTx;
    @BindView(R.id.alipayProgressBar)
    QMUIProgressBar alipayProgressBar;
    @BindView(R.id.chart_month_tv)
    TextView chartMonthTv;
    @BindView(R.id.charttwo_ll)
    LinearLayout charttwoLl;
    @BindView(R.id.barchart)
    BarChart barChart;
    @BindView(R.id.chart_amnumbertime_tv)
    TextView chartAmnumbertimeTv;

    private ChartBean chartBean;

    protected static final int STOP = 0x10000;
    protected static final int NEXT = 0x10001;

    private ProgressHandler myHandler = new ProgressHandler();

    private QMUIListPopup mListPopup;
    private String amNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);
        toolbarTitleTv.setText("交易报表");
        chartdata("883000000000105", "2017-06");

    }

    private void chartdata(String am_number, String month) {
        Api.gethttpService().getReportData(am_number, month).enqueue(new Callback<ChartBean>() {
            @Override
            public void onResponse(Call<ChartBean> call, Response<ChartBean> response) {
                chartBean = response.body();
                chartAmnumbertimeTv.setText("商户号:"+amNumber+"(已入网"+chartBean.getMap().getAllMap().getJointime()+"天)");

                //条形图的代码
                zfb();
                //进度条数据
                setbardata();

                /*
                * 折线图数据代码
                * */

                //微信折线图代码
                if (chartBean.getMap().getToDayMap().getWx() != null) {
                    List<ChartBean.MapBean.ToDayMapBean.WxBean> wxBeans = chartBean.getMap().getToDayMap().getWx();
                    for (int i = 0; i < 8; i++) {
                        boolean flag = true;
                        for (ChartBean.MapBean.ToDayMapBean.WxBean bean : wxBeans) {
                            if (bean.getTimequantum() - 1 == i) {
                                Entry entry = new Entry(i, (float) bean.getTranmoney());
                                valsComp1.add(i, entry);
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            Entry entry = new Entry(i, 0);
                            valsComp1.add(i, entry);
                        }
                    }
                    LineDataSet setComp1 = new LineDataSet(valsComp1, "微信交易");
                    setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
                    setComp1.setCircleColor(Color.parseColor("#19d100"));//设置小圆点的颜色
                    setComp1.setColor(Color.parseColor("#19d100"));
                    setComp1.setValueTextColor(Color.parseColor("#ffffff"));
                    setComp1.setHighlightEnabled(true); // 是否允许高亮
                    setComp1.setDrawHighlightIndicators(true); // 设置是否有拖拽高亮指示器
                    setComp1.setHighLightColor(Color.parseColor("#19d100"));

                    dataSets.add(setComp1);//填充数据

                } else {
                    Log.w("+++", "微信为空");
                }
                //支付宝折线图代码
                if (chartBean.getMap().getToDayMap().getZfb() != null) {
                    List<ChartBean.MapBean.ToDayMapBean.ZfbBean> zfbBeans = chartBean.getMap().getToDayMap().getZfb();
                    for (int i = 0; i < 8; i++) {
                        boolean flag = true;
                        for (ChartBean.MapBean.ToDayMapBean.ZfbBean bean : zfbBeans) {
                            if (bean.getTimequantum() - 1 == i) {
                                Entry entry = new Entry(i, (float) bean.getTranmoney());
                                valsComp2.add(i, entry);
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            Entry entry = new Entry(i, 0);
                            valsComp2.add(i, entry);
                        }
                    }
                    LineDataSet setComp2 = new LineDataSet(valsComp2, "支付宝交易");
                    setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
                    setComp2.setCircleColor(Color.parseColor("#039feb"));//设置小圆点的颜色
                    setComp2.setColor(Color.parseColor("#039feb"));
                    setComp2.setValueTextColor(Color.parseColor("#ffffff"));
                    setComp2.setHighlightEnabled(true); // 是否允许高亮
                    setComp2.setDrawHighlightIndicators(true); // 设置是否有拖拽高亮指示器
                    setComp2.setHighLightColor(Color.parseColor("#039feb"));

                    dataSets.add(setComp2);//填充数据

                } else {
                    Log.w("+++", "支付宝为空");
                }
                //POS折线图代码
                if (chartBean.getMap().getToDayMap().getPos() != null) {
                    List<ChartBean.MapBean.ToDayMapBean.PosBean> posBeans = chartBean.getMap().getToDayMap().getPos();
                    for (int i = 0; i < 8; i++) {
                        boolean flag = true;
                        for (ChartBean.MapBean.ToDayMapBean.PosBean bean : posBeans) {
                            if (bean.getTimequantum() - 1 == i) {
                                Entry entry = new Entry(i, (float) bean.getTranmoney());
                                valsComp3.add(i, entry);
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            Entry entry = new Entry(i, 0);
                            valsComp3.add(i, entry);
                        }
                    }
                    LineDataSet setComp3 = new LineDataSet(valsComp3, "POS机交易");
                    setComp3.setCircleColor(Color.parseColor("#f7b43b"));//设置小圆点的颜色
                    setComp3.setAxisDependency(YAxis.AxisDependency.LEFT);
                    setComp3.setColor(Color.parseColor("#f7b43b"));
                    setComp3.setValueTextColor(Color.parseColor("#ffffff"));
                    setComp3.setHighlightEnabled(true); // 是否允许高亮
                    setComp3.setDrawHighlightIndicators(true); // 设置是否有拖拽高亮指示器
                    setComp3.setHighLightColor(Color.parseColor("#f7b43b"));

                    dataSets.add(setComp3);//填充数据

                } else {
                    Log.w("+++", "POS为空");
                }

                //折线图点的标记
                MyMarkerView mv = new MyMarkerView(ChartActivity.this);
                mLineChart.setMarker(mv);

                LineData data = new LineData(dataSets);
                mLineChart.setData(data);
                mLineChart.invalidate(); // 刷新

                // 绘制在X轴上的数据数组
                final String[] quarters = new String[]{"00:00", "03:00", "06:00", "09:00", "12:00", "15:00", "18:00", "21:00"};

                IAxisValueFormatter formatter = new IAxisValueFormatter() {

                    @Override
                    public String getFormattedValue(float value, AxisBase axis) {
                        return quarters[(int) value];
                    }
                };

                //描述文字的颜色
                Legend mLegend = mLineChart.getLegend(); // 设置坐标线描述?? 的样式
                mLegend.setEnabled(false);//坐标线开关是否显示
                mLegend.setTextColor(Color.parseColor("#ffffff"));// 坐标线描述文字的颜色
                mLegend.setTextSize(8);// 坐标线描述文字的大小，单位dp

                XAxis xAxis = mLineChart.getXAxis();
                xAxis.setGranularity(1f); // 最小的间隔设置为1
                xAxis.setAxisMaximum(7);
                xAxis.setValueFormatter(formatter);
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                xAxis.setAxisLineColor(Color.parseColor("#1affffff"));//网格线颜色
                //X轴字颜色
                xAxis.setTextColor(Color.parseColor("#fdfdfd"));
                //设置坐标轴线条颜色
                xAxis.setAxisLineColor(Color.parseColor("#ffffff"));

                //得到Y轴
                YAxis yAxis = mLineChart.getAxisLeft();
                //设置坐标轴线条颜色
                yAxis.setAxisLineColor(Color.parseColor("#ffffff"));
                yAxis.setGridColor(Color.parseColor("#1affffff"));//网格线颜色
                //Y轴字颜色
                yAxis.setTextColor(Color.parseColor("#fdfdfd"));
                //设置从Y轴值
                yAxis.setAxisMinimum(0f);
                ////设置从Y轴间隔值
                yAxis.setLabelCount(10, true);
                YAxis rightYAxis = mLineChart.getAxisRight();
                //设置Y轴是否显示
                rightYAxis.setEnabled(false); //右侧Y轴不显示
            }
            @Override
            public void onFailure(Call<ChartBean> call, Throwable t) {
                mdialog(ChartActivity.this, "服务器维护中");
                Log.e("/*/*/*",t.getMessage()+"");
            }
        });
    }

    @OnClick(R.id.charttwo_ll)
    public void onViewClicked(View view) {
        initListPopupIfNeed();
        mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
        mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_BOTTOM);
        mListPopup.show(view);
    }

    public class MyMarkerView extends MarkerView {
        private TextView tvContent;
        private DecimalFormat format = new DecimalFormat("##0");

        public MyMarkerView(Context context) {
            super(context, R.layout.custom_marker_view);//这个布局自己定义
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        //显示的内容
        @Override
        public void refreshContent(Entry e, Highlight highlight) {
//            tvContent.setText(format(e.getX())+"\n"+format.format(e.getY())+"元");
            tvContent.setText(format.format(e.getY()) + "元");
            super.refreshContent(e, highlight);
        }

        //标记相对于折线图的偏移量
        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }

        //时间格式化（显示今日往前30天的每一天日期）
        public String format(float x) {
            CharSequence format = DateFormat.format("MM月dd日",
                    System.currentTimeMillis() - (long) (30 - (int) x) * 24 * 60 * 60 * 1000);
            return format.toString();
        }
    }

    private static class ProgressHandler extends Handler {
        private WeakReference<QMUIProgressBar> weakRectProgressBar;
        private WeakReference<QMUIProgressBar> weakRectProgressBarwechat;
        private WeakReference<QMUIProgressBar> weakRectProgressBaralipay;

        void setProgressBar(QMUIProgressBar rectProgressBar) {
            weakRectProgressBar = new WeakReference<>(rectProgressBar);
        }

        void setwechatProgressBar(QMUIProgressBar rectProgressBar) {
            weakRectProgressBarwechat = new WeakReference<>(rectProgressBar);
        }

        void setalipayProgressBar(QMUIProgressBar rectProgressBar) {
            weakRectProgressBaralipay = new WeakReference<>(rectProgressBar);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case STOP:
                    break;
                case NEXT:
                    if (!Thread.currentThread().isInterrupted()) {
                        if (weakRectProgressBar.get() != null) {
                            weakRectProgressBar.get().setProgress(msg.arg1);
                            weakRectProgressBarwechat.get().setProgress(msg.arg1);
                            weakRectProgressBaralipay.get().setProgress(msg.arg1);
                        }
                    }
            }
        }
    }

    private void zfb() {

        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        List<BarEntry> entriesGroup3 = new ArrayList<>();

        if (chartBean.getMap().getMonthMap().getZfb() != null) {
            List<ChartBean.MapBean.MonthMapBean.ZfbBeanX> zfbBeans = chartBean.getMap().getMonthMap().getZfb();
            for (int i = 0; i < getDayOfMonth(); i++) {
                boolean flag = true;
                for (ChartBean.MapBean.MonthMapBean.ZfbBeanX bean : zfbBeans) {
                    if (bean.getTimequantum() == i) {

                        entriesGroup1.add(new BarEntry(i, (float) bean.getTranmoney()));
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    entriesGroup1.add(new BarEntry(i, 0));
                }
            }
        }

        if (chartBean.getMap().getMonthMap().getWx() != null) {
            List<ChartBean.MapBean.MonthMapBean.WxBeanX> wxBeans = chartBean.getMap().getMonthMap().getWx();
            for (int i = 0; i < getDayOfMonth(); i++) {
                boolean flag = true;
                for (ChartBean.MapBean.MonthMapBean.WxBeanX bean : wxBeans) {
                    if (bean.getTimequantum() == i) {

                        entriesGroup2.add(new BarEntry(i, (float) bean.getTranmoney()));
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    entriesGroup2.add(new BarEntry(i, 0));
                }
            }
        }
        if (chartBean.getMap().getMonthMap().getPos() != null) {
            List<ChartBean.MapBean.MonthMapBean.PosBeanX> posBeans = chartBean.getMap().getMonthMap().getPos();
            for (int i = 0; i < getDayOfMonth(); i++) {
                boolean flag = true;
                for (ChartBean.MapBean.MonthMapBean.PosBeanX bean : posBeans) {
                    if (bean.getTimequantum() == i) {

                        entriesGroup3.add(new BarEntry(i, (float) bean.getTranmoney()));
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    entriesGroup3.add(new BarEntry(i, 0));
                }
            }
        }
        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setTextSize(12f); // 设置字体大小
        yAxis.setAxisMinimum(0f); // 设置坐标轴从0开始
        yAxis.setTextColor(Color.RED); // 设置字体颜色为黑色
//        yAxis.setAxisMaximum(100f);
        yAxis.setGranularity(1f); // 设置间隔为1
        yAxis.setLabelCount(10, true); // 设置标签个数
        //设置坐标轴线条颜色
        yAxis.setAxisLineColor(Color.parseColor("#ffffff"));
        yAxis.setGridColor(Color.parseColor("#1affffff"));//网格线颜色
        //Y轴字颜色
        yAxis.setTextColor(Color.parseColor("#fdfdfd"));
        YAxis rightYAxis = barChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        yAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return (int) value + "元";
            }
        });


        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(true);
        xAxis.setDrawGridLines(false);
        xAxis.setLabelCount(10);
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setAxisLineColor(Color.parseColor("#1affffff"));//网格线颜色
        //X轴字颜色
        xAxis.setTextColor(Color.parseColor("#fdfdfd"));
        //设置坐标轴线条颜色
        xAxis.setAxisLineColor(Color.parseColor("#ffffff"));

        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {

                return (int) value + "号";
            }
        });


        //两组数据
        BarDataSet set1 = new BarDataSet(entriesGroup1, "支付宝交易");
        BarDataSet set2 = new BarDataSet(entriesGroup2, "微信交易");
        BarDataSet set3 = new BarDataSet(entriesGroup3, "POS交易");

        set1.setColor(GradientDrawable.LINEAR_GRADIENT);
        set1.setColor(Color.parseColor("#039feb"));
        set1.setValueTextColor(Color.parseColor("#ffffff"));
        set2.setColor(Color.parseColor("#19d100"));
        set2.setValueTextColor(Color.parseColor("#ffffff"));
        set3.setColor(Color.parseColor("#f7b43b"));
        set3.setValueTextColor(Color.parseColor("#ffffff"));

        //描述文字的颜色
        Legend mLegend = barChart.getLegend(); // 设置坐标线描述?? 的样式
        mLegend.setEnabled(false);//坐标线开关是否显示
        mLegend.setTextColor(Color.parseColor("#ffffff"));// 坐标线描述文字的颜色
        mLegend.setTextSize(8);// 坐标线描述文字的大小，单位dp

        int amount = 3;//条形图的组数三组

        float groupSpace = 0.12f; //柱状图组之间的间距
        float barSpace = (float) ((1 - 0.12) / amount / 10); // x4 DataSet
        float barWidth = (float) ((1 - 0.12) / amount / 10 * 9); // x4 DataSet

//        xAxis.setLabelCount(getDayOfMonth() , false);

        BarData data = new BarData(set1, set2, set3); //设置组数据
        data.setBarWidth(barWidth); // 设置柱状条宽度
        barChart.setData(data);
        barChart.groupBars(1f, groupSpace, barSpace); // 设置组样式宽度等
        barChart.invalidate(); // 刷新

    }

    //java获取当前月的天数
    public int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }

    //条形图选择月份
    private void initListPopupIfNeed() {
        if (mListPopup == null) {

            List<Integer> mlist = new ArrayList<Integer>();
            for (int i = 1; i <= 12; i++) {
                mlist.add(i);
            }
            ArrayAdapter adapter = new ArrayAdapter<>(ChartActivity.this, R.layout.simple_list_item, mlist);

            mListPopup = new QMUIListPopup(ChartActivity.this, QMUIPopup.DIRECTION_NONE, adapter);//必须使用本类类名不能getcontext
            mListPopup.create(QMUIDisplayHelper.dp2px(getContext(), 50), QMUIDisplayHelper.dp2px(getContext(), 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int a = i + 1;
                    String b = a < 10 ? "0" + a : a + "";
                    chartMonthTv.setText(i + 1 + "");

                    chartdata("883000000000105", "2017-" + b);
                    mListPopup.dismiss();
                }
            });
            mListPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                }
            });
        }
    }

    //进度条数据
    private void setbardata(){
        if (chartBean.getMap().getAllMap() != null) {
            chartAllwechatTx.setText(chartBean.getMap().getAllMap().getWx() + "元");
            chartAlipayTx.setText(chartBean.getMap().getAllMap().getZfb() + "元");
            chartAllposTv.setText(chartBean.getMap().getAllMap().getPos() + "元");
        }

        //微信进度条数据
        if (chartBean.getMap().getAllMap().getTodaywx() != 0) {
            int wechatprogress = chartBean.getMap().getAllMap().getTodaywx() * 100 / chartBean.getMap().getAllMap().getWx();
            if (wechatprogress > 0) {
                Message msg = new Message();
                msg.what = NEXT;
                msg.arg1 = wechatprogress;
                myHandler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = NEXT;
                msg.arg1 = 1;
                myHandler.sendMessage(msg);
            }
            wechatProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
                @Override
                public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                    return "今日交易" + chartBean.getMap().getAllMap().getTodaywx() + "元";
                }
            });

            wechatProgressBar.setTextSize(30);
            myHandler.setwechatProgressBar(wechatProgressBar);
        }
        //支付宝进度条数据
        if (chartBean.getMap().getAllMap().getTodayzfb() != 0) {
            int alipayprogress = chartBean.getMap().getAllMap().getTodayzfb() * 100 / chartBean.getMap().getAllMap().getZfb();
            if (alipayprogress > 0) {
                Message msg = new Message();
                msg.what = NEXT;
                msg.arg1 = alipayprogress;
                myHandler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = NEXT;
                msg.arg1 = 1;
                myHandler.sendMessage(msg);
            }
            alipayProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
                @Override
                public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                    return "今日交易" + chartBean.getMap().getAllMap().getTodayzfb() + "元";
                }
            });

            alipayProgressBar.setTextSize(30);
            myHandler.setalipayProgressBar(alipayProgressBar);
        }
        //pos进度条数据
        if (chartBean.getMap().getAllMap().getTodaypos() != 0) {
            int posprogress = chartBean.getMap().getAllMap().getTodaypos() * 100 / chartBean.getMap().getAllMap().getPos();
            if (posprogress > 0) {
                Message msg = new Message();
                msg.what = NEXT;
                msg.arg1 = posprogress;
                myHandler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = NEXT;
                msg.arg1 = 1;
                myHandler.sendMessage(msg);
            }

            posProgressBar.setTextSize(30);
            posProgressBar.setQMUIProgressBarTextGenerator(new QMUIProgressBar.QMUIProgressBarTextGenerator() {
                @Override
                public String generateText(QMUIProgressBar progressBar, int value, int maxValue) {
                    return "今日交易" + chartBean.getMap().getAllMap().getTodayzfb() + "元";
                }
            });
            myHandler.setProgressBar(posProgressBar);
        }
    }



}

