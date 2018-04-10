package com.jorgereina.bottlerocket.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.jorgereina.bottlerocket.BrAdapter;
import com.jorgereina.bottlerocket.network.BulletApi;
import com.jorgereina.bottlerocket.R;
import com.jorgereina.bottlerocket.util.SpacesItemDecoration;
import com.jorgereina.bottlerocket.databinding.ActivityStoreListBinding;
import com.jorgereina.bottlerocket.model.Store;
import com.jorgereina.bottlerocket.model.StoreResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoreListActivity extends AppCompatActivity implements BrAdapter.ClickListener {

    private static final String BASE_URL = "http://sandbox.bottlerocketapps.com/";
    public static final String STORE_IMAGE_URL = "STORE_IMAGE_URL";
    public static final String STORE_NAME = "STORE_NAME";
    public static final String STORE_ADDRESS = "STORE_ADDRESS";
    public static final String STORE_PHONE = "STORE_PHONE";

    private ActivityStoreListBinding binding;
    private LayoutManager layoutManager;
    private BrAdapter adapter;
    private List<Store> stores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_list);

        if (!checkNetworkConnection()) {
            Toast.makeText(
                    getApplicationContext(),
                    getResources().getString(R.string.no_internet_connection_available),
                    Toast.LENGTH_LONG
            ).show();
        } else {
            stores = new ArrayList<>();
            layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
            adapter = new BrAdapter(getApplicationContext(), fetchStoreList(stores));
            adapter.setClickListener(this);
            binding.brRv.setLayoutManager(layoutManager);
            binding.brRv.setAdapter(adapter);
            binding.brRv.addItemDecoration(new SpacesItemDecoration(20));
        }
    }

    private List<Store> fetchStoreList(final List<Store> storeList) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BulletApi service = retrofit.create(BulletApi.class);
        final Call<StoreResponse> stores = service.listStores();

        stores.enqueue(new Callback<StoreResponse>() {
            @Override
            public void onResponse(Call<StoreResponse> call, Response<StoreResponse> response) {
                Log.d("lagarto", "onResponse: " + response.body().getStoreList().get(0).getFullAddress());
                storeList.addAll(response.body().getStoreList());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<StoreResponse> call, Throwable t) {
                Log.d("lagarto", "onFailure: " + t.getMessage());
            }
        });
        return storeList;
    }

    @Override
    public void itemClicked(View view, int position) {
        Store store = stores.get(position);
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(STORE_NAME, store.getName());
        intent.putExtra(STORE_ADDRESS, store.getFullAddress());
        intent.putExtra(STORE_PHONE, store.getPhone());
        intent.putExtra(STORE_IMAGE_URL, store.getStoreLogoURL());
        startActivity(intent);
    }

    private boolean checkNetworkConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
