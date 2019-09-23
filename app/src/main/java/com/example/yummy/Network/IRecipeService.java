package com.example.yummy.Network;

import com.example.yummy.Model.Recipe;
import com.example.yummy.Model.RecipeWithIngredints;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IRecipeService {
    @GET("search")
    Call<List<Recipe>> search(@Query("q") String searchTerm);
//    https://www.food2fork.com/api/search?key=YOUR_API_KEY&q=chicken%20breast&page=2

    @GET("get")
    Call<RecipeWithIngredints> getRecipeDetail(@Query("rId") String recipeId);
}
