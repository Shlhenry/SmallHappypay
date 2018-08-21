package com.example.administrator.smallhappypay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.util.MccRateBean;
import java.util.List;

/**
 * Created by Administrator on 2018/6/21.
 */

public class MccRateTAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<MccRateBean.MapBean.TBean> mList;
    private Context mContext;

    public MccRateTAdapter(Context context, List<MccRateBean.MapBean.TBean>list){
        mContext=context;
        mList=list;
        mInflater=LayoutInflater.from(context);
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
            convertView=mInflater.inflate(R.layout.item_mccrate,null);
            holder.mccrateitem_mccname_tv = (TextView) convertView.findViewById(R.id.mccrateitem_mccname_tv);
            holder.mccrateitem_mccrate_tv = (TextView) convertView.findViewById(R.id.mccrateitem_mccrate_tv);
            holder.mccrateitem_mcccap_tv = (TextView) convertView.findViewById(R.id.mccrateitem_mcccap_tv);
            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        holder.mccrateitem_mccname_tv.setText(mList.get(position).getMcc_name());
        holder.mccrateitem_mccrate_tv.setText(mList.get(position).getMcc_rate());
        holder.mccrateitem_mcccap_tv.setText(mList.get(position).getMcc_cap());

        return convertView;
    }

    public class viewholder{
        public TextView mccrateitem_mccname_tv;
        public TextView mccrateitem_mccrate_tv;
        public TextView mccrateitem_mcccap_tv;
    }
}
