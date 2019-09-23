package com.example.yummy.Fragment;


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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Adapter.RecipeListAdapter;
import com.example.yummy.Model.Recipe;
import com.example.yummy.Network.ApiClient;
import com.example.yummy.R;
import com.example.yummy.Repository.IRecipeRepository;
import com.example.yummy.Repository.RecipeRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Explore extends Fragment {


    private RecyclerView recyclerView;
    private RecipeListAdapter recipeListAdapter;
    private IRecipeRepository repository;
    private LiveData<List<Recipe>> recipesSearchResult;

    private androidx.appcompat.widget.SearchView searchView;


    public Explore() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchRecipes(null);
        recyclerView = view.findViewById(R.id.recipe_list_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recipeListAdapter = new RecipeListAdapter();
        recyclerView.setAdapter(recipeListAdapter);

        recipesSearchResult.observe(this.getViewLifecycleOwner(), new Observer<List<Recipe>>() {
            @Override
            public void onChanged(List<Recipe> recipes) {
                if (recipes != null) {
                    recipeListAdapter.setItems(recipes);
                }
            }
        });


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
        //repository = MockRecipeRepository.getInstance(this.getContext());
        repository = new RecipeRepository(ApiClient.getRecipeService());
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);


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

                    //todo if you like to search after typing full word
//                    searchRecipes(query);
//                    Toast.makeText(getContext(),"From explore fragment " + query ,Toast.LENGTH_SHORT).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    searchRecipes(newText);
                    return true;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchRecipes(String searchTerm) {
        recipesSearchResult = repository.searchRecipes(searchTerm);
    }

}
