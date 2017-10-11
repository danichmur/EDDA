package com.daniilmuraveyko.edda.utilites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.daniilmuraveyko.edda.R;

/**
 * Created by danichmur on 11.10.17
 * Copyright © 2017 danichmur. All rights reserved.
 */

public class IngredientAdapter extends BaseAdapter {

    String[] ingredients;
    private Context context;

    public IngredientAdapter(Context context, String[] ingredients){
        this.ingredients = ingredients;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ingredients.length;
    }

    @Override
    public Object getItem(int i) {
        return ingredients[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).
                inflate(R.layout.ingredient_list_item, parent, false);
        TextView tvName = rowView.findViewById(R.id.tvName);
        tvName.setText(ingredients[position]);
        return rowView;
    }
}
