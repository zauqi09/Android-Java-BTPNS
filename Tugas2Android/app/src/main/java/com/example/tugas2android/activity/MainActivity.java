package com.example.tugas2android.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import com.example.tugas2android.R;
import com.example.tugas2android.adapter.BeritaAdapter;
import com.example.tugas2android.model.Berita;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private static final int CODE_MAIN_ACTIVITY = 69 ;
    ListView beritaListView;
    FloatingActionButton addButton;
    BeritaAdapter beritaAdapter;
    ArrayList<Berita> listBerita = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        onClickGroup();

    }
    void initView(){
        beritaListView = (ListView) findViewById(R.id.beritaListView);
        addButton = (FloatingActionButton) findViewById(R.id.addButton);
        beritaAdapter = new BeritaAdapter(getApplicationContext(),listBerita);
        beritaListView.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.v("request Code: ",String.valueOf(requestCode));
        Log.v("resutl: ","isinya: "+data.getStringExtra("data"));
        parse(data.getStringExtra("data"));

    }


    private void parse( String data){
        Gson gson = new Gson();
        Berita model = gson.fromJson(data,Berita.class);
        Log.v("IsiModel: ",model.getTitle());
        listBerita.add(model);
        beritaAdapter.notifyDataSetChanged();
    }

    void onClickGroup(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddBeritaActivity.class);
                intent.putExtra("id", "CEK");
                startActivityForResult(intent, CODE_MAIN_ACTIVITY);
            }
        });
    }

}