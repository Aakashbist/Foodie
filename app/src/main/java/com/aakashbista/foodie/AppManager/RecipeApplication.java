package com.aakashbista.foodie.AppManager;

import android.app.Application;
import android.util.Log;

import com.aakashbista.foodie.Repository.INoteRepository;
import com.aakashbista.foodie.Repository.IRecipeRepository;
import com.aakashbista.foodie.Repository.MockRecipeRepository;
import com.aakashbista.foodie.Repository.NoteRepository;

public class RecipeApplication extends Application implements ApplicationServiceManager {
    private IRecipeRepository recipeRepository;
    private INoteRepository noteRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("RecipeApplication", "onCreate: ");
    }

    @Override
    public IRecipeRepository getRecipeRepository() {
        if (recipeRepository != null) {
            return recipeRepository;
        } else {
            recipeRepository = MockRecipeRepository.getInstance(this);
            return recipeRepository;
        }
    }

    @Override
    public INoteRepository getNoteRepository() {
        if (noteRepository != null) {
            return noteRepository;
        } else {
            noteRepository = NoteRepository.getInstance(this);
            return noteRepository;
        }
    }


}


