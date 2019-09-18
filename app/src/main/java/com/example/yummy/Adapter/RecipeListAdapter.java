package com.example.yummy.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yummy.Model.Recipe;
import com.example.yummy.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {

    private List<Recipe> recipes = new ArrayList<>();


    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //inflate custom view
        View view = inflater.inflate(R.layout.recipe_list_item, parent, false);
        RecipeViewHolder recipeViewHolder = new RecipeViewHolder(view);
        return recipeViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe searchRecipeItem = recipes.get(position);
        holder.setRecipe(searchRecipeItem);

    }

    @Override
    public int getItemCount() {
        if (recipes != null) {
            return recipes.size();
        }
        return 0;
    }

    public void setItems(List<Recipe> items) {
        recipes = items;
        notifyDataSetChanged();
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView titleTextView;
        private TextView socialTextView;
        private TextView publisherTextView;


        private RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            imageView = itemView.findViewById(R.id.recipeImage);
            titleTextView = itemView.findViewById(R.id.recipeTitle);
            socialTextView = itemView.findViewById(R.id.recipeSocial);
            publisherTextView = itemView.findViewById(R.id.recipePublisher);
        }

        public void setRecipe(@NotNull Recipe searchRecipeItem) {
            String url = searchRecipeItem.getImageUrl();

            Glide.with(itemView.getContext()).load(url).placeholder(R.drawable.ic_launcher_foreground).into(imageView);

            titleTextView.setText(searchRecipeItem.getTitle());
            socialTextView.setText(searchRecipeItem.getSourceUrl());
            publisherTextView.setText(searchRecipeItem.getRecipeId());

        }


        @Override
        public void onClick(View view) {
            String recipeId = publisherTextView.getText().toString();
            Bundle bundle = new Bundle();

            //todo pass recipe id from above
            bundle.putString("recipeId", recipeId);
            Navigation.findNavController(view).navigate(R.id.recipeDetailFragment, bundle);
        }
    }


}
