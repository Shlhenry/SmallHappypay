package com.example.administrator.smallhappypay.activity.water;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.andview.refreshview.XRefreshView;
import com.example.administrator.smallhappypay.BaseActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.adapter.WaterAdapter;
import com.example.administrator.smallhappypay.net.Api;
import com.example.administrator.smallhappypay.tool.SPUtils;
import com.example.administrator.smallhappypay.util.WaterBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WechatWaterFragment extends Fragment {

    @BindView(R.id.wechatwater_lv)
    ListView wechatwaterLv;
    @BindView(R.id.union_view)
    XRefreshView unionView;
    Unbinder unbinder;
    private View view;

    private int curpage = 1;
    private String amNumber;
    private int datebase = 0;//判断刷新
    private String count = "20";

    private WaterBean waterBean;
    private WaterAdapter waterAdapter;
    List<WaterBean.ListBean> tranList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_wechat_water_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        amNumber = SPUtils.getString(getActivity(), "amNumber");

        getposwater(0, amNumber, "wx", count);
        initRefresh();
        return view;
    }

    private void initRefresh() {
        unionView.setPinnedTime(1000);
        unionView.setMoveForHorizontal(true);
        unionView.setPullLoadEnable(true);
        unionView.setAutoLoadMore(false);

        unionView.enableReleaseToLoadMore(true);
        unionView.enableRecyclerViewPullUp(true);
        unionView.enablePullUpWhenLoadCompleted(true);
        //设置静默加载时提前加载的item个数
        unionView.setPreLoadCount(20);

        unionView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getposwater(0, amNumber, "wx", count);
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        getposwater(1, amNumber, "wx", count);
                    }
                }, 2000);
            }
        });
    }

    private void getposwater(final int loadtype, String amNumber, String mode, String count) {
        //loadtype 0为刷新 1为加载更多
        if (loadtype == 0) {
            curpage = 1;
        }
        Api.gethttpService().getWaterData(amNumber, mode, curpage, count).enqueue(new Callback<WaterBean>() {
            @Override
            public void onResponse(Call<WaterBean> call, Response<WaterBean> response) {
                if ("00".equals(response.body().getCode())) {
                    if (response.body().getList() != null) {
                        waterBean = response.body();
                        curpage++;
                        if (loadtype == 0) {
                            //初次加载、或者刷新
                            tranList.clear();
                            tranList.addAll(waterBean.getList());
                            initView();
                        } else {
                            tranList.addAll(waterBean.getList());
                            waterAdapter.notifyDataSetChanged();
                        }
                        datebase = 1;
                    } else {
                        datebase = 0;
                        BaseActivity.mdialog(getActivity(), "已加载全部数据");
                    }
                } else if ("99".equals(response.body().getCode())) {
                    BaseActivity.mdialog(getActivity(), response.body().getMessage());
                } else {
                    return;
                }
                unionView.stopRefresh();
                unionView.stopLoadMore();

            }

            @Override
            public void onFailure(Call<WaterBean> call, Throwable t) {
                BaseActivity.mdialog(getActivity(),"服务器维护中");
            }
        });
    }

    private void initView() {
        waterAdapter = new WaterAdapter(getActivity(), tranList);
        wechatwaterLv.setAdapter(waterAdapter);
        waterAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
