package com.example.pokemonapplication.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.model.PokemonResponse;
import com.example.pokemonapplication.repository.PokemonRepository;
import com.example.pokemonapplication.utils.Const;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokemonViewModel extends ViewModel {
    private PokemonRepository pokemonRepository;

    private MutableLiveData<List<Pokemon>> mNetworkPokemons = new MutableLiveData<>();
    private LiveData<List<Pokemon>> mFavoritePokemons = null;

    public PokemonViewModel(Application application) {
        this.pokemonRepository = new PokemonRepository(application);
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
