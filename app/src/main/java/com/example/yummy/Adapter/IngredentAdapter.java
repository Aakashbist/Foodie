package com.example.yummy.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yummy.R;

import java.util.List;

public class IngredentAdapter extends RecyclerView.Adapter<IngredentAdapter.IngredentViewHolder> {

   private List<String> ingredents;

    @NonNull
    @Override
    public IngredentAdapter.IngredentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        //inflate custom view
        View view = inflater.inflate(R.layout.ingredents_list_item, parent, false);
        IngredentViewHolder ingredentViewHolder = new IngredentViewHolder(view);
        return ingredentViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull IngredentAdapter.IngredentViewHolder holder, int position) {
      holder.setIngredent(ingredents.get(position));

    }

    @Override
    public int getItemCount() {

        if(ingredents!=null && ingredents.size()>0){
            return ingredents.size();
        }
        return 0;
    }

    public void setItem(List<String> ingredients) {
        ingredents=ingredients;
        notifyDataSetChanged();
    }

    public class IngredentViewHolder extends RecyclerView.ViewHolder {

        private TextView ingredentsView;
        private IngredentViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredentsView=itemView.findViewById(R.id.ingredientsItem);
        }


        public void setIngredent(String string) {
            ingredentsView.setText(string);
        }
    }
}
