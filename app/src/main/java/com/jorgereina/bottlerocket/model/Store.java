package com.jorgereina.bottlerocket.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jorgereina on 4/5/18.
 */

public class Store implements Parcelable{

    private String address;
    private String city;
    private String name;
    private String zipcode;
    private String storeLogoURL;
    private String phone;
    private String state;

    protected Store(Parcel in) {
        address = in.readString();
        city = in.readString();
        name = in.readString();
        zipcode = in.readString();
        storeLogoURL = in.readString();
        phone = in.readString();
        state = in.readString();
    }

    public static final Creator<Store> CREATOR = new Creator<Store>() {
        @Override
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        @Override
        public Store[] newArray(int size) {
            return new Store[size];
        }
    };

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getStoreLogoURL() {
        return storeLogoURL;
    }

    public void setStoreLogoURL(String storeLogoURL) {
        this.storeLogoURL = storeLogoURL;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFullAddress() {
        return address + "\n" + city  + ", " + state + " " + zipcode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(address);
        parcel.writeString(city);
        parcel.writeString(name);
        parcel.writeString(zipcode);
        parcel.writeString(storeLogoURL);
        parcel.writeString(phone);
        parcel.writeString(state);
    }
}
