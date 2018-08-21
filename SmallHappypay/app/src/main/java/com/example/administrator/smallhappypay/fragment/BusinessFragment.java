package com.example.administrator.smallhappypay.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.activity.BankInfoActivity;
import com.example.administrator.smallhappypay.activity.ChartActivity;
import com.example.administrator.smallhappypay.activity.MachineAllInfoActivity;
import com.example.administrator.smallhappypay.activity.MccRateActivity;
import com.example.administrator.smallhappypay.activity.PayProductActivity;
import com.example.administrator.smallhappypay.activity.WaterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class BusinessFragment extends Fragment {

    @BindView(R.id.business_mccrate_ll)
    LinearLayout businessMccrateLl;
    Unbinder unbinder;
    @BindView(R.id.business_water_ll)
    LinearLayout businessWaterLl;
    @BindView(R.id.business_bankinfo_ll)
    LinearLayout businessBankinfoLl;
    @BindView(R.id.business_machineinfo_ll)
    LinearLayout businessMachineinfoLl;
    @BindView(R.id.business_payproduct_ll)
    LinearLayout businessPayproductLl;
    @BindView(R.id.business_chart_ll)
    LinearLayout businessChartLl;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_businessfragment, container, false);
        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.business_mccrate_ll, R.id.business_water_ll, R.id.business_bankinfo_ll, R.id.business_machineinfo_ll, R.id.business_payproduct_ll,R.id.business_chart_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.business_mccrate_ll:
                startActivity(new Intent(getActivity(), MccRateActivity.class));
                break;
            case R.id.business_water_ll:
                startActivity(new Intent(getActivity(), WaterActivity.class));
                break;
            case R.id.business_bankinfo_ll:
                startActivity(new Intent(getActivity(), BankInfoActivity.class));
                break;
            case R.id.business_machineinfo_ll:
                startActivity(new Intent(getActivity(), MachineAllInfoActivity.class));
                break;
            case R.id.business_payproduct_ll:
                startActivity(new Intent(getActivity(), PayProductActivity.class));
                break;
            case R.id.business_chart_ll:
                startActivity(new Intent(getActivity(), ChartActivity.class));
                break;
        }
    }
}
