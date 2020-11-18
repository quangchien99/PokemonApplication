package com.example.pokemonapplication.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapplication.adapter.PokemonListAdapter;
import com.example.pokemonapplication.databinding.FragmentPokemonBinding;
import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;

public class PokemonFavoriteFragment extends Fragment {
    private static PokemonFavoriteFragment INSTANCE;
    private List<Pokemon> pokemons;
    private PokemonListAdapter pokemonAdapter;
    private PokemonViewModel pokemonViewModel;
    private FragmentPokemonBinding fragmentPokemonBinding;

    public static PokemonFavoriteFragment getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PokemonFavoriteFragment();
        }
        return INSTANCE;
    }

    public PokemonFavoriteFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentPokemonBinding = FragmentPokemonBinding.inflate(inflater, container, false);
        pokemons = new ArrayList<>();
        pokemonAdapter = new PokemonListAdapter();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        fragmentPokemonBinding.rvPokemon.setLayoutManager(layoutManager);
        fragmentPokemonBinding.rvPokemon.setAdapter(pokemonAdapter);

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        pokemonViewModel.getmFavoritePokemons().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> data) {
                pokemons.clear();
                pokemons.addAll(data);
                pokemonAdapter.submitList(data);
                Log.d("qcpTag", "FavoriteFragment: Data changed with size is: " + pokemons.size());
            }
        });
        setUpSwipeItem();
        return fragmentPokemonBinding.getRoot();
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
                Toast.makeText(getContext(), "Pokemon - " + pokemon.getName() + "is not favorite anymore.", Toast.LENGTH_SHORT).show();
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(fragmentPokemonBinding.rvPokemon);
    }
}
