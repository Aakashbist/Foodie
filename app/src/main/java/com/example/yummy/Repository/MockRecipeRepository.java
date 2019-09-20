package com.example.yummy.Repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.yummy.Model.Recipe;
import com.example.yummy.Model.RecipeWithIngredints;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MockRecipeRepository implements IRecipeRepository {
    private List<Recipe> listOfRecipes;
    private List<Recipe> listOfFavouriteRecipes = new ArrayList<>();
    private List<RecipeWithIngredints> recipesWithIngredints;
    private Context context;
    private MutableLiveData<List<Recipe>> searchedRecipes = new MutableLiveData<>();
    private MutableLiveData<RecipeWithIngredints> recipe = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> recipeFavourites = new MutableLiveData<>();

    private Gson gson = new Gson();

   static MockRecipeRepository obj ;

    public static MockRecipeRepository getInstance(Context context){

        if (obj != null){
            return obj;
        }
        obj=new MockRecipeRepository(context);
        return obj;
    }

    private MockRecipeRepository(Context context) {

        this.context = context;

        loadData();
    }

    @Override
    public void addToFavourite(String recipeId) {

        Iterator<Recipe> iterator = listOfRecipes.iterator();

        while (iterator.hasNext()) {
            Recipe item = iterator.next();
            if (item.getRecipeId().equals(recipeId)) {
                listOfFavouriteRecipes.add(item);
            }
        }


    }

    @Override
    public LiveData<List<Recipe>> getFavourite() {
        recipeFavourites.setValue(listOfFavouriteRecipes);
        return recipeFavourites;

    }

    @Override
    public boolean isFavourite(String recipeId) {
        Iterator<Recipe> iterator = listOfFavouriteRecipes.iterator();

        while (iterator.hasNext()) {
            Recipe item = iterator.next();
            if (item.getRecipeId().equals(recipeId)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public LiveData<List<Recipe>> searchRecipes(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            searchedRecipes.setValue(listOfRecipes);
            return searchedRecipes;

        }
        // get an iterator to the list
        Iterator<Recipe> iterator = listOfRecipes.iterator();
        List<Recipe> searchRecipeItemList = new ArrayList<>();

        // iterate through the list
        while (iterator.hasNext()) {
            Recipe item = iterator.next();
            // filter values that start with searchTerm
            if (item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                searchRecipeItemList.add(item);
            }
        }
        searchedRecipes.setValue(searchRecipeItemList);
        return searchedRecipes;
    }

    @Override
    public LiveData<RecipeWithIngredints> getRecipeDetail(String recipeId) {
        Iterator<RecipeWithIngredints> iterator = recipesWithIngredints.iterator();
        while (iterator.hasNext()) {
            RecipeWithIngredints item = iterator.next();
            if (item.getRecipeId().equals(recipeId)) {
                recipe.setValue(item);
                return recipe;
            }

        }
        recipe.setValue(recipesWithIngredints.get(0));
        return recipe;
    }


    private void loadData() {

        listOfRecipes = Arrays.asList(gson.fromJson(getJson("recipe.json"), Recipe[].class));
        recipesWithIngredints = Arrays.asList(gson.fromJson(getJson("recipe.json"), RecipeWithIngredints[].class));
        recipe.setValue(recipesWithIngredints.get(0));
        listOfFavouriteRecipes.add(listOfRecipes.get(0));
        listOfFavouriteRecipes.add(listOfRecipes.get(1));
    }


    private String getJson(String fileName) {

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");
            return json;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}
