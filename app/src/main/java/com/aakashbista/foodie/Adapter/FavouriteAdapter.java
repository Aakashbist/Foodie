package com.aakashbista.foodie.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.aakashbista.foodie.Model.Recipe;
import com.aakashbista.foodie.R;
import com.aakashbista.foodie.UI.Fragment.FavouriteDirections;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
    private List<Recipe> favouriteRecipe = new ArrayList<>();

    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_list_item, parent, false);
        FavouriteViewHolder favouriteViewHolder = new FavouriteViewHolder(view);
        return favouriteViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
        holder.setFavourite(favouriteRecipe.get(position));

    }

    @Override
    public int getItemCount() {
        if (favouriteRecipe != null && favouriteRecipe.size() > 0) {
            return favouriteRecipe.size();
        }
        return 0;
    }

    public void setItems(List<Recipe> recipes) {
        favouriteRecipe = recipes;
        notifyDataSetChanged();
    }


    public class FavouriteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView titleTextView;
        private TextView socialTextView;
        private TextView publisherTextView;
        private String recipeId;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageView = itemView.findViewById(R.id.recipeImage);
            titleTextView = itemView.findViewById(R.id.recipeTitle);
            socialTextView = itemView.findViewById(R.id.recipeSocial);
            publisherTextView = itemView.findViewById(R.id.recipePublisher);
        }

        public void setFavourite(Recipe recipe) {
            recipeId = recipe.getRecipeId();
            String url = recipe.getImageUrl();
            RequestOptions requestOptions = new RequestOptions();
            requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(16));
            Glide.with(itemView.getContext()).load(url).centerCrop().apply(requestOptions).
                placeholder(R.drawable.ic_launcher_foreground).into(imageView);

            titleTextView.setText(recipe.getTitle());
            socialTextView.setText(String.valueOf(recipe.getSocialRank()));
            publisherTextView.setText(recipe.getPublisher());
        }

        @Override
        public void onClick(View view) {
            if (recipeId != null) {
                FavouriteDirections.ActionFavouriteFragmentToRecipeDetailFragment  action=FavouriteDirections.actionFavouriteFragmentToRecipeDetailFragment();
                action.setRecipeId(recipeId);
                Navigation.findNavController(view).navigate(action);
            }

        }


    }
}
