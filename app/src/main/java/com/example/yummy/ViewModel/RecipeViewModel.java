package com.example.yummy.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.ApplicationServiceManager;
import com.example.yummy.Model.Recipe;
import com.example.yummy.Model.RecipeWithIngredints;
import com.example.yummy.RecipeApplication;
import com.example.yummy.Repository.IRecipeRepository;

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
        repository = manager.getRepository();

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
