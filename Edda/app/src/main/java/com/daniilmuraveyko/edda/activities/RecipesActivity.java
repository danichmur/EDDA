package com.daniilmuraveyko.edda.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.daniilmuraveyko.edda.R;
import com.daniilmuraveyko.edda.entity.Ingredient;
import com.daniilmuraveyko.edda.entity.Product;
import com.daniilmuraveyko.edda.entity.Recipe;
import com.daniilmuraveyko.edda.utilites.JsonParser;
import com.daniilmuraveyko.edda.utilites.RecipeAdapter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class RecipesActivity extends BaseActivity {

    ListView recipes_list;
    public static ArrayList<Recipe> recipes;
    String url;
    ArrayList<Product> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipes_list = (ListView) findViewById(R.id.list_for_recipe);
        ingredients = (ArrayList<Product>)getIntent().getSerializableExtra("products");
        System.out.println(ingredients);
        url = fillUrl();
        System.out.println(url);
       // DownloadRecipes dr = new DownloadRecipes();
        //dr.execute();
    }

    private String fillUrl(){
        String url = "http://192.168.100.3:3000/recipes.json?";
        String name = "name";
        String count = "count";
        for(Product product : ingredients){
            url += (name + "=" + product.getName() +
                    "&" + count + "=" + product.getCount() + "&");
        }
        return url.substring(0, url.length() - 1);
    }



    @Override
    int getContentViewId() {
        return R.layout.activity_recipes;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_recipes;
    }

    class DownloadRecipes extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            return JsonParser.getJSONFromUrl(url);
        }

        @Override
        protected void onPostExecute(String result) {
            recipes = new Gson().fromJson(result, new TypeToken<List<Recipe>>() {}.getType());
            RecipeAdapter recipeAdapter = new RecipeAdapter(RecipesActivity.this, recipes);
            recipes_list.setAdapter(recipeAdapter);


            recipes_list.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
                Intent intent = new Intent(RecipesActivity.this, RecipeActivity.class);
                System.out.println("BEFORE:" + position);
                intent.putExtra("position", String.valueOf(position));
                startActivity(intent);
            });
        }
    }
}
