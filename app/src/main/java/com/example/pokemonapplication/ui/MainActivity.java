package com.example.pokemonapplication.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.pokemonapplication.R;
import com.example.pokemonapplication.model.Pokemon;
import com.example.pokemonapplication.model.PokemonResponse;
import com.example.pokemonapplication.network.PokemonModule;
import com.example.pokemonapplication.viewmodel.PokemonViewModel;
import com.example.pokemonapplication.viewmodel.PokemonViewModelFactory;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private PokemonViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this, new PokemonViewModelFactory(getApplication()))
                .get(PokemonViewModel.class);
        viewModel.fetchPokemonFromNetwork();
        viewModel.getmNetworkPokemons().observe(this, new Observer<List<Pokemon>>() {
            @Override
            public void onChanged(List<Pokemon> pokemons) {
                Log.d("qcpTag", "Data changed with size is:" + pokemons.size());
            }
        });

//        Log.d("qcpTag","MainActivity().onCreate(): check");
//        //FIXME test retrofit
//        PokemonModule.getInstance().getPokemons().enqueue(new Callback<PokemonResponse>() {
//            @Override
//            public void onResponse(Call<PokemonResponse> call, Response<PokemonResponse> response) {
//                if (response.isSuccessful()){
//                    Log.d("qcpTag","MainActivity().onCreate(): get all pokemons sucessfully. Total: " + response.body().getCount());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PokemonResponse> call, Throwable t) {
//                Log.d("qcpTag","MainActivity().onCreate(): failed to get all pokemons. " + t.getMessage());
//            }
//        });
    }
}