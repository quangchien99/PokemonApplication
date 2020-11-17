package com.example.pokemonapplication.db;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pokemonapplication.model.Pokemon;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class PokemonDatabase extends RoomDatabase {
    public abstract PokemonDAO getPokemonDAO();

    public static PokemonDatabase getPokemonDatabase(Application application) {
        return Room.databaseBuilder(application.getApplicationContext(),
                PokemonDatabase.class, "pokemon")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }
}
