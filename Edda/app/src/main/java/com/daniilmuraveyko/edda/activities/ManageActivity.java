package com.daniilmuraveyko.edda.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.daniilmuraveyko.edda.model.Product;
import com.daniilmuraveyko.edda.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ManageActivity extends BaseActivity {

    AutoCompleteTextView product_name;
    AutoCompleteTextView product_measure;
    EditText product_count;
    List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        product_name = (AutoCompleteTextView) findViewById(R.id.autocompName);
        product_measure = (AutoCompleteTextView) findViewById(R.id.autoCompMeasure);
        product_count = (EditText) findViewById(R.id.autocompCount);
        products = Product.listAll(Product.class);
        fillNames();
        fillMeasurements();
    }

    private void fillNames(){
        List<String> products_names = new ArrayList<>(products.size());
        for (Product product : products) {
            products_names.add(product.getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, products_names);
        product_name.setAdapter(adapter);
    }

    private void fillMeasurements(){
        List<String> products_measurements = new ArrayList<>(products.size());
        for (Product product : products) {
            products_measurements.add(product.getMeasure());
        }
        HashSet hs = new HashSet();
        hs.addAll(products_measurements);
        products_measurements.clear();
        products_measurements.addAll(hs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, products_measurements);
        product_measure.setAdapter(adapter);
    }

    public void onSaveButtonClick(View view)
    {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        if(TextUtils.isEmpty(product_name.getText()) ||
                TextUtils.isEmpty(product_count.getText()) ||
                TextUtils.isEmpty(product_measure.getText())) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "All fields must be filled", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        String name = product_name.getText().toString();
        Float count = Float.parseFloat(product_count.getText().toString());
        String measurement = product_measure.getText().toString();
        List<Product> p = Product.find(Product.class, "name = ?", name);
        if(!p.isEmpty()) {
            Product product = p.get(0);
            product.addCount(count);
            product.save();

        }
        else {
            Product product = new Product(name, count, measurement);
            product.save();
        }
        product_name.setText("");
        product_count.setText("");
        product_measure.setText("");
    }

    @Override
    int getContentViewId() {
        return R.layout.activity_manage;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_manage;
    }
}
