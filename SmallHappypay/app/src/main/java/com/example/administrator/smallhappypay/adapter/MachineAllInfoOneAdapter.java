package com.example.administrator.smallhappypay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.util.MachineInfoBean;
import com.example.administrator.smallhappypay.util.WaterBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/29.
 */

public class MachineAllInfoOneAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MachineInfoBean.MapBean.OneBean> mList;
    private Context mContext;

    public MachineAllInfoOneAdapter(Context context, List<MachineInfoBean.MapBean.OneBean> list){
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        if (mList == null || mList.size() == 0)
            return null;
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewholder holder;
        if (convertView==null){
            holder=new viewholder();
            convertView=mInflater.inflate(R.layout.item_machine,null);
            holder.itemmachine_name= (TextView) convertView.findViewById(R.id.itemmachine_name);
            holder.itemmachine_sn= (TextView) convertView.findViewById(R.id.itemmachine_sn);
//            holder.itemmachine_state= (TextView) convertView.findViewById(R.id.itemmachine_state);

            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }

        holder.itemmachine_name.setText(mList.get(position).getModename());
        holder.itemmachine_sn.setText(mList.get(position).getMac_sn());
//        holder.itemmachine_state.setText("+"+mList.get(position).getTranMoney());


        return convertView;
    }
    public class viewholder{
        public TextView itemmachine_name;
        public TextView itemmachine_sn;
//        public TextView itemmachine_state;
    }
}
