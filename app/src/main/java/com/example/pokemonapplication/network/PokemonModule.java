package com.example.pokemonapplication.network;

import com.example.pokemonapplication.utils.Const;

public class PokemonModule {
    private static PokemonService INSTANCE;

    private PokemonModule() {

    }

    public static PokemonService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = PokemonClient.getInstance(Const.BASE_URL).create(PokemonService.class);
        }
        return INSTANCE;
    }
}
