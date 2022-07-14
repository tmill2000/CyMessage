package com.example.loginscreen.api;

import com.example.loginscreen.model.Message;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MessageApi {

    /**
     * (Blake's comment)
     * retrieves message from database based on what user sent the message and what group it was
     * sent in
     * @param groupid group id of message
     * @param sentby user that sent message
     * @return new reference to the message
     */
    @GET("getMessage")
    Call<Message> getMessage(@Query("group_id") int groupid, @Query("sent_by") String sentby);

    /**
     * (Jack's comment)
     * Adds new message to system based on input
     * @param message message to add
     * @return a reference to the new message
     */
    @POST("addMessage")
    Call<Message> addMessage(@Body Message message);

    /**
     * (Jack's comment)
     * Displays messages based on the group id given
     * @param groupId group to display messages of
     * @return all messages in the group
     */
    @GET("getMessagesByGroupId")
    Call<List<Message>> getMessagesByGroupId(@Query("group_id") int groupId);
}
