package com.jorgereina.bottlerocket;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jorgereina.bottlerocket.databinding.StoreItemBinding;
import com.jorgereina.bottlerocket.model.Store;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BrAdapter extends RecyclerView.Adapter<BrAdapter.BrViewHolder> {

    private Context context;
    private List<Store> stores;
    private ClickListener clickListener;

    public BrAdapter(Context context, List<Store> stores) {
        this.context = context;
        this.stores = stores;
    }

    @NonNull
    @Override
    public BrViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StoreItemBinding itemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.store_item, parent, false);
        return new BrViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull BrViewHolder holder, int position) {
        Store store = stores.get(position);
        Picasso.with(context).load(store.getStoreLogoURL()).into(holder.binding.storeLogoIv);
        holder.binding.nameTv.setText(store.getName());
        holder.binding.addressTv.setText(store.getFullAddress());
        holder.binding.phoneTv.setText(store.getPhone());
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class BrViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private StoreItemBinding binding;

        public BrViewHolder(final StoreItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.listItemLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (clickListener != null) {
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        void itemClicked(View view, int position);
    }

}
