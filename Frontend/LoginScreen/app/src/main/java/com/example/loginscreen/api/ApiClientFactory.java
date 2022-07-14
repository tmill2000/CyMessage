package com.example.loginscreen.api;

import com.example.loginscreen.model.GroupEvent;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientFactory {

    static Retrofit apiClientSeed;

    static Retrofit getApiClientSeed() {

        if(apiClientSeed == null) {
            apiClientSeed = new Retrofit.Builder()
                    .baseUrl("http://coms-309-055.cs.iastate.edu:8080/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return apiClientSeed;
    }

    public static UserApi GetUserApi() {
        return getApiClientSeed().create(UserApi.class);
    }
    public static GroupApi GetGroupApi() {
        return getApiClientSeed().create(GroupApi.class);
    }
    public static MessageApi GetMessageApi() {
        return getApiClientSeed().create(MessageApi.class);
    }
    public static UserAssociationApi GetUserAssociationApi() { return getApiClientSeed().create(UserAssociationApi.class);}
    public static GroupEventApi GetGroupEventApi() { return getApiClientSeed().create(GroupEventApi.class);}

}
