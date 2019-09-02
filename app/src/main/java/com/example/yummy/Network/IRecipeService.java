package com.example.yummy.Network;

import com.example.yummy.Model.Recipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRecipeService {
    @GET("search")
    Call<Recipes> search(@Query("q") String searchTerm);

    @GET("get")
    Call<Recipes> getRecipe(@Query("rId") String recipeId);
}
