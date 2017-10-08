package com.daniilmuraveyko.edda.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.daniilmuraveyko.edda.Product;
import com.daniilmuraveyko.edda.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danichmur on 06.10.17.
 */

public class RecipeAdapter extends BaseAdapter {
    List<Product> products;
    List<Product> checkedProducts;
    private Context context;

    public boolean isChecked() {
        return checkBox.isChecked();
    }

    private CheckBox checkBox;

    public RecipeAdapter(Context context, List products){
        this.products = products;
        this.context = context;
        checkedProducts = new ArrayList<>();
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
                inflate(R.layout.product_list_item_with_choosebox, parent, false);
        checkBox = (CheckBox) rowView.findViewById(R.id.checkBox);
        TextView text1 = (TextView) rowView.findViewById(R.id.name);
        text1.setText(products.get(position).getName());
        checkBox.setOnCheckedChangeListener(myCheckChangeList);
        checkBox.setTag(position);
        return rowView;
    }

    public List<Product> getIngridients(){
        return checkedProducts;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // меняем данные товара (в корзине или нет)
            if(isChecked) {
                checkedProducts.add(products.get((Integer) buttonView.getTag()));
            }
            else{
                checkedProducts.remove(products.get((Integer) buttonView.getTag()));
            }
        }
    };
}
