package com.example.yummy.AppManager;

import com.example.yummy.Repository.INoteRepository;
import com.example.yummy.Repository.IRecipeRepository;

public interface ApplicationServiceManager {
   IRecipeRepository getRecipeRepository();
   INoteRepository getNoteRepository();

}
