package com.example.administrator.smallhappypay.activity.test;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.util.ChartBean;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.qmuiteam.qmui.util.QMUIDisplayHelper;
import com.qmuiteam.qmui.widget.popup.QMUIListPopup;
import com.qmuiteam.qmui.widget.popup.QMUIPopup;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.administrator.smallhappypay.App.getContext;

public class ChartTwoActivity extends BaseActivity {


    @BindView(R.id.chart)
    BarChart barChart;
    @BindView(R.id.charttwo_ll)
    LinearLayout charttwoLl;
    @BindView(R.id.chart_month_tv)
    TextView chartMonthTv;

    private ChartBean chartBean;
    private QMUIListPopup mListPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_two);
        ButterKnife.bind(this);
        chartdata("883000000000105", "2018-07");

    }

    private void zfb() {

        List<BarEntry> entriesGroup1 = new ArrayList<>();
        List<BarEntry> entriesGroup2 = new ArrayList<>();
        List<BarEntry> entriesGroup3 = new ArrayList<>();

        List<ChartBean.MapBean.MonthMapBean.ZfbBeanX> zfbBeans = chartBean.getMap().getMonthMap().getZfb();

        for (int i = 1; i <= getDayOfMonth(); i++) {
            boolean flag = true;
            for (ChartBean.MapBean.MonthMapBean.ZfbBeanX bean : zfbBeans) {
                if (bean.getTimequantum() == i) {
                    entriesGroup1.add(new BarEntry(i+1, (float) bean.getTranmoney()));
                    flag = false;
                    break;
                }
            }
            if (flag) {
                entriesGroup1.add(new BarEntry(i, 0));
            }
        }
        List<ChartBean.MapBean.MonthMapBean.WxBeanX> wxBeans = chartBean.getMap().getMonthMap().getWx();

        for (int i = 1; i <= getDayOfMonth(); i++) {
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

        List<ChartBean.MapBean.MonthMapBean.PosBeanX> posBeans = chartBean.getMap().getMonthMap().getPos();

        for (int i = 1; i <= getDayOfMonth(); i++) {
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

    private void chartdata(String am_number, String month) {
        Api.gethttpService().getReportData(am_number, month).enqueue(new Callback<ChartBean>() {
            @Override
            public void onResponse(Call<ChartBean> call, Response<ChartBean> response) {
                chartBean = response.body();
                zfb();
            }

            @Override
            public void onFailure(Call<ChartBean> call, Throwable t) {

            }
        });
    }

    //java获取当前月的天数
    public int getDayOfMonth() {
        Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
        int day = aCalendar.getActualMaximum(Calendar.DATE);
        return day;
    }


    private void initListPopupIfNeed() {
        if (mListPopup == null) {

            List<Integer> mlist = new ArrayList<Integer>();
            for (int i = 1; i <= 12; i++) {
                mlist.add(i);
            }
            ArrayAdapter adapter = new ArrayAdapter<>(ChartTwoActivity.this, R.layout.simple_list_item, mlist);

            mListPopup = new QMUIListPopup(ChartTwoActivity.this, QMUIPopup.DIRECTION_NONE, adapter);//必须使用本类类名不能getcontext
            mListPopup.create(QMUIDisplayHelper.dp2px(getContext(), 50), QMUIDisplayHelper.dp2px(getContext(), 200), new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    int a=i+1;
                    String b=a < 10 ? "0" + a : a+"";
                    chartMonthTv.setText(i+1+"");

                    chartdata("883000000000105", "2017-"+b);
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


    @OnClick({R.id.chart_month_tv, R.id.charttwo_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.chart_month_tv:
                break;
            case R.id.charttwo_ll:
                initListPopupIfNeed();
                mListPopup.setAnimStyle(QMUIPopup.ANIM_GROW_FROM_CENTER);
                mListPopup.setPreferredDirection(QMUIPopup.DIRECTION_BOTTOM);
                mListPopup.show(view);
                break;
        }
    }
}
