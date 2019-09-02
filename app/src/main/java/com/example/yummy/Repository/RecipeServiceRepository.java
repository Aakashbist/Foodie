package com.example.yummy.Repository;

import android.util.Log;

import com.example.yummy.Model.Recipes;
import com.example.yummy.Model.SearchRecipeItem;
import com.example.yummy.Network.ApiClient;
import com.example.yummy.Network.IRecipeService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeServiceRepository implements IRecipeService {
    private  IRecipeService recipeService;
    public RecipeServiceRepository(IRecipeService iRecipeService){
        recipeService=iRecipeService;
    }



    @Override
    public Call<Recipes> search(String searchTerm) {

        Call<Recipes>  call = ApiClient.getRecipeService().search(searchTerm);
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                //List<SearchRecipeItem> recipes= response.body().getRecipes();
                int size=response.body().getRecipes().size();
                if(size>0)
                {
                    Log.e("success" ," size = "+ size);
                }
                else{
                    Log.e("onsucc" ," failure");
                }
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                Log.e("onfail" ," failure");
            }
        });
        return call;
    }

    @Override
    public Call<Recipes> getRecipe(String recipeId) {
        return recipeService.getRecipe(recipeId);
    }
}
