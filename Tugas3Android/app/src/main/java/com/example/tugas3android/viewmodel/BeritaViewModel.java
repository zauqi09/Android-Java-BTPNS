package com.example.tugas3android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugas3android.model.Berita;
import com.example.tugas3android.model.BeritaResponse;
import com.example.tugas3android.model.BeritasResponse;
import com.example.tugas3android.networking.BeritaRepository;

public class BeritaViewModel extends ViewModel {
    private MutableLiveData<BeritasResponse> mutableLiveData;
    private BeritaRepository beritasRepository;
    private MutableLiveData<BeritaResponse> mutableBeritaLiveData;
//    private BeritaRepository beritaRepository;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        beritasRepository = BeritaRepository.getInstance();
        mutableLiveData = beritasRepository.getBeritas("1", "10");
    }

    public LiveData<BeritasResponse> getBeritasRepository() {
        return mutableLiveData;
    }
    public void refresh(String page, String limit ){
        if (mutableLiveData != null){
            mutableLiveData = beritasRepository.getBeritas(page, limit);
            return;
        }
        beritasRepository = BeritaRepository.getInstance();
        mutableLiveData = beritasRepository.getBeritas("1", "10");
    }

    public LiveData<BeritaResponse> postBeritaRepository(Berita beritaPayload) {
        if (mutableBeritaLiveData == null) {
            beritasRepository = BeritaRepository.getInstance();
        }
        mutableBeritaLiveData = beritasRepository.postBerita(beritaPayload);

        return mutableBeritaLiveData;
    }



}
