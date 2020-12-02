package com.example.android_tugas4.viewmodels;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_tugas4.model.ProductResponse;
import com.example.android_tugas4.model.ProductsResponse;
import com.example.android_tugas4.network.ProductsRepository;


public class ProductViewModel extends ViewModel {
    private MutableLiveData<ProductsResponse> mutableLiveData;
    private ProductsRepository productsRepository;
    private MutableLiveData<ProductResponse> mutableProductLiveData;
    public void init(){
        if (mutableLiveData != null){
            return;
        }
        productsRepository = ProductsRepository.getInstance();
        mutableLiveData = productsRepository.getProducts("1", "10");
    }

    public LiveData<ProductsResponse> getProductsRepository() {
        return mutableLiveData;
    }
    public void refresh( String page, String limit){
        if (mutableLiveData != null){
            mutableLiveData = productsRepository.getProducts(page, limit);
            return;
        }
        productsRepository = ProductsRepository.getInstance();
        mutableLiveData = productsRepository.getProducts("1","10");
    }
    public LiveData<ProductResponse> getProductRepository(String id) {
        if (mutableProductLiveData == null){
            productsRepository = ProductsRepository.getInstance();
            mutableProductLiveData = productsRepository.getProduct(id);
        }
        return mutableProductLiveData;
    }

}