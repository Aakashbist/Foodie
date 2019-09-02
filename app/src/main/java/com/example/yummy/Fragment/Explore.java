package com.example.yummy.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yummy.Adapter.RecipeListAdapter;
import com.example.yummy.Data.RecipeService;
import com.example.yummy.Model.Recipes;
import com.example.yummy.Model.SearchRecipeItem;
import com.example.yummy.Network.ApiClient;
import com.example.yummy.Network.IRecipeService;
import com.example.yummy.R;
import com.example.yummy.Repository.RecipeServiceRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Explore extends Fragment {

    private  IRecipeService iRecipeService;
    private RecyclerView recyclerView;
    private RecipeListAdapter recipeListAdapter;
    private RecipeServiceRepository recipeServiceRepository = new RecipeServiceRepository(iRecipeService=new RecipeService());


private TextView textView;
    public Explore() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        Call<Recipes> search = recipeServiceRepository.search(null);
        recyclerView=view.findViewById(R.id.recipe_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recipeListAdapter=new RecipeListAdapter();
        recyclerView.setAdapter(recipeListAdapter);
        return view;
    }

    private void getRecipes(String searchTerm) {

        Call<Recipes>  call = ApiClient.getRecipeService().search(searchTerm);
        call.enqueue(new Callback<Recipes>() {
            @Override
            public void onResponse(Call<Recipes> call, Response<Recipes> response) {
                int size=response.body().getRecipes().size();
                if(size>0)
                {
                    textView.setText(" size = "+ size);
                }
                else{
                    textView.setText("no reciepe returned");
                }
            }

            @Override
            public void onFailure(Call<Recipes> call, Throwable t) {
                textView.setText(t.toString());
            }
        });
    }

}
