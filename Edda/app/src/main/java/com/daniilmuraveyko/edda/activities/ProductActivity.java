package com.daniilmuraveyko.edda.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.daniilmuraveyko.edda.model.Product;
import com.daniilmuraveyko.edda.utilites.ProductAdapter;
import com.daniilmuraveyko.edda.R;

import java.util.List;

import static com.daniilmuraveyko.edda.model.Product.COMPARE_ALPHABETIC;

public class ProductActivity extends BaseActivity {

    ListView products_list;
    List<Product> products;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        products_list = (ListView) findViewById(R.id.products_list);
        products_list.setOnItemLongClickListener((parent, view, position, id) -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = this.getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.dialog_eat, null));

            builder.setPositiveButton(R.string.eat, (d, i) -> {
                changeProduct(products.get(position));
            });
            builder.setNegativeButton(R.string.cancel, (d, i) -> {
            });
            AlertDialog dialog = builder.create();
            dialog.show();
            TextView tvName = dialog.findViewById(R.id.tvNameDialog);
            editText = dialog.findViewById(R.id.eatCount);
            tvName.setText(products.get(position).getName() + ", ("
                    + products.get(position).getMeasure() + ") " );

            return true;
        });
        fillList();
    }

    private void changeProduct(Product product){

        String count = editText.getText().toString();
        if(count.equals("")){
            return;
        }
        int countInt = Integer.parseInt(count);
        product.subCount(countInt);
        product.saveChanges();
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
        products = Product.listAll(Product.class);
        products.sort(COMPARE_ALPHABETIC);
        products_list.setAdapter(new ProductAdapter(this, products));
    }
}
