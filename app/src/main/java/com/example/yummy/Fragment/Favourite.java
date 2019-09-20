package com.example.yummy.Fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yummy.Adapter.FavouriteAdapter;
import com.example.yummy.Model.Recipe;
import com.example.yummy.R;
import com.example.yummy.Repository.IRecipeRepository;
import com.example.yummy.Repository.MockRecipeRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favourite extends Fragment {


    private RecyclerView recyclerView;
    private FavouriteAdapter favouriteAdapter;
    private IRecipeRepository repository;
    private LiveData<List<Recipe>> recipesSearchResult;

    public Favourite() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        repository = MockRecipeRepository.getInstance(this.getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipesSearchResult=repository.getFavourite();
        recipesSearchResult.observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if(recipes!=null){
                    favouriteAdapter.setItems(recipes);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        recipesSearchResult=repository.getFavourite();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.favouriteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        favouriteAdapter = new FavouriteAdapter();
        recyclerView.setAdapter(favouriteAdapter);
        return  view;
    }


}
