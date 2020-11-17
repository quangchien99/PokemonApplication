package com.example.pokemonapplication.di;

import android.app.Application;

import androidx.room.Room;

import com.example.pokemonapplication.db.PokemonDAO;
import com.example.pokemonapplication.db.PokemonDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DatabaseModule {
    @Provides
    public static PokemonDatabase providePokemonDB(Application application) {
        return Room.databaseBuilder(
                application.getApplicationContext(),
                PokemonDatabase.class, "pokemon")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    public static PokemonDAO providePokemonDAO(PokemonDatabase pokemonDatabase) {
        return pokemonDatabase.getPokemonDAO();
    }
}
