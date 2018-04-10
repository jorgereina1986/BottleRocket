package com.jorgereina.bottlerocket.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jorgereina.bottlerocket.R;
import com.jorgereina.bottlerocket.databinding.ActivityDetailsBinding;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    private ActivityDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        Intent getIntent = getIntent();
        String name = getIntent.getStringExtra(StoreListActivity.STORE_NAME);
        String address = getIntent.getStringExtra(StoreListActivity.STORE_ADDRESS);
        String phone = getIntent.getStringExtra(StoreListActivity.STORE_PHONE);
        String imageUrl = getIntent.getStringExtra(StoreListActivity.STORE_IMAGE_URL);

        binding.detailsNameTv.setText(name);
        binding.detailsAddressTv.setText(address);
        binding.detailsPhoneTv.setText(phone);
        Picasso.with(getApplicationContext()).load(imageUrl).into(binding.detailsStoreLogoIv);
    }
}
