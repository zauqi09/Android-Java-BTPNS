package com.example.tugas3android.networking;

import com.example.tugas3android.model.Berita;
import com.example.tugas3android.model.BeritaResponse;
import com.example.tugas3android.model.BeritasResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BeritaApi {
    @GET("berita")
    Call<BeritasResponse> getBeritasList(@Query("page") String page,
                                          @Query("limit") String limit);

    @POST("berita")
    Call<BeritaResponse> postBerita(@Body Berita body);



}
