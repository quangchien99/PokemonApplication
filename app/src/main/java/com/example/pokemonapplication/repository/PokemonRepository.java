package com.example.pokemonapplication.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.pokemonapplication.db.PokemonDAO;
import com.example.pokemonapplication.db.PokemonDatabase;
import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.model.PokemonResponse;
import com.example.pokemonapplication.network.PokemonModule;
import com.example.pokemonapplication.network.PokemonService;

import java.util.List;

import retrofit2.Call;

public class PokemonRepository {
    private PokemonDAO pokemonDAO;
    private PokemonService pokemonService;

    public PokemonRepository(Application application) {
        this.pokemonDAO = PokemonDatabase.getPokemonDatabase(application).getPokemonDAO();
        this.pokemonService = PokemonModule.getInstance();
    }

    public Call<PokemonResponse> getPokemons() {
        return pokemonService.getPokemons();
    }

    public void insertPokemon(Pokemon pokemon) {
        pokemonDAO.insertPokemon(pokemon);
    }

    public void deletePokemon(String name) {
        pokemonDAO.deleteOnePokemon(name);
    }

    public void deleteAllPokemons() {
        pokemonDAO.deleteAllPokemons();
    }

    public LiveData<List<Pokemon>> getFavoritePokemons() {
        return pokemonDAO.getAllPokemons();
    }
}
