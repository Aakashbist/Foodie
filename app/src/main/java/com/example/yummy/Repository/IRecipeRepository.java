package com.example.yummy.Repository;

import androidx.lifecycle.LiveData;

import com.example.yummy.Model.Recipe;
import com.example.yummy.Model.RecipeWithIngredints;

import java.util.List;

public interface IRecipeRepository {

    LiveData<List<Recipe>> searchRecipes(String searchTerm);

    LiveData<RecipeWithIngredints> getRecipeDetail(String recipeId);

}
