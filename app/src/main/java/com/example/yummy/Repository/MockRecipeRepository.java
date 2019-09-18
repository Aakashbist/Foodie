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
    private List<RecipeWithIngredints> recipesWithIngredints;
    private RecipeWithIngredints recipeItem;
    private Context context;
    private MutableLiveData<List<Recipe>> searchedRecipes = new MutableLiveData<>();
    private MutableLiveData<RecipeWithIngredints> recipe = new MutableLiveData<>();
    private Gson gson = new Gson();


    public MockRecipeRepository(Context context) {

        this.context = context;

        loadData();
    }


    @Override
    public LiveData<List<Recipe>> searchRecipes(String searchTerm) {

        // get an iterator to the list
        Iterator<Recipe> iterator = listOfRecipes.iterator();
        List<Recipe> searchRecipeItemList = new ArrayList<>();

        // iterate through the list
        while (iterator.hasNext()) {
            Recipe item = iterator.next();

            // filter values that start with searchTerm
            if (searchTerm == null) {
                    searchRecipeItemList.add(item);
            }
            else{
                if(item.getTitle().toLowerCase().contains(searchTerm.toLowerCase())){
                    searchRecipeItemList.add(item);
                }
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

        listOfRecipes = Arrays.asList(gson.fromJson(getRecipesJson(), Recipe[].class));
        recipesWithIngredints = Arrays.asList(gson.fromJson(getRecipesJson(), RecipeWithIngredints[].class));
        recipe.setValue(recipesWithIngredints.get(0));
    }


    private String getRecipesJson() {

        try {
            InputStream inputStream = context.getAssets().open("recipe.json");
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
