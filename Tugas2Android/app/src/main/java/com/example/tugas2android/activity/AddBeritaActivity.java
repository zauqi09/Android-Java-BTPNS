package com.example.tugas2android.activity;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tugas2android.R;
import com.example.tugas2android.adapter.BeritaAdapter;
import com.example.tugas2android.model.Berita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AddBeritaActivity extends AppCompatActivity{
    Button submit_addberita;
    EditText title,category,url_picture;
    ArrayList<Berita> listBerita = new ArrayList<>();;
    private ArrayList<String> arrTemp;
    private static String json="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addberita);
        initView();
        onClickGroup();
    }
    void initView(){
        arrTemp=getIntent().getStringArrayListExtra("data");
        submit_addberita = (Button) findViewById(R.id.submit_addberita);
        title = (EditText) findViewById(R.id.title);
        category = (EditText) findViewById(R.id.category);
        url_picture = (EditText) findViewById(R.id.url);

    }
    private void saveData(){
        String newtitle = title.getText().toString();
        String newcategory = category.getText().toString();
        String newurl = url_picture.getText().toString();

        Berita model = new Berita();
        model.setTitle(newtitle);
        model.setCategory(newcategory);
        model.setUrlpict(newurl);
        Gson gson = new Gson();
        json=gson.toJson(model);

    }

    void onClickGroup(){
        submit_addberita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(listBerita);
                Log.v("Isi Array: ",jsonArray.toString());
                Intent i = getIntent();
                i.putExtra("data", json);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
