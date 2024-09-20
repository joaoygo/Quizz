package com.example.quizz.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz.Models.Categories;
import com.example.quizz.NiveisActivity;
import com.example.quizz.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    Context context;
    ArrayList<Categories> listCategories;

    public CategoriesAdapter(Context context, ArrayList<Categories> listCategories) {
        this.context = context;
        this.listCategories = listCategories;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_categories,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {
        Categories categories = listCategories.get(position);
        holder.name.setText(categories.getName());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, NiveisActivity.class);
            intent.putExtra("categoryName", categories.getName());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return listCategories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
        }
    }
}
