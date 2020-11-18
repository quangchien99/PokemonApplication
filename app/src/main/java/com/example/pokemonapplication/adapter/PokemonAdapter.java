package com.example.pokemonapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapplication.databinding.ItemPokemonBinding;
import com.example.pokemonapplication.model.Pokemon;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {
    private List<Pokemon> pokemons;
    private Context context;

    public PokemonAdapter(List<Pokemon> pokemons, Context context) {
        this.pokemons = pokemons;
        this.context = context;
    }

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemPokemonBinding itemPokemonBinding = ItemPokemonBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(itemPokemonBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonAdapter.ViewHolder holder, int position) {
        holder.bind(pokemons.get(position));
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemPokemonBinding itemPokemonBinding;

        public ViewHolder(ItemPokemonBinding itemPokemonBinding) {
            super(itemPokemonBinding.getRoot());
            this.itemPokemonBinding = itemPokemonBinding;
            this.itemPokemonBinding.executePendingBindings();
        }

        public void bind(Pokemon pokemon) {
            itemPokemonBinding.setPokemon(pokemon);
        }
    }
}
