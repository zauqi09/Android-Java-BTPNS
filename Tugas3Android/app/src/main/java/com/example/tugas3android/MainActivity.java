package com.example.tugas3android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tugas3android.adapter.BeritaAdapter;
import com.example.tugas3android.model.Berita;
import com.example.tugas3android.viewmodel.BeritaViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Berita> beritaArrayList = new ArrayList<>();
    BeritaAdapter beritaAdapter;
    RecyclerView rvBerita;
    BeritaViewModel beritaViewModel;
    FloatingActionButton refreshTextView, addTextView;
    List<Berita> beritas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
    }

    void findViewById(){
        rvBerita = findViewById(R.id.beritaListView);
        refreshTextView = (FloatingActionButton) findViewById(R.id.refreshTextView);
        addTextView = (FloatingActionButton) findViewById(R.id.addTextView);
    }

    private void initData() {
        if (beritaAdapter == null) {
            beritaAdapter = new BeritaAdapter(MainActivity.this, beritaArrayList);
            rvBerita.setLayoutManager(new LinearLayoutManager(this));
            rvBerita.setAdapter(beritaAdapter);
            rvBerita.setItemAnimator(new DefaultItemAnimator());
            rvBerita.setNestedScrollingEnabled(true);
        } else {
            beritaAdapter.notifyDataSetChanged();
        }
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);

        beritaViewModel.init();
        beritaViewModel.getBeritasRepository().observe(this, beritasResponse -> {
            beritas = beritasResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });


    }
    private void getListBerita(String page, String limit ){
        beritaViewModel.refresh(page,limit);
        beritaViewModel.getBeritasRepository().observe(this, beritasResponse -> {
            beritas = beritasResponse.getData();
            beritaArrayList.clear();
            beritaArrayList.addAll(beritas);
            beritaAdapter.notifyDataSetChanged();
        });
    }
    void onClickGroup(){
        refreshTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListBerita("1","20");
            }
        });
        addTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =
                        new Intent( getApplicationContext(), AddBeritaActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListBerita("1","20");
    }
}