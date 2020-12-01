package com.example.tugas3android;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import java.util.List;

import com.example.tugas3android.model.Berita;
import com.example.tugas3android.viewmodel.BeritaViewModel;


public class AddBeritaActivity extends AppCompatActivity {
    private String id,nama,alamat,email;
    private Berita berita=new Berita();

    EditText titleEditText,categoryEditText,urlpictEditText;
    Button saveButton,deleteButton;
    String mode="add";
    BeritaViewModel beritaViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addberita);
        findViewById();
        initData();
        onClickGroup();
    }
    void findViewById(){
        titleEditText = (EditText) findViewById(R.id.title);
        categoryEditText = (EditText) findViewById(R.id.category);
        urlpictEditText = (EditText) findViewById(R.id.url);

        saveButton = (Button) findViewById(R.id.submit_addberita);

    }
    void initData(){
        beritaViewModel = ViewModelProviders.of(this).get(BeritaViewModel.class);
        Bundle bundle = getIntent().getExtras();
        mode=bundle.getString("mode","add");
        id=bundle.getString("id","");
        nama=bundle.getString("nama","");
        alamat=bundle.getString("alamat","");
        email=bundle.getString("email","");
        Toast.makeText(getApplicationContext(),nama, Toast.LENGTH_LONG).show();

    }
    void onClickGroup(){

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Berita beritaPayload = new Berita();
                beritaPayload.setTitle(titleEditText.getText().toString());
                beritaPayload.setCategory(categoryEditText.getText().toString());
                beritaPayload.setUrlpict(urlpictEditText.getText().toString());
                postBerita(beritaPayload);

            }
        });
    }

    private void postBerita(Berita beritaPayload ){
        beritaViewModel.postBeritaRepository(beritaPayload).observe(this, beritaResponse -> {
            berita = beritaResponse.getData();
            finish();
        });
    }

}