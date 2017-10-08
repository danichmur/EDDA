package com.daniilmuraveyko.edda;

/**
 * Created by danichmur on 05.10.17.
 */

import com.orm.SugarRecord;

import java.util.Comparator;

public class Product extends SugarRecord{

    String name;
    float count;
    String measure;

    public String getName() {
        return name;
    }

    public float getCount() {
        return count;
    }

    public String getMeasure() {
        return measure;
    }

    public Product() {
    }

    public Product(String name, float count, String measure) {
        this.name = name;
        this.count = count;
        this.measure = measure;
    }

    public void addCount(float count){
        this.count += count;
    }

    public void subCount(float count){
        this.count -= count;
    }

    @Override
    public String toString() {
        return name + ": " + count + " " + measure;
    }

    public static final Comparator<Product> COMPARE_ALPHABETIC = (product, t1) -> product.name.compareTo(t1.name);

}
