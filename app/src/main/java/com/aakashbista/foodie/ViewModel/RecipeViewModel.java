package com.aakashbista.foodie.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.aakashbista.foodie.AppManager.ApplicationServiceManager;
import com.aakashbista.foodie.Model.Recipe;
import com.aakashbista.foodie.Model.RecipeWithIngredints;
import com.aakashbista.foodie.AppManager.RecipeApplication;
import com.aakashbista.foodie.Repository.IRecipeRepository;

import java.util.List;

public class RecipeViewModel extends AndroidViewModel {
    private IRecipeRepository repository;
    public LiveData<List<Recipe>> recipes = new MutableLiveData<>();
    public LiveData<List<Recipe>> favouriteRecipes = new MutableLiveData<>();
    public LiveData<RecipeWithIngredints> recipe = new MutableLiveData<>();

    public RecipeViewModel(@NonNull Application application) {
        super(application);
        RecipeApplication recipeApplication = (RecipeApplication) application;
        ApplicationServiceManager manager = recipeApplication;
        repository = manager.getRecipeRepository();

    }

    public void searchRecipes(String searchTerm) {
        recipes = repository.searchRecipes(searchTerm);
    }

    public void getRecipeDetail(String recipeId) {
        recipe = repository.getRecipeDetail(recipeId);
    }

    public boolean isFavourite(String recipeId) {
        return repository.isFavourite(recipeId);
    }

    public void addToFavourite(String recipeId) {

        repository.addToFavourite(recipeId);

    }

    public void getFavourite() {
        favouriteRecipes = repository.getFavourite();

    }
}
