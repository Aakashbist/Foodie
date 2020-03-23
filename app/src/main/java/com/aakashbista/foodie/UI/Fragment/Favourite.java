package com.aakashbista.foodie.UI.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aakashbista.foodie.Adapter.FavouriteAdapter;
import com.aakashbista.foodie.Model.Recipe;
import com.aakashbista.foodie.R;
import com.aakashbista.foodie.ViewModel.RecipeViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Favourite extends Fragment {


    private RecyclerView recyclerView;
    private FavouriteAdapter favouriteAdapter;
    private RecipeViewModel viewModel;

    public Favourite() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getFavourite();
        viewModel.favouriteRecipes.observe(getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null) {
                    favouriteAdapter.setItems(recipes);
                }
            }
        });
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerView = view.findViewById(R.id.favouriteRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        favouriteAdapter = new FavouriteAdapter();
        recyclerView.setAdapter(favouriteAdapter);
        return view;
    }


}
