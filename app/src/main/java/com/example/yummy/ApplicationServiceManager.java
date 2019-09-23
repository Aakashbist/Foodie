package com.example.yummy;

import com.example.yummy.Repository.IRecipeRepository;

public interface ApplicationServiceManager {
   IRecipeRepository getRepository();

}
