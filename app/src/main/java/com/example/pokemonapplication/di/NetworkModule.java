package com.example.pokemonapplication.di;

import com.example.pokemonapplication.network.PokemonService;
import com.example.pokemonapplication.utils.Const;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkModule {

    @Provides
    public static GsonConverterFactory provideGSONConvertFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    public static Retrofit provideRetrofit(GsonConverterFactory converterFactory) {
        return new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Provides
    public static PokemonService providePokemonService(Retrofit retrofit) {
        return retrofit.create(PokemonService.class);
    }
}
