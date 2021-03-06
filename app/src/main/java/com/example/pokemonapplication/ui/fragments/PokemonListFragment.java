package com.example.pokemonapplication.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonapplication.R;
import com.example.pokemonapplication.adapter.PokemonListAdapter;
import com.example.pokemonapplication.databinding.FragmentPokemonBinding;
import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.viewmodel.PokemonViewModel;

import java.util.ArrayList;
import java.util.List;


public class PokemonListFragment extends Fragment {
    private static PokemonListFragment INSTANCE;
    private List<Pokemon> pokemons;
    private PokemonListAdapter pokemonAdapter;
    private PokemonViewModel pokemonViewModel;
    private FragmentPokemonBinding fragmentPokemonBinding;

    public static PokemonListFragment getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new PokemonListFragment();
        }
        return INSTANCE;
    }

    public PokemonListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentPokemonBinding = FragmentPokemonBinding.inflate(inflater, container, false);
        pokemons = new ArrayList<>();
        pokemonAdapter = new PokemonListAdapter(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        fragmentPokemonBinding.rvPokemon.setLayoutManager(layoutManager);
        fragmentPokemonBinding.rvPokemon.setAdapter(pokemonAdapter);

        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        pokemonViewModel.fetchPokemonFromNetwork();
        pokemonViewModel.getmNetworkPokemons().observe(getViewLifecycleOwner(), new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> data) {
                pokemons.clear();
                pokemons.addAll(data);
                pokemonAdapter.submitList(data);
                Log.d("qcpTag", "ListFragment: Data changed with size is: " + pokemons.size());
            }
        });
        setUpSwipeItem();
        return fragmentPokemonBinding.getRoot();
    }

//    @Override
//    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        MenuInflater menuInflater = getActivity().getMenuInflater();
//        menuInflater.inflate(R.menu.menu_long_press_in_list, menu);
//    }
//
//    @Override
//    public boolean onContextItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.item_show) {
//
//        } else if (item.getItemId() == R.id.item_add_to_favorite) {
//
//        } else {
//            return false;
//        }
//        return true;
//    }

    private void setUpSwipeItem() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                Pokemon pokemon = pokemons.get(position);
                boolean isExist = false;
                for (Pokemon p : pokemonViewModel.getmFavoritePokemons().getValue()) {
                    if (p.getName().equals(pokemon.getName())) {
                        isExist = true;
                        continue;
                    }
                }
                if (isExist) {
                    Toast.makeText(getContext(), "Pokemon - " + pokemon.getName() + " already is favorite.", Toast.LENGTH_SHORT).show();
                    pokemonAdapter.notifyDataSetChanged();
                } else {
                    pokemonViewModel.insertPokemon(pokemon);
                    Toast.makeText(getContext(), "Pokemon - " + pokemon.getName() + "is now favorite.", Toast.LENGTH_SHORT).show();
                    pokemonAdapter.notifyDataSetChanged();
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(fragmentPokemonBinding.rvPokemon);
    }
}
