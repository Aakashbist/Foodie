package com.example.yummy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.Model.Recipes;
import com.example.yummy.Model.SearchRecipeItem;
import com.example.yummy.R;

import java.util.List;

import retrofit2.Call;


public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeViewHolder> {
    List<SearchRecipeItem> recipes;

    public RecipeListAdapter(Call<Recipes> search) {
        recipes=search;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        //inflate custom view
        View view=inflater.inflate(R.layout.recipe_list_item,parent,false);
        RecipeViewHolder recyclerViewHolder=new RecipeViewHolder(view);

        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        SearchRecipeItem searchRecipeItem=recipes.get(position);
        ImageView imageView=holder.imageView;
        imageView.setImageResource(R.drawable.ic_favorite);

        TextView textView= holder.textView;
        textView.setText(searchRecipeItem.getTitle());

    }

    @Override
    public int getItemCount() {
        if(recipes.size()>0){

        }
        return 0;
    }

    public class RecipeViewHolder extends RecyclerView.ViewHolder {
       public ImageView imageView;
       public TextView textView;
        public RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.recipeImage);
            textView=itemView.findViewById(R.id.recipeTitle);
        }
    }
}
