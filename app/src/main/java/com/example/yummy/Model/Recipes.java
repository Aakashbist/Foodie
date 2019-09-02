package com.example.yummy.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Recipes {

    @SerializedName("recipes")
    @Expose
    private List<SearchRecipeItem> recipes;



    public void setRecipes(List<SearchRecipeItem> recipes){
        this.recipes = recipes;
    }

    public List<SearchRecipeItem> getRecipes() {
        return recipes;
    }


}
