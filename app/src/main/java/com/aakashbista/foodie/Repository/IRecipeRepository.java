package com.aakashbista.foodie.Repository;

import androidx.lifecycle.LiveData;

import com.aakashbista.foodie.Model.Recipe;
import com.aakashbista.foodie.Model.RecipeWithIngredints;

import java.util.List;

public interface IRecipeRepository {

    LiveData<List<Recipe>> searchRecipes(String searchTerm);

    LiveData<RecipeWithIngredints> getRecipeDetail(String recipeId);

    void addToFavourite(String recipeId);

    LiveData<List<Recipe>> getFavourite();

    boolean isFavourite(String recipeId);

}
