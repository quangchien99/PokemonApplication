package com.example.pokemonapplication.viewmodel;


import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.repository.PokemonRepository;
import com.example.pokemonapplication.utils.Const;

import java.util.List;


public class PokemonViewModel extends ViewModel {
    PokemonRepository pokemonRepository;

    private MutableLiveData<List<Pokemon>> mNetworkPokemons = new MutableLiveData<>();
    private LiveData<List<Pokemon>> mFavoritePokemons = null;

    @ViewModelInject
    public PokemonViewModel(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.mFavoritePokemons = pokemonRepository.getFavoritePokemons();
    }

    public MutableLiveData<List<Pokemon>> getmNetworkPokemons() {
        return mNetworkPokemons;
    }

    public LiveData<List<Pokemon>> getmFavoritePokemons() {
        return mFavoritePokemons;
    }

    public void insertPokemon(Pokemon pokemon) {
        pokemonRepository.insertPokemon(pokemon);
    }

    public void deleteOnePokemon(String name) {
        pokemonRepository.deletePokemon(name);
    }

    public void deleteAllPokemons() {
        pokemonRepository.deleteAllPokemons();
    }

    public void fetchPokemonFromNetwork() {
        pokemonRepository.getPokemons()
                .map(pokemonResponse -> {
                    List<Pokemon> pokemons = pokemonResponse.getResults();
                    for (Pokemon pokemon : pokemons) {
                        String url = pokemon.getUrl();
                        String[] index = url.split("/");
                        String imageName = index[index.length - 1] + ".png";
                        pokemon.setUrl(Const.IMAGE_URL + imageName);
                    }
                    return pokemons;
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        result -> mNetworkPokemons.postValue(result)
                );
    }
}
