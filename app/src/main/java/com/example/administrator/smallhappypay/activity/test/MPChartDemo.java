package com.example.administrator.smallhappypay.activity.test;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.widget.TextView;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.util.ChartBean;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 55241 on 2018/7/9.
 */

public class MPChartDemo extends AppCompatActivity{
    @BindView(R.id.chart)
    LineChart mLineChart;

    List<Entry> valsComp1 = new ArrayList<Entry>();
    List<Entry> valsComp2 = new ArrayList<Entry>();

    private ChartBean chartBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        ButterKnife.bind(this);
//        chartdata("883000000000105");

//        Entry c1e1 = new Entry(0f, 100000f); // 一公司第一季度数据
//        valsComp1.add(c1e1);
//        Entry c1e2 = new Entry(1f, 140000f); // 一公司第二季度数据
//        valsComp1.add(c1e2);
//
//
//        Entry c2e1 = new Entry(0f, 130000f); // 二公司第一季度数据
//        valsComp2.add(c2e1);
//        Entry c2e2 = new Entry(1f, 115000f); // 二公司第二季度数据
//        valsComp2.add(c2e2);
//        Entry c2e3 = new Entry(2f, 130000f); // 二公司第一季度数据
//        valsComp2.add(c2e3);
//        Entry c2e4 = new Entry(3f, 115000f); // 二公司第二季度数据
//        valsComp2.add(c2e4);
//        Entry c2e5 = new Entry(4f, 130000f); // 二公司第一季度数据
//        valsComp2.add(c2e5);
//        Entry c2e6 = new Entry(5f, 115000f); // 二公司第二季度数据
//        valsComp2.add(c2e6);
//        Entry c2e7 = new Entry(0f, 130000f); // 二公司第一季度数据
//        valsComp2.add(c2e7);
//        Entry c2e8= new Entry(1f, 115000f); // 二公司第二季度数据
//        valsComp2.add(c2e8);
//
//
//        LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
//        setComp1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2");
//        setComp2.setAxisDependency(YAxis.AxisDependency.LEFT);
//
//        // 使用ILineDataSet接口
//        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
//        dataSets.add(setComp1);
//        dataSets.add(setComp2);
//
//        LineData data = new LineData(dataSets);
//        mLineChart.setData(data);
//        mLineChart.invalidate(); // 刷新
//
//        // 绘制在X轴上的数据数组
//        final String[] quarters = new String[] { "01:00", "03:00", "05:00", "07:00" , "09:00", "12:00",
//                "14:00", "16:00"};
//
//        IAxisValueFormatter formatter = new IAxisValueFormatter() {
//
//            @Override
//            public String getFormattedValue(float value, AxisBase axis) {
//                return quarters[(int) value];
//            }
//        };
//
//        XAxis xAxis = mLineChart.getXAxis();
//        xAxis.setGranularity(1f); // 最小的间隔设置为1
//        xAxis.setValueFormatter(formatter);
//        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);




//
//        List<Integer> listtwo=new ArrayList<>();
//        list.add(11);
//        list.add(15);
//        list.add(14);
//        list.add(12);
//        list.add(11);
//        list.add(12);
//        list.add(16);
//        list.add(17);
////        initLineChart(list);

//        ArrayList<Entry>mEntry=new ArrayList<>();
//        for (int i=0;i<chartBean.getMap().getToDayList().size();i++){
//            ChartBean.MapBean.ToDayListBean Bean=chartBean.getMap().getToDayList().get(i);
//            Entry entry=new Entry(i,Bean.getTimequantum());
//            mEntry.add(i,entry);
//        }


    }

    private void initLineChart(final List<Integer> list)
    {
        //显示边界
        mLineChart.setDrawBorders(true);
        //设置数据
//        List<Entry> entries = new ArrayList<>();
//        for (int i = 0; i < list.size(); i++)
//        {
//            entries.add(new Entry(i, (float) list.get(i)));
//        }

        ArrayList<Entry>mEntry=new ArrayList<>();
        for (int i=0;i<chartBean.getMap().getToDayMap().getWx().size();i++){
            ChartBean.MapBean.ToDayMapBean.WxBean Bean=chartBean.getMap().getToDayMap().getWx().get(i);

            Entry entry=new Entry(i, (float) Bean.getTranmoney());
            mEntry.add(i,entry);
        }

        LineDataSet lineDataSet = new LineDataSet(mEntry, "");
        //线颜色
        lineDataSet.setColor(Color.parseColor("#1E4C15"));
        //线宽度
        lineDataSet.setLineWidth(1.6f);
        //显示圆点
        lineDataSet.setDrawCircles(true);

        lineDataSet.setMode(LineDataSet.Mode.LINEAR);
        //设置折线图填充
//        lineDataSet.setDrawFilled(true);
        LineData data = new LineData(lineDataSet);
        //无数据时显示的文字
        mLineChart.setNoDataText("暂无数据");
        //折线图显示数值
        data.setDrawValues(true);
        //折线图显示数值颜色
        data.setValueTextColor(Color.parseColor("#ffffff"));
        //得到X轴
        XAxis xAxis = mLineChart.getXAxis();
        //设置X轴的位置（默认在上方)
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        xAxis.setGranularity(0.5f);
        //设置X轴的刻度数量，第二个参数为true
        xAxis.setLabelCount( list.size(), true);
        //设置坐标轴线条颜色
        xAxis.setAxisLineColor(Color.parseColor("#ff0000"));
        //设置X轴的值（最小值、最大值）
        xAxis.setAxisMinimum(0.5f);
        xAxis.setAxisMaximum(7);//下标小于长度
        //显示网格线
        xAxis.setDrawGridLines(true);
        //X轴字颜色
        xAxis.setTextColor(Color.parseColor("#fdfdfd"));
        // 标签倾斜
        xAxis.setLabelRotationAngle(0);

        final String[] quarters = new String[] { "00:00", "03:00", "06:00", "09:00" , "12:00", "15:00","18:00", "21:00"};

        //设置X轴值为字符串
        xAxis.setValueFormatter(new IAxisValueFormatter()
        {
            @Override
            public String getFormattedValue(float value, AxisBase axis)
            {
//                int IValue = (int) value;
//                CharSequence format = DateFormat.format("MM/dd",
//                        System.currentTimeMillis()-(long)(list.size()-IValue)*24*60*60*1000);

                return quarters[(int) value];
            }
        });


        //得到Y轴
        YAxis yAxis = mLineChart.getAxisLeft();
        YAxis rightYAxis = mLineChart.getAxisRight();
        //设置Y轴是否显示
        rightYAxis.setEnabled(false); //右侧Y轴不显示
        //显示网格线
        yAxis.setDrawGridLines(true);
        //设置Y轴坐标之间的最小间隔
        yAxis.setGranularity(1);
        //设置坐标轴线条颜色
        yAxis.setAxisLineColor(Color.parseColor("#ffffff"));
        //Y轴字颜色
        yAxis.setTextColor(Color.parseColor("#fdfdfd"));
        //设置y轴的刻度数量
        yAxis.setLabelCount(Collections.max(list) + 2, false);
        //设置从Y轴值
        yAxis.setAxisMinimum(0f);

        yAxis.setAxisMaximum(Collections.max(list) + 1);

        //y轴
        yAxis.setValueFormatter(new IAxisValueFormatter()
        {
            @Override
            public String getFormattedValue(float value, AxisBase axis)
            {
                DecimalFormat decimalFormat=new DecimalFormat("000,0");
                int IValue = (int) value;
                return String.valueOf(IValue)+"万";
            }
        });
        //图例：得到Lengend
        Legend legend = mLineChart.getLegend();
        //隐藏Lengend
        legend.setEnabled(false);
        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);
        //折线图点的标记
        MyMarkerView mv = new MyMarkerView(this);
        mLineChart.setMarker(mv);
        //设置数据
        mLineChart.setData(data);
        //图标刷新
        mLineChart.invalidate();
    }

    public class MyMarkerView extends MarkerView
    {

        private TextView tvContent;
        private DecimalFormat format = new DecimalFormat("##0");

        public MyMarkerView(Context context) {
            super(context, R.layout.custom_marker_view);//这个布局自己定义
            tvContent = (TextView) findViewById(R.id.tvContent);
        }

        //显示的内容
        @Override
        public void refreshContent(Entry e, Highlight highlight) {
            tvContent.setText(format(e.getX())+"\n"+format.format(e.getY())+"元");
            super.refreshContent(e, highlight);
        }

        //标记相对于折线图的偏移量
        @Override
        public MPPointF getOffset() {
            return new MPPointF(-(getWidth() / 2), -getHeight());
        }

        //时间格式化（显示今日往前30天的每一天日期）
        public String  format(float x)
        {
            CharSequence format = DateFormat.format("MM月dd日",
                    System.currentTimeMillis()-(long) (30-(int)x)*24*60*60*1000);
            return format.toString();
        }
    }

//    private void chartdata(String am_number){
//        Api.gethttpService().getReportData(am_number).enqueue(new Callback<ChartBean>() {
//            @Override
//            public void onResponse(Call<ChartBean> call, Response<ChartBean> response) {
//                chartBean=response.body();
//
//                List<Integer> list=new ArrayList<>();
//
//                for (int i=0;i<chartBean.getMap().getToDayMap().getWx().size();i++){
//                    list.add(chartBean.getMap().getToDayMap().getWx().get(i).getTranmoney());
//                    list.add(chartBean.getMap().getToDayMap().getWx().get(i).getTranmoney()+10);
//                }
//                initLineChart(list);
//            }
//
//            @Override
//            public void onFailure(Call<ChartBean> call, Throwable t) {
//
//            }
//        });
//    }

}
