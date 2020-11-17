package com.example.pokemonapplication.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapplication.R;
import com.example.pokemonapplication.adapter.PokemonAdapter;
import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

public class PokemonFavoriteFragment extends Fragment {
    private RecyclerView rvPokemon;
    private List<Pokemon> pokemons;
    private PokemonAdapter pokemonAdapter;
    private PokemonViewModel pokemonViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pokemon, container, false);
        rvPokemon = view.findViewById(R.id.rv_pokemon);
        pokemons = new ArrayList<>();
        pokemonAdapter = new PokemonAdapter(pokemons, getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvPokemon.setLayoutManager(layoutManager);
        rvPokemon.setAdapter(pokemonAdapter);

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        pokemonViewModel.getmFavoritePokemons().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> data) {
                pokemons.clear();
                pokemons.addAll(data);
                pokemonAdapter.notifyDataSetChanged();
            }
        });
        setUpSwipeItem();
        return view;
    }

    private void setUpSwipeItem() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemons.get(position);
                pokemonViewModel.deleteOnePokemon(pokemon.getName());
                pokemonAdapter.notifyDataSetChanged();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(rvPokemon);
    }
}
