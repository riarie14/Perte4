package com.example.pert3;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ViewHolder> {
    private ArrayList<Hero> listHero;
    private int type;
    public HeroAdapter(ArrayList<Hero> list,int type) {
        this.listHero = list;
        this.type = type;
    }

    @NonNull
    @Override
    public HeroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRow;

        switch (type) {
            case 1 :
                itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kolom_hero,parent, false);
                return new ViewHolder(itemRow);
            case 2 :
                itemRow = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row_hero,parent, false);
                return new ViewHolder(itemRow);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final HeroAdapter.ViewHolder holder, int position) {
        final Hero hero = listHero.get(position);
        Glide.with(holder.itemView.getContext())
                .load(hero.getFoto())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgFoto);
        holder.tvNama.setText(hero.getNama());
        if (type == 2){
            holder.tvDetail.setText(hero.getDetail());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(),hero.getNama(),Toast.LENGTH_SHORT).show();
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Bundle bundle = new Bundle();
                bundle.putParcelable("Data",hero);
                DetailFragment fragmentDetail = new DetailFragment();
                fragmentDetail.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragmentDetail, fragmentDetail.getClass().getSimpleName())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listHero.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFoto;
        TextView tvNama, tvDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFoto = itemView.findViewById(R.id.ivFoto);
            tvDetail = itemView.findViewById(R.id.tvDetail);
            tvNama = itemView.findViewById(R.id.tvNama);
        }
    }
}
