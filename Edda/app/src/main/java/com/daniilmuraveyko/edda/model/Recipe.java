package com.daniilmuraveyko.edda.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by danichmur on 10.10.17.
 */

public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public String getImg_url() {
        return img_url;
    }

    String name;
    String description;
    List<Ingredient> ingredients;
    String img_url;


    public Recipe(){}

    public Recipe(String name, String description,
                  List<Ingredient> ingredients, String img_url){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.img_url = img_url;
    }

    public String ingredientsToText(){
        String s = "";
        for(Ingredient ingredient : ingredients){
            s += ingredient.toString() + "\n";
        }
        return s;
    }

    public String toString(){
        return name + " " + description + " " +
                ingredients.toString() + " " + img_url;
    }
}
