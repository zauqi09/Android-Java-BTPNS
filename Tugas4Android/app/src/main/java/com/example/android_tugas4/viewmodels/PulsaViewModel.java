package com.example.android_tugas4.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_tugas4.model.ProductResponse;
import com.example.android_tugas4.model.ProductsResponse;
import com.example.android_tugas4.model.Pulsa;
import com.example.android_tugas4.model.PulsaResponse;
import com.example.android_tugas4.network.ProductsRepository;
import com.example.android_tugas4.network.PulsaApi;
import com.example.android_tugas4.network.PulsaRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PulsaViewModel extends ViewModel {
    private PulsaRepository pulsaRepository;
    private MutableLiveData<PulsaResponse> mutableLiveData;


    public LiveData<PulsaResponse> postPulsaRepository(Pulsa pulsaPayload) {
        if (mutableLiveData == null) {
            pulsaRepository = PulsaRepository.getInstance();
        }
        mutableLiveData = pulsaRepository.postPulsa(pulsaPayload);

        return mutableLiveData;
    }
}
