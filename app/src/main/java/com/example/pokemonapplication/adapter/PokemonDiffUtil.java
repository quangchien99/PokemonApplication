package com.example.pokemonapplication.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.example.pokemonapplication.model.Pokemon;

import java.util.List;

public class PokemonDiffUtil extends DiffUtil.Callback {
    private List<Pokemon> oldData;
    private List<Pokemon> newData;

    public PokemonDiffUtil(List<Pokemon> oldData, List<Pokemon> newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    @Override
    public int getOldListSize() {
        return oldData.size();
    }

    @Override
    public int getNewListSize() {
        return newData.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition) == newData.get(newItemPosition);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
    }
}
