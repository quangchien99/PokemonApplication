package com.example.pokemonapplication.network;

import com.example.pokemonapplication.model.PokemonResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface PokemonService {
    @GET("pokemon")
    Observable<PokemonResponse> getPokemons();
}
