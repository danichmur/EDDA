package com.daniilmuraveyko.edda.activities;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ListView;

import com.daniilmuraveyko.edda.Product;
import com.daniilmuraveyko.edda.R;

import java.util.ArrayList;
import java.util.List;

import static com.daniilmuraveyko.edda.Product.COMPARE_ALPHABETIC;

public class RecipesActivity  extends BaseActivity {
    ListView list_for_recipe;
    List<Product> products;
    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_for_recipe = (ListView) findViewById(R.id.list_for_recipe);
        fillList();
    }

    private void fillList(){
        products = Product.listAll(Product.class);
        products.sort(COMPARE_ALPHABETIC);
        recipeAdapter = new RecipeAdapter(this, products);

        list_for_recipe.setAdapter(recipeAdapter);
    }

    public void onDoneButtonClick(View view) {
        System.out.println(recipeAdapter.getIngridients());
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_recipes;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_recipes;
    }
}
