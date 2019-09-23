package com.example.yummy.Network;

import com.example.yummy.Repository.models.ServiceRecipeWithIngredints;
import com.example.yummy.Repository.models.ServiceRecipes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


//    https://www.food2fork.com/api/search?key=YOUR_API_KEY&q=chicken%20breast&page=2
public interface IRecipeService {
    @GET("search")
    Call<ServiceRecipes> search(@Query("q") String searchTerm);

    @GET("get")
    Call<ServiceRecipeWithIngredints> getRecipeDetail(@Query("rId") String recipeId);
}
