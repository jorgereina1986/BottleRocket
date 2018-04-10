package com.jorgereina.bottlerocket.model;

import java.util.List;

/**
 * Created by jorgereina on 4/5/18.
 */

public class StoreResponse {

    private List<Store> stores;

    public List<Store> getStoreList() {
        return stores;
    }

    public void setStoreList(List<Store> stores) {
        this.stores = stores;
    }
}
