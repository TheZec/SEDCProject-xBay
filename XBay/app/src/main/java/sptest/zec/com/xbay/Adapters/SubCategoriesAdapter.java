package sptest.zec.com.xbay.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import sptest.zec.com.xbay.Models.SubCategoryModel;
import sptest.zec.com.xbay.R;

/**
 * Created by Aleksandar on 9/29/2014.
 */
public class SubCategoriesAdapter extends ArrayAdapter<SubCategoryModel> {

    private Context context;
    private List<SubCategoryModel> subCategoryModels;

    public SubCategoriesAdapter(Context context, int resource, List<SubCategoryModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.subCategoryModels = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.categories_list_items, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.img_categories);
            viewHolder.mText = (TextView) convertView.findViewById(R.id.txt_categories);

            convertView.setTag(viewHolder);
        }

        ViewHolder mHolder = (ViewHolder) convertView.getTag();
        SubCategoryModel subCategoryModel = subCategoryModels.get(position);

        mHolder.mText.setText(subCategoryModel.getSubCategoryName());
        mHolder.mImage.setImageResource(R.drawable.ic_home);

        return convertView;
    }

    private static class ViewHolder {
        ImageView mImage;
        TextView mText;
    }
}
