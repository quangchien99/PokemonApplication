package com.example.pokemonapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;


import com.example.pokemonapplication.R;
import com.example.pokemonapplication.adapter.PokemonPagerAdapter;
import com.example.pokemonapplication.viewmodel.PokemonViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private PokemonViewModel viewModel;
    private ViewPager vpPokemon;
    private TabLayout tabPokemon;
    private NavigationView navPokemon;
    private DrawerLayout drawerLayout;
    private PokemonPagerAdapter pokemonPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        pokemonPagerAdapter = new PokemonPagerAdapter(getSupportFragmentManager());
        vpPokemon.setAdapter(pokemonPagerAdapter);
        tabPokemon.setupWithViewPager(vpPokemon);
    }

    private void findViewById() {
        vpPokemon = findViewById(R.id.vp_pokemon);
        tabPokemon = findViewById(R.id.tab_layout);
        navPokemon = findViewById(R.id.nav_pokemon);
        drawerLayout = findViewById(R.id.layout_drawer);
    }
}