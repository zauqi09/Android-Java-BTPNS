package com.example.tugas3android.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tugas3android.R;
import com.example.tugas3android.AddBeritaActivity;
import com.example.tugas3android.model.Berita;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class BeritaAdapter extends RecyclerView.Adapter<BeritaAdapter.BeritaViewHolder> {

    Context context;
    ArrayList<Berita> allBerita;

    public BeritaAdapter(Context context, ArrayList<Berita> allBerita) {
        this.context = context;
        this.allBerita = allBerita;
    }

    @NonNull
    @Override
    public BeritaAdapter.BeritaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);
        return new  BeritaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BeritaAdapter.BeritaViewHolder holder, int position) {
        holder.titleTv.setText(allBerita.get(position).getTitle());
        holder.categoryTv.setText(allBerita.get(position).getCategory());
        UrlImageViewHelper.setUrlDrawable(holder.urlpictTv, allBerita.get(position).getUrlpict());
    }

    @Override
    public int getItemCount() {
        return allBerita.size();
    }

    public class BeritaViewHolder extends RecyclerView.ViewHolder{

        TextView titleTv;
        TextView categoryTv;
        ImageView urlpictTv;
        public BeritaViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTv = itemView.findViewById(R.id.titleTextView);
            categoryTv = itemView.findViewById(R.id.categoryTextView);
            urlpictTv = itemView.findViewById(R.id.beritaImageView);
        }
    }
}
