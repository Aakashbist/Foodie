package com.example.yummy.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.Adapter.IngredentAdapter;
import com.example.yummy.ApplicationServiceManager;
import com.example.yummy.Model.RecipeWithIngredints;
import com.example.yummy.R;
import com.example.yummy.RecipeApplication;
import com.example.yummy.Repository.IRecipeRepository;
import com.example.yummy.Repository.MockRecipeRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipeDetailFragment extends Fragment {
    private RecyclerView recyclerView;
    private IngredentAdapter ingredentAdapter;
    private LiveData<RecipeWithIngredints> recipe;
    private IRecipeRepository repository;
    private String recipeId;

    List<String> list = new ArrayList<>();

    private ImageView imageView;
    private ImageView addToFavouriteImageView;
    private TextView titleTextView;
    private TextView publisherTextView;


    public RecipeDetailFragment() {

        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context applicationContext = getContext().getApplicationContext();
        ApplicationServiceManager manager = (RecipeApplication) applicationContext;
        repository = manager.getRepository();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_recipe_detail, container, false);
        //find views

        imageView = view.findViewById(R.id.recipeImage);
        addToFavouriteImageView = view.findViewById(R.id.addtofavourite);
        titleTextView = view.findViewById(R.id.recipeTitle);
        publisherTextView = view.findViewById(R.id.recipePublisher);
        addToFavourite(view);

        //set up recycler view
        recyclerView = view.findViewById(R.id.recipeDetailRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ingredentAdapter = new IngredentAdapter();
        recyclerView.setAdapter(ingredentAdapter);

        //todo change id as needed

        recipeId = getArguments().getString("recipeId");
        if (recipeId != null) {
            getDetails(recipeId);
            if (repository.isFavourite(recipeId)) {
                addToFavouriteImageView.setColorFilter(Color.RED);
                addToFavouriteImageView.setEnabled(false);
            } else {
                addToFavouriteImageView.setColorFilter(Color.GRAY);
            }
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recipe.observe(this.getViewLifecycleOwner(), new Observer<RecipeWithIngredints>() {
            @Override
            public void onChanged(RecipeWithIngredints recipeItem) {
                if (recipeItem != null) {
                    ingredentAdapter.setItem(recipeItem.getIngredients());
                    Glide.with(RecipeDetailFragment.this).load(recipeItem.getImageUrl()).into(imageView);
                    titleTextView.setText(recipeItem.getTitle());
                    publisherTextView.setText(recipeItem.getPublisher());
                }
            }
        });
    }

    private void getDetails(String recipeId) {
        recipe = repository.getRecipeDetail(recipeId);
    }


    public void addToFavourite(View view) {
        addToFavouriteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (recipeId != null) {
                    repository.addToFavourite(recipeId);
                }
                addToFavouriteImageView.setColorFilter(Color.RED);
                addToFavouriteImageView.setEnabled(false);
            }
        });

    }


}
