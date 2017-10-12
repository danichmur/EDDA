package com.daniilmuraveyko.edda.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.daniilmuraveyko.edda.model.Recipe;
import com.daniilmuraveyko.edda.utilites.JsonParser;
import com.daniilmuraveyko.edda.model.Product;
import com.daniilmuraveyko.edda.R;
import com.daniilmuraveyko.edda.utilites.ProductChooseAdapter;

import java.util.List;

import static com.daniilmuraveyko.edda.model.Product.COMPARE_ALPHABETIC;

public class ProductChooseActivity extends BaseActivity {

    ListView list_for_recipe;
    List<Product> products;
    ProductChooseAdapter productChooseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_for_recipe = (ListView) findViewById(R.id.list_for_recipe);

        fillList();
    }

    private void fillList(){
        products = Product.listAll(Product.class);
        products.sort(COMPARE_ALPHABETIC);
        productChooseAdapter = new ProductChooseAdapter(this, products);
        list_for_recipe.setAdapter(productChooseAdapter);
    }

    public void onDoneButtonClick(View view) {
        Intent intent = new Intent(this, RecipesActivity.class);
        intent.putExtra("products", productChooseAdapter.getIngredients());
        startActivity(intent);
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_product_choose;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_recipes;
    }


}
