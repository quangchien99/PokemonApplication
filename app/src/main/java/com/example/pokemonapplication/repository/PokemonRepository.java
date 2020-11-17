package com.example.pokemonapplication.repository;

import androidx.lifecycle.LiveData;

import com.example.pokemonapplication.db.PokemonDAO;
import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.model.PokemonResponse;
import com.example.pokemonapplication.network.PokemonService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class PokemonRepository {
    private PokemonDAO pokemonDAO;
    private PokemonService pokemonService;

    @Inject
    public PokemonRepository(PokemonDAO pokemonDAO, PokemonService pokemonService) {
        this.pokemonDAO = pokemonDAO;
        this.pokemonService = pokemonService;
    }

    public Observable<PokemonResponse> getPokemons() {
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
