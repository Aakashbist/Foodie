package com.example.yummy.AppManager;

import android.app.Application;
import android.util.Log;

import com.example.yummy.Repository.INoteRepository;
import com.example.yummy.Repository.IRecipeRepository;
import com.example.yummy.Repository.MockRecipeRepository;
import com.example.yummy.Repository.NoteRepository;

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


