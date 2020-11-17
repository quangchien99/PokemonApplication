package com.example.pokemonapplication.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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
        pokemonRepository.getPokemons().enqueue(new Callback<PokemonResponse>() {
            @Override
            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("qcpTag", "PokemonViewModel.fetchPokemonFromNetwork(): succeeded!");
                    List<Pokemon> pokemons = response.body().getResults();
                    for (Pokemon pokemon : pokemons) {
                        String url = pokemon.getUrl();
                        String[] index = url.split("/");
                        String imageName = index[index.length - 1] + ".png";
                        pokemon.setUrl(Const.IMAGE_URL + imageName);
                    }
                    mNetworkPokemons.postValue(pokemons);
                }
            }

            @Override
            public void onFailure(Call<PokemonResponse> call, Throwable t) {
                Log.d("qcpTag", "PokemonViewModel.fetchPokemonFromNetwork(): failed!");
            }
        });
    }
}
