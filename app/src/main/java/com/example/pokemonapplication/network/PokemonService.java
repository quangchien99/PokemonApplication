package com.example.pokemonapplication.network;

import com.example.pokemonapplication.model.PokemonResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokemonService {
    @GET("pokemon")
    Call<PokemonResponse> getPokemons();
}
