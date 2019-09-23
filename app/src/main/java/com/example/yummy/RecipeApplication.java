package com.example.yummy;

import android.app.Application;
import android.util.Log;

import com.example.yummy.Network.IRecipeService;
import com.example.yummy.Repository.IRecipeRepository;
import com.example.yummy.Repository.MockRecipeRepository;

public class RecipeApplication extends Application implements ApplicationServiceManager {
    private IRecipeRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("RecipeApplication", "onCreate: ");
    }

    @Override
    public IRecipeRepository getRepository() {
        if (repository != null) {
            return repository;
        } else {
            repository = MockRecipeRepository.getInstance(this);
            return repository;
        }
    }


}


