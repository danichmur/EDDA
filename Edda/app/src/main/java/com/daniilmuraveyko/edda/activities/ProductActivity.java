package com.daniilmuraveyko.edda.activities;

import android.os.Bundle;
import android.widget.ListView;

import com.daniilmuraveyko.edda.entity.Product;
import com.daniilmuraveyko.edda.utilites.ProductAdapter;
import com.daniilmuraveyko.edda.R;

import java.util.List;

import static com.daniilmuraveyko.edda.entity.Product.COMPARE_ALPHABETIC;

public class ProductActivity extends BaseActivity {

    ListView products_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        products_list = (ListView) findViewById(R.id.products_list);
        fillList();
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_product;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_products;
    }

    private void fillList(){
        List<Product> products = Product.listAll(Product.class);
        products.sort(COMPARE_ALPHABETIC);
        products_list.setAdapter(new ProductAdapter(this, products));
    }
}
