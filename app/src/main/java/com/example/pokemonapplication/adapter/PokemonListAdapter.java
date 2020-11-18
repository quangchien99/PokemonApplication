package com.example.pokemonapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapplication.databinding.ItemPokemonBinding;
import com.example.pokemonapplication.model.Pokemon;

public class PokemonListAdapter extends ListAdapter<Pokemon, PokemonListAdapter.ViewHolder> {
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

    public PokemonListAdapter() {
        super(DIFF_CALLBACK);
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
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPokemonBinding itemPokemonBinding;

        public ViewHolder(ItemPokemonBinding itemPokemonBinding) {
            super(itemPokemonBinding.getRoot());
            this.itemPokemonBinding = itemPokemonBinding;
        }

        public void bind(Pokemon pokemon) {
            itemPokemonBinding.setPokemon(pokemon);
            itemPokemonBinding.executePendingBindings();
        }
    }
}
