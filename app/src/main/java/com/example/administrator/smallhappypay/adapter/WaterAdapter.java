package com.example.administrator.smallhappypay.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.util.WaterBean;
import java.util.List;

/**
 * Created by Administrator on 2018/6/27.
 */

public class WaterAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<WaterBean.ListBean> mList;
    private Context mContext;

    public WaterAdapter(Context context, List<WaterBean.ListBean> list){
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
            convertView=mInflater.inflate(R.layout.item_water,null);
            holder.itemwater_type_img= (ImageView) convertView.findViewById(R.id.itemwater_type_img);
            holder.itemwater_mccName_tv= (TextView) convertView.findViewById(R.id.itemwater_mccName_tv);
            holder.itemwater_tran_tv= (TextView) convertView.findViewById(R.id.itemwater_tran_tv);
            holder.itemwater_tranTime_tv= (TextView) convertView.findViewById(R.id.itemwater_tranTime_tv);
            holder.itemwater_tranMoney_tv= (TextView) convertView.findViewById(R.id.itemwater_tranMoney_tv);
            holder.itemwater_tranFee_tv= (TextView) convertView.findViewById(R.id.itemwater_tranFee_tv);

            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        if (mList.get(position).getMcc_id().equals("45")||mList.get(position).getMcc_id().equals("46")){
            holder.itemwater_type_img.setBackgroundResource(R.drawable.jylswechat);
            holder.itemwater_tran_tv.setText(mList.get(position).getTran37());
        }else if (mList.get(position).getMcc_id().equals("47")||mList.get(position).getMcc_id().equals("48")){
            holder.itemwater_type_img.setBackgroundResource(R.drawable.jylszfb);
            holder.itemwater_tran_tv.setText(mList.get(position).getTran37());
        }else {
            holder.itemwater_type_img.setBackgroundResource(R.drawable.jylspos);
            holder.itemwater_tran_tv.setText(mList.get(position).getTran2());
        }
        holder.itemwater_mccName_tv.setText(mList.get(position).getMccName());
        holder.itemwater_tranTime_tv.setText(mList.get(position).getTranTime());
        holder.itemwater_tranMoney_tv.setText("+"+mList.get(position).getTranMoney());
        holder.itemwater_tranFee_tv.setText("手续费 : "+mList.get(position).getTranFee());

        return convertView;
    }
    public class viewholder{
        public ImageView itemwater_type_img;
        public TextView itemwater_mccName_tv;
        public TextView itemwater_tran_tv;
        public TextView itemwater_tranTime_tv;
        public TextView itemwater_tranMoney_tv;
        public TextView itemwater_tranFee_tv;
    }
}
