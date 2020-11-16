package com.example.pokemonapplication.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pokemonapplication.model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPokemon(Pokemon pokemon);

    @Query("DELETE FROM favorite WHERE name= :name")
    void deleteOnePokemon(String name);

    @Query("DELETE FROM favorite")
    void deleteAllPokemons();

    @Query("SELECT * FROM favorite")
    LiveData<List<Pokemon>> getAllPokemons();
}
