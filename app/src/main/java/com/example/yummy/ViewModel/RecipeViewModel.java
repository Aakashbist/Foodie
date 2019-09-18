//package com.example.yummy.ViewModel;
//
//import androidx.lifecycle.LiveData;
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.example.yummy.Model.Recipe;
//import com.example.yummy.Model.Recipes;
//import com.example.yummy.Repository.RecipeRepository;
//
//public class RecipeViewModel extends ViewModel {
//
//    private RecipeRepository recipeRepository;
//    private MutableLiveData<LiveData<LiveData<Recipes>>> recipes;
//
//
//    public RecipeViewModel() {
//        recipeRepository = RecipeRepository.getInstance();
//
//    }
//
//    public LiveData<LiveData<LiveData<Recipes>>> search(String searchTerm) {
//        LiveData<LiveData<Recipes>> search = recipeRepository.search(searchTerm);
//        recipes.setValue(search);
//        return recipes;
//
//
//    }
//    public LiveData<LiveData<Recipe>> getRecipe(String recipeId) {
//        return recipeRepository.getRecipe(recipeId);
//    }
//
//
//}
