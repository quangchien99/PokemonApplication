package com.example.pokemonapplication.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.pokemonapplication.model.Pokemon;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDAO getPokemonDAO();
}
