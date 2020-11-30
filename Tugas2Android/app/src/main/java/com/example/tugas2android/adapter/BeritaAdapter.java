package com.example.tugas2android.adapter;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import java.io.InputStream;
import java.util.List;

import com.example.tugas2android.R;
import com.example.tugas2android.model.Berita;
public class BeritaAdapter extends BaseAdapter {

    Context context;
    private List<Berita> list;


    public BeritaAdapter(Context context, List<Berita> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }


    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    @SuppressWarnings("deprecation")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.item_berita, null);
        }

        Berita berita = list.get(position);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.titleTextView);
        TextView categoryTextView = (TextView) convertView.findViewById(R.id.categoryTextView);

        titleTextView.setText(berita.getTitle());
        categoryTextView.setText(berita.getCategory());
        new DownloadImageTask((ImageView) convertView.findViewById(R.id.beritaImageView))
                .execute(berita.getUrlpict());
        return convertView;
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
