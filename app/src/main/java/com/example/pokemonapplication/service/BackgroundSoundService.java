package com.example.pokemonapplication.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.pokemonapplication.R;

public class BackgroundSoundService extends Service {
    private MediaPlayer player;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        player = MediaPlayer.create(this, R.raw.pokemon_theme_song);
        player.setLooping(true);
        player.setVolume(70, 70);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();

        Log.d("qcpTag", "Background music starts...");
        return Service.START_NOT_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
    }

    @Override
    public void onLowMemory() {

    }
}
