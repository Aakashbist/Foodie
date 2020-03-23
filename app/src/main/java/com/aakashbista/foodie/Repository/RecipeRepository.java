package com.aakashbista.foodie.Repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aakashbista.foodie.Model.Recipe;
import com.aakashbista.foodie.Model.RecipeWithIngredints;
import com.aakashbista.foodie.Network.IRecipeService;
import com.aakashbista.foodie.Repository.models.ServiceRecipeWithIngredints;
import com.aakashbista.foodie.Repository.models.ServiceRecipes;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository implements IRecipeRepository {

    private IRecipeService service;

    private MutableLiveData<List<Recipe>> listOfRecipes = new MutableLiveData<>();

    private MutableLiveData<RecipeWithIngredints> recipe = new MutableLiveData<>();

    public RecipeRepository(IRecipeService recipeService) {
        service = recipeService;
    }

    public LiveData<List<Recipe>> searchRecipes(String searchTerm) {
        Call<ServiceRecipes> recipes = service.search(searchTerm);

        recipes.enqueue(new Callback<ServiceRecipes>() {
            @Override
            public void onResponse(Call<ServiceRecipes> call, Response<ServiceRecipes> response) {
                if (response.isSuccessful()) {
                    ServiceRecipes body = response.body();
                    listOfRecipes.setValue(body.recipes);
                }
            }

            @Override
            public void onFailure(Call<ServiceRecipes> call, Throwable t) {

            }
        });


        return listOfRecipes;
    }


    @Override
    public LiveData<RecipeWithIngredints> getRecipeDetail(String recipeId) {

        Call<ServiceRecipeWithIngredints> recipeDetail = service.getRecipeDetail(recipeId);
        recipeDetail.enqueue(new Callback<ServiceRecipeWithIngredints>() {
            @Override
            public void onResponse(Call<ServiceRecipeWithIngredints> call, Response<ServiceRecipeWithIngredints> response) {
                if (response.isSuccessful()) {
                    recipe.setValue(response.body().recipe);
                }

            }

            @Override
            public void onFailure(Call<ServiceRecipeWithIngredints> call, Throwable t) {

            }
        });
        return recipe;
    }

    @Override
    public void addToFavourite(String recipeId) {

    }

    @Override
    public LiveData<List<Recipe>> getFavourite() {
        return null;
    }

    @Override
    public boolean isFavourite(String recipeId) {
        return false;
    }


}
