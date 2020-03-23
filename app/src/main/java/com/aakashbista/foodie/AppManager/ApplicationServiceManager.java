package com.aakashbista.foodie.AppManager;

import com.aakashbista.foodie.Repository.INoteRepository;
import com.aakashbista.foodie.Repository.IRecipeRepository;

public interface ApplicationServiceManager {
   IRecipeRepository getRecipeRepository();
   INoteRepository getNoteRepository();

}
