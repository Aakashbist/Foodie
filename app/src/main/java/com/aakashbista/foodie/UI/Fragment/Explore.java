package com.aakashbista.foodie.UI.Fragment;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aakashbista.foodie.Adapter.RecipeListAdapter;
import com.aakashbista.foodie.Model.Recipe;
import com.aakashbista.foodie.R;
import com.aakashbista.foodie.ViewModel.RecipeViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Explore extends Fragment {


    private RecyclerView recyclerView;
    private RecipeListAdapter recipeListAdapter;
    private RecipeViewModel viewModel;
    private androidx.appcompat.widget.SearchView searchView;


    public Explore() {

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.searchRecipes(null);
        recyclerView = view.findViewById(R.id.recipe_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recipeListAdapter = new RecipeListAdapter();
        recyclerView.setAdapter(recipeListAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_explore, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.recipes.observe(this.getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null) {
                    recipeListAdapter.setItems(recipes);
                }
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.appbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.appbar_search);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        }
        if (searchItem != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    viewModel.searchRecipes(newText);
                    return true;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }


}
