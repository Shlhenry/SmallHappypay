package com.example.administrator.smallhappypay.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.smallhappypay.R;
import com.example.administrator.smallhappypay.util.CityBean;
import java.util.List;

/**
 * Created by Administrator on 2017/4/7.
 */
public class ListviewCityAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
        private List<CityBean.ListBean> mList;
        private Context mContext;

        public ListviewCityAdapter(Context context, List<CityBean.ListBean> list){
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
            if (convertView == null) {
                holder = new viewholder();
                convertView = mInflater.inflate(R.layout.renzheng_listview_item, null);
                holder.city_name = (TextView) convertView.findViewById(R.id.item_city);
                convertView.setTag(holder);
            }else{
                holder = (viewholder) convertView.getTag();
            }
            holder.city_name.setText(mList.get(position).getCityName());
            return convertView;
        }
        private class viewholder{
            TextView city_name;
    }
}
