package sptest.zec.com.xbay.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import sptest.zec.com.xbay.Models.CategoryViewModel;
import sptest.zec.com.xbay.R;

/**
 * Created by Aleksandar on 9/18/2014.
 */
public class ProductsAdapter extends ArrayAdapter<CategoryViewModel> {

    private Context context;
    private List<CategoryViewModel> categoryViewModels;

    public ProductsAdapter(Context context, int resource, List<CategoryViewModel> objects) {
        super(context, resource, objects);
        this.context = context;
        this.categoryViewModels = objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater =
                    (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.category_view, parent, false);

            ViewHolder viewHolder = new ViewHolder();
            viewHolder.mImage = (ImageView) convertView.findViewById(R.id.img_category_view);
            viewHolder.mTextName = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.mTextQuantity = (TextView) convertView.findViewById(R.id.txt_orders);
            viewHolder.mTextPrice = (TextView) convertView.findViewById(R.id.txt_price);
            viewHolder.mTextDescription = (TextView) convertView.findViewById(R.id.txt_shiping);


            convertView.setTag(viewHolder);
        }


        ViewHolder mHolder = (ViewHolder) convertView.getTag();
        CategoryViewModel categoryViewModel = categoryViewModels.get(position);

        mHolder.mTextName.setText(categoryViewModel.getProductName());
        mHolder.mTextPrice.setText("Price: $" + categoryViewModel.getPrice());
        mHolder.mTextDescription.setText("Condition: " + categoryViewModel.getItemCondition());
        mHolder.mTextQuantity.setText("Quantity " + categoryViewModel.getQuantity());
        String path = "http://www.webmedia.mk/xbay/";
        Picasso.with(context)
                .load(path + categoryViewModel.getProductImageUrl().replaceAll("~/", ""))
                .resize(655, 550)
                .placeholder(R.id.img_category_view)
                .into(mHolder.mImage);

        System.out.println(path + categoryViewModel.getProductImageUrl());
        return convertView;
    }

    private static class ViewHolder {
        ImageView mImage;
        TextView mTextName;
        TextView mTextPrice;
        TextView mTextQuantity;
        TextView mTextDescription;
    }
}
