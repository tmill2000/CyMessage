package com.example.loginscreen.api;

import com.example.loginscreen.model.User;
import com.example.loginscreen.model.UserAssociation;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserAssociationApi {

    /**
     * (Blake's comment)
     * retrieves user association from database based on the username
     * @param username username of the user
     * @return new reference to the user association
     */
    @GET("getUserAssociation")
    Call<List<UserAssociation>> getUserAssociation(@Query("user_name") String username);


    /**
     * (Jack's comment)
     * Adds new user association to system based on input
     * @param userAssociation group to add
     * @return a reference to the new user association
     */
    @POST("addUserAssociation")
    Call<UserAssociation> addUserAssociation(@Body UserAssociation userAssociation);

    @PUT("updateUserAssociation")
    Call<UserAssociation> updateUserAssociation(@Query("user_id") int id, @Body UserAssociation newUserAssc);

    @DELETE("removeUserAssociation")
    Call<UserAssociation> deleteUserAssociation(@Query("user_id") int userId);

}
