package com.daniilmuraveyko.edda.utilites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daniilmuraveyko.edda.R;
import com.daniilmuraveyko.edda.model.Product;

import java.util.List;

/**
 * Created by danichmur on 05.10.17.
 */

public class ProductAdapter extends BaseAdapter {
    List<Product> products;
    private Context context;

    public ProductAdapter(Context context, List products){
        this.products = products;
        this.context = context;
    }
    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).
                inflate(R.layout.product_list_item, parent, false);

        TextView text1 = rowView.findViewById(R.id.name);
        TextView text2 = rowView.findViewById(R.id.info);

        text1.setText(products.get(position).getName());
        Product product = products.get(position);
        text2.setText((int) product.getCount() + " " + product.getMeasure());
        return rowView;
    }
}
