package sptest.zec.com.xbay.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import sptest.zec.com.xbay.Models.NavigationDrawerListModel;
import sptest.zec.com.xbay.R;

/**
 * Created by Aleksandar on 9/18/2014.
 */
public class NavigationdDrawerAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavigationDrawerListModel> navDrawerItems;

    public NavigationdDrawerAdapter(Context context, ArrayList<NavigationDrawerListModel> navDrawerItems) {
        this.context = context;
        this.navDrawerItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.item_navigation_drawer, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.item_icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.item_title);

        imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        txtTitle.setText(navDrawerItems.get(position).getTitle());

        // displaying count
        // check whether it set visible or not

        return convertView;
    }

}