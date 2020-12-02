package com.example.android_tugas4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import com.example.android_tugas4.adapter.ProductAdapter;
import com.example.android_tugas4.model.Product;
import com.example.android_tugas4.viewmodels.ProductViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    ArrayList<Product> productArrayList = new ArrayList<>();
    ProductAdapter productAdapter;
    @BindView(R.id.listprod)
    RecyclerView rvProduct;

    ProductViewModel productViewModel;

    @BindView(R.id.phone_number)
    EditText phone_numberET;

    List<Product> products;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        String[] perms = {"android.permission.INTERNET"};
//        requestPermissions(perms,100);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);

        productViewModel.init();
        productViewModel.getProductsRepository().observe(this, productsResponse -> {
            products = productsResponse.getData();
            productArrayList.clear();
            productArrayList.addAll(products);
            productAdapter.notifyDataSetChanged();
        });

        if (productAdapter == null) {
            productAdapter = new ProductAdapter(MainActivity.this, productArrayList, phone_numberET.getText().toString());
            rvProduct.setLayoutManager(new GridLayoutManager(this,2));

            rvProduct.setAdapter(productAdapter);
            //rvProduct.setItemAnimator(new DefaultItemAnimator());
        } else {
            productAdapter.notifyDataSetChanged();
        }


    }
    private void getListProduct(String page, String limit ){
        productViewModel.refresh(page, limit);
        productViewModel.getProductsRepository().observe(this, productsResponse -> {
            products = productsResponse.getData();
            productArrayList.clear();
            productArrayList.addAll(products);
            productAdapter.notifyDataSetChanged();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListProduct("1", "10");
    }

}