package com.daniilmuraveyko.edda.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import com.daniilmuraveyko.edda.R;
import com.daniilmuraveyko.edda.entity.Recipe;
import com.squareup.picasso.Picasso;

public class RecipeActivity extends BaseActivity {

    Recipe recipe;
    ImageView image;
    TextView tvName;
    TextView tvIngredients;
    TextView tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String positionS = getIntent().getStringExtra("position");
        int position = Integer.parseInt(positionS);
        recipe = RecipesActivity.recipes.get(position);
        image = (ImageView) findViewById(R.id.image);
        tvName = (TextView) findViewById(R.id.tvName);
        tvIngredients = (TextView) findViewById(R.id.tvIngredients);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        fillContent();
    }

    void fillContent(){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        System.out.println(image.getWidth() + " " + image.getHeight());
        Picasso.with(this).load(recipe.getImg_url())
                .resize(displayMetrics.widthPixels, displayMetrics.heightPixels / 3).centerInside().into(image);
        tvName.setText(recipe.getName());
        tvIngredients.setText(recipe.ingredientsToText());
        tvDescription.setText(recipe.getDescription());
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_recipe;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_recipes;
    }
}
