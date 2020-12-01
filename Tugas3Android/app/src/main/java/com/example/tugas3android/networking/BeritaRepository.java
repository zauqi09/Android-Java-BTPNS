package com.example.tugas3android.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.tugas3android.model.Berita;
import com.example.tugas3android.model.BeritaResponse;
import com.example.tugas3android.model.BeritasResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaRepository {
    private static BeritaRepository beritasRepository;

    public static BeritaRepository getInstance(){
        if (beritasRepository == null){
            beritasRepository = new BeritaRepository();
        }
        return beritasRepository;
    }

    private BeritaApi beritaApi;

    public BeritaRepository(){
        beritaApi = RetrofitService.cteateService(BeritaApi.class);
    }

    public MutableLiveData<BeritasResponse> getBeritas(String page, String limit){
        MutableLiveData<BeritasResponse> beritasData = new MutableLiveData<>();
        beritaApi.getBeritasList(page, limit).enqueue(new Callback<BeritasResponse>() {
            @Override
            public void onResponse(Call<BeritasResponse> call,
                                   Response<BeritasResponse> response) {
                if (response.isSuccessful()){
                    System.out.println("getBerita");
                    beritasData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritasResponse> call, Throwable t) {
                beritasData.setValue(null);
            }
        });
        return beritasData;
    }

    public MutableLiveData<BeritaResponse> postBerita(Berita beritaPayload){
        MutableLiveData<BeritaResponse> beritaData = new MutableLiveData<>();
        beritaApi.postBerita(beritaPayload).enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call,
                                   Response<BeritaResponse> response) {
                if (response.isSuccessful()){
                    System.out.println("postBerita");
                    beritaData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                beritaData.setValue(null);
            }
        });
        return beritaData;
    }
}


