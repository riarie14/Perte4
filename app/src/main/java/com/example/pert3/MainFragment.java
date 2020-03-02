package com.example.pert3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private RecyclerView rvHeroes,rvHorizontal;
    private ArrayList<Hero> list = new ArrayList<>();


    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_main, container, false);
        rvHeroes = view.findViewById(R.id.rv_Heroes);
        rvHorizontal = view.findViewById(R.id.rv_list);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvHeroes.setHasFixedSize(true);
        rvHorizontal.setHasFixedSize(true);

        list.addAll(HeroData.getListData());
        showRecyclerList();
    }
    private void showRecyclerList(){
        rvHeroes.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvHorizontal.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        HeroAdapter listHeroAdapterHorizontal = new HeroAdapter(list, 1);
        HeroAdapter listHeroAdapter = new HeroAdapter(list, 2);
        rvHorizontal.setAdapter(listHeroAdapterHorizontal);
        rvHeroes.setAdapter(listHeroAdapter);
    }

}
