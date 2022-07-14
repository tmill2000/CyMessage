package com.example.loginscreen.api;

import com.example.loginscreen.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    /**
     * (Blake's comment)
     * retrieves user from database based on the username
     * @param user username of user
     * @return new reference to the user
     */
    @GET("getUser")
    Call<User> getUser(@Query("user_name") String user);

    /**
     * (Blake's comment)
     * Returns a user that has a protected password to show if the given password is valid
     * @param userName username of the user we want to check
     * @param password password of the user we want to check
     * @return new user with password to set to valid or invalid
     */
    @GET("getUserValidate")
    Call<User> getUserValidate(@Query("user_name") String userName, @Query("password") String password);

    /**
     * (Jack's comment)
     * Adds new user to system based on input
     * @param user user to add
     * @return a reference to the new user
     */
    @POST("addUser")
    Call<User> addUser(@Body User user);

    /**
     * (Jack's comment)
     * Depending on the id, changes user password
     * @param id id of the user we want to change
     * @param user2 use object with new password
     * @return new user
     */
    @PUT("updateUser")
    Call<User> updateUser(@Query("id") int id, @Body User user2);

    /**
     * (Blake's comment)
     * Deletes the user with the given id
     * @param userId id of user we want to delete
     * @return the deleted user
     */
    @DELETE("deleteUser")
    Call<User> deleteUser(@Query("id") int userId);

}
