package com.example.pokemonapplication.utils;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.pokemonapplication.R;

public class BindingAdapter {
    @androidx.databinding.BindingAdapter("set_src")
    public static void setImage(ImageView imgView, String link) {
        Glide.with(imgView).load(link)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(imgView);
    }
}
