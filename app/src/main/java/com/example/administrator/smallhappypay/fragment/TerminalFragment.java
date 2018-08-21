package com.example.administrator.smallhappypay.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.administrator.smallhappypay.activity.UnbindThreeActivity;
import com.example.administrator.smallhappypay.activity.UnbindTwoActivity;
import com.example.administrator.smallhappypay.activity.binding.MachineBindingOneActivity;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.activity.binding.MachineBindingThreeActivity;
import com.example.administrator.smallhappypay.activity.binding.MachineBindingTwoActivity;
import com.example.administrator.smallhappypay.activity.UnbindOneActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TerminalFragment extends Fragment {

    @BindView(R.id.terminal_oneok_ll)
    LinearLayout terminalOneokLl;
    @BindView(R.id.terminal_twook_ll)
    LinearLayout terminalTwookLl;
    @BindView(R.id.terminal_threeok_ll)
    LinearLayout terminalThreeokLl;
    @BindView(R.id.terminal_oneno_ll)
    LinearLayout terminalOnenoLl;
    @BindView(R.id.terminal_twono_ll)
    LinearLayout terminalTwonoLl;
    @BindView(R.id.terminal_threeno_ll)
    LinearLayout terminalThreenoLl;
    Unbinder unbinder;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_terminal_fragment, container, false);
        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.terminal_oneok_ll, R.id.terminal_twook_ll, R.id.terminal_threeok_ll, R.id.terminal_oneno_ll, R.id.terminal_twono_ll, R.id.terminal_threeno_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.terminal_oneok_ll:
                startActivity(new Intent(getActivity(), MachineBindingOneActivity.class));
                break;
            case R.id.terminal_twook_ll:
                startActivity(new Intent(getActivity(), MachineBindingTwoActivity.class));
                break;
            case R.id.terminal_threeok_ll:
                startActivity(new Intent(getActivity(), MachineBindingThreeActivity.class));
                break;
            case R.id.terminal_oneno_ll:
                startActivity(new Intent(getActivity(), UnbindOneActivity.class));
                break;
            case R.id.terminal_twono_ll:
                startActivity(new Intent(getActivity(), UnbindTwoActivity.class));
                break;
            case R.id.terminal_threeno_ll:
                startActivity(new Intent(getActivity(), UnbindThreeActivity.class));
                break;
        }
    }
}
