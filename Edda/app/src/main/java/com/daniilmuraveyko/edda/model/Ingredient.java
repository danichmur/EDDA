package com.daniilmuraveyko.edda.model;


import java.io.Serializable;

/**
 * Created by danichmur on 10.10.17.
 */

public class Ingredient implements Serializable{
    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public int getCount() {
        return count;
    }

    String name;
    int count;

    public Ingredient(String name, int count){
        this.name = name;
        this.count = count;
    }

    public String toString(){
        return name + " : " + count;
    }
}
