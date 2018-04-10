package com.jorgereina.bottlerocket.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        final String phone = getIntent.getStringExtra(StoreListActivity.STORE_PHONE);
        String imageUrl = getIntent.getStringExtra(StoreListActivity.STORE_IMAGE_URL);

        binding.detailsNameTv.setText(name);
        binding.detailsAddressTv.setText(address);
        binding.detailsPhoneTv.setText(phone);
        Picasso.with(getApplicationContext()).load(imageUrl).into(binding.detailsStoreLogoIv);

        binding.detailsPhoneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callIntent(phone);
            }
        });
    }

    private void callIntent(String phoneNumber) {
        phoneNumber = phoneNumber.replaceAll("[\\D]", "");
        phoneNumber = phoneNumber.replaceAll(" ", "").trim();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}

