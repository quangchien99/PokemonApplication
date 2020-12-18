package com.example.pokemonapplication.adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapplication.databinding.ItemPokemonBinding;
import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.utils.ItemClickedListener;

public class PokemonListAdapter extends ListAdapter<Pokemon, PokemonListAdapter.ViewHolder> {
    private Context context;
    private static final DiffUtil.ItemCallback<Pokemon> DIFF_CALLBACK = new DiffUtil.ItemCallback<Pokemon>() {
        @Override
        public boolean areItemsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Pokemon oldItem, @NonNull Pokemon newItem) {
            return oldItem.equals(newItem);
        }
    };

    public PokemonListAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPokemonBinding itemPokemonBinding = ItemPokemonBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(itemPokemonBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
        holder.setItemClickedListener(new ItemClickedListener() {
            @Override
            public void onItemClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(context, "Long Click in position: " + position, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(context, "Short Click in position: " + position, Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        ItemPokemonBinding itemPokemonBinding;
        ItemClickedListener itemClickedListener;

        public ViewHolder(ItemPokemonBinding itemPokemonBinding) {
            super(itemPokemonBinding.getRoot());
            this.itemPokemonBinding = itemPokemonBinding;
            itemPokemonBinding.tvName.setOnClickListener(this);
            itemPokemonBinding.tvName.setOnLongClickListener(this);
            itemPokemonBinding.imgAvatar.setOnClickListener(this);
            itemPokemonBinding.imgAvatar.setOnLongClickListener(this);

        }

        public void bind(Pokemon pokemon) {
            itemPokemonBinding.setPokemon(pokemon);
            itemPokemonBinding.executePendingBindings();
        }

        public void setItemClickedListener(ItemClickedListener itemClickedListener) {
            this.itemClickedListener = itemClickedListener;
        }

        @Override
        public void onClick(View v) {
            itemClickedListener.onItemClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickedListener.onItemClick(v, getAdapterPosition(), true);
            return false;
        }
    }
}
