package com.example.yummy.Repository;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.Model.Recipe;
import com.example.yummy.Model.RecipeWithIngredints;
import com.example.yummy.Network.IRecipeService;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeRepository implements IRecipeRepository {

    private IRecipeService service;

    private MutableLiveData<List<Recipe>> listOfRecipes=new MutableLiveData<>();

    private MutableLiveData<RecipeWithIngredints> recipe = new MutableLiveData<>();

    public RecipeRepository(IRecipeService recipeService) {
        service = recipeService;
    }

    public LiveData<List<Recipe>> searchRecipes(String searchTerm) {
        Call<List<Recipe>> search = service.search(searchTerm);

        search.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NotNull Call<List<Recipe>> call, @NotNull Response<List<Recipe>> response) {
                if (response.isSuccessful())

                    listOfRecipes.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                Log.d("", "onFailure: ss");
            }
        });

        return listOfRecipes;
    }


    @Override
    public LiveData<RecipeWithIngredints> getRecipeDetail(String recipeId) {

        Call<RecipeWithIngredints> recipeDetail = service.getRecipeDetail(recipeId);
        recipeDetail.enqueue(new Callback<RecipeWithIngredints>() {
            @Override
            public void onResponse(Call<RecipeWithIngredints> call, Response<RecipeWithIngredints> response) {
                recipe.setValue(response.body());
            }

            @Override
            public void onFailure(Call<RecipeWithIngredints> call, Throwable t) {

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
