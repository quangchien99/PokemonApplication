package com.example.pokemonapplication.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.pokemonapplication.R;
import com.example.pokemonapplication.adapter.PokemonPagerAdapter;
import com.example.pokemonapplication.databinding.ActivityMainBinding;
import com.example.pokemonapplication.service.BackgroundSoundService;
import com.example.pokemonapplication.viewmodel.PokemonViewModel;
import com.google.android.material.navigation.NavigationView;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private PokemonViewModel viewModel;
    private PokemonPagerAdapter pokemonPagerAdapter;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        startService(new Intent(MainActivity.this, BackgroundSoundService.class));
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        pokemonPagerAdapter = new PokemonPagerAdapter(getSupportFragmentManager());
        activityMainBinding.vpPokemon.setAdapter(pokemonPagerAdapter);
        activityMainBinding.tabLayout.setupWithViewPager(activityMainBinding.vpPokemon);
        activityMainBinding.navPokemon.setNavigationItemSelectedListener(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(MainActivity.this, BackgroundSoundService.class));
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_list: {

                activityMainBinding.vpPokemon.setCurrentItem(0);
                break;
            }
            case R.id.item_favorite: {
                activityMainBinding.vpPokemon.setCurrentItem(1);
                break;
            }
            case R.id.item_about: {
                Toast.makeText(getApplicationContext(), "Quang Chien Pham", Toast.LENGTH_LONG).show();
                break;
            }
        }
        return true;
    }
}