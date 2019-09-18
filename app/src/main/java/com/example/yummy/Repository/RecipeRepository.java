package com.example.yummy.Repository;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.Model.Recipe;
import com.example.yummy.Model.RecipeWithIngredints;
import com.example.yummy.Network.IRecipeService;

import java.util.List;

import io.reactivex.schedulers.Schedulers;

public class RecipeRepository implements IRecipeRepository {

    private IRecipeService service;

    private MutableLiveData<List<Recipe>> listOfRecipes = new MutableLiveData<>();
    private MutableLiveData<RecipeWithIngredints> recipe = new MutableLiveData<>();

    public RecipeRepository(IRecipeService recipeService) {
        service = recipeService;
    }

    public LiveData<List<Recipe>> searchRecipes(String searchTerm) {
        LiveData<List<Recipe>> recipesLiveDataSource = LiveDataReactiveStreams.fromPublisher(service.search(searchTerm)
            .subscribeOn(Schedulers.io()));
        listOfRecipes.setValue(recipesLiveDataSource.getValue());
        return listOfRecipes;
    }


    @Override
    public LiveData<RecipeWithIngredints> getRecipeDetail(String recipeId) {
        LiveData<RecipeWithIngredints> recipeItemLiveData = LiveDataReactiveStreams.fromPublisher(service.getRecipeDetail(recipeId)
            .subscribeOn(Schedulers.io()));
        recipe.setValue(recipeItemLiveData.getValue());
        return recipe;
    }
}
