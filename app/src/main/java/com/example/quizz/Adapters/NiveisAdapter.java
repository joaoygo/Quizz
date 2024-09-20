package com.example.quizz.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizz.Models.Nivel;
import com.example.quizz.R;

import java.util.ArrayList;

public class NiveisAdapter extends RecyclerView.Adapter<NiveisAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Nivel> listNiveis;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Nivel nivel);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public NiveisAdapter(Context context, ArrayList<Nivel> listNiveis) {
        this.context = context;
        this.listNiveis = listNiveis;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.card_nivel, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Nivel nivel = listNiveis.get(position);
        holder.name.setText(nivel.getName());

        // Configure o clique do item
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(nivel);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listNiveis.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewNivel);
        }
    }
}
