package com.example.administrator.smallhappypay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.util.MccRateBean;
import com.example.administrator.smallhappypay.util.QueryModelBean;

import java.util.List;

/**
 * Created by Administrator on 2018/6/28.
 */

public class QueryModleAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private List<QueryModelBean.ListBean> mList;
    private Context mContext;

    public QueryModleAdapter(Context context, List<QueryModelBean.ListBean>list){
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
            convertView=mInflater.inflate(R.layout.simple_list_item,null);
            holder.text = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holder);
        }else{
            holder = (viewholder) convertView.getTag();
        }
        holder.text.setText(mList.get(position).getName());

        return convertView;
    }

    public class viewholder{
        public TextView text;
    }

}
