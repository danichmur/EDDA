package com.daniilmuraveyko.edda.utilites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.daniilmuraveyko.edda.R;
import com.daniilmuraveyko.edda.model.Ingredient;
import com.daniilmuraveyko.edda.model.Recipe;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danichmur on 11.10.17
 * Copyright © 2017 danichmur. All rights reserved.
 */

public class RecipeAdapter extends BaseAdapter {
    List<Recipe> recipeList;
    private Context context;

    public RecipeAdapter(Context context, List recipeList){
        this.recipeList = recipeList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return recipeList.size();
    }

    @Override
    public Object getItem(int i) {
        return recipeList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View rowView = LayoutInflater.from(context).
                inflate(R.layout.recipe_list_item, parent, false);
        Recipe recipe = recipeList.get(position);
        ImageView image = rowView.findViewById(R.id.image);
        TextView tvName = rowView.findViewById(R.id.tvName);
        ListView lvIngredients = rowView.findViewById(R.id.lvIngredients);
        tvName.setText(recipe.getName());

        ArrayList<Ingredient> ingredientsList =
                (ArrayList<Ingredient>) recipe.getIngredients();

        String[] ingredients = new String[ingredientsList.size()];
        int i = 0;
        for (Ingredient ingredient : ingredientsList) {
            ingredients[i++] = (ingredient.toString());
        }
        lvIngredients.setAdapter(new IngredientAdapter(context, ingredients));

        Picasso.with(context)
                .load(recipe.getImg_url()).resize(125,199).centerCrop().into(image);

        return rowView;
    }
}
