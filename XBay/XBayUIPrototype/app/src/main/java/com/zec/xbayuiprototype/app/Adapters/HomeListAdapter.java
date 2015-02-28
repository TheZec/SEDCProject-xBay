package com.zec.xbayuiprototype.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zec.xbayuiprototype.app.Models.ListMenu;
import com.zec.xbayuiprototype.app.R;

import java.util.ArrayList;

/**
 * Created by Aleksandar on 6/11/2014.
 */
public class HomeListAdapter extends BaseAdapter {

    private ArrayList<ListMenu> list;
    private LayoutInflater layoutInflater;

    public HomeListAdapter(Context context, ArrayList list){
        this.list = list;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.home_items, null);
            holder = new ViewHolder();
            holder.items = (TextView) convertView.findViewById(R.id.txt_list_item);

            convertView.setTag(holder);

        }
            holder = (ViewHolder) convertView.getTag();
            ListMenu item = list.get(position);


        holder.items.setText(item.getItem());
        holder.items.setTextSize(28);
        holder.items.setHeight(100);


        return convertView;
    }

    static class ViewHolder{

        private TextView items;

    }
}
