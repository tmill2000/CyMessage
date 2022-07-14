package com.example.loginscreen.api;

import com.example.loginscreen.model.GroupEvent;
import com.example.loginscreen.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface GroupEventApi {

    @GET("getAllGroupEvents")
    Call<List<GroupEvent>> getAllGroupEvents();

    @GET("getGroupById")
    Call<GroupEvent> getGroupById(@Query("id") int id);

    @POST("addGroupEvent")
    Call<GroupEvent> addGroupEvent(@Body GroupEvent gEvent);

    @PUT("updateGroupEvent")
    Call<GroupEvent> updateGroupEvent(@Query("id") int id, @Body GroupEvent gEvent2);

    @DELETE("deleteGroupEvent")
    Call<GroupEvent> deleteGroupEvent(@Query("id") int id);


}
