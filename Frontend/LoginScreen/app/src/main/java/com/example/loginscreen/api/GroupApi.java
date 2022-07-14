package com.example.loginscreen.api;

import com.example.loginscreen.model.Group;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GroupApi {

    /**
     * (Jack's comment)
     * Adds new group to system based on input
     * @param group group to add
     * @return a reference to the new group
     */
    @POST("addGroup")
    Call<Group> addGroup(@Body Group group);

    /**
     * (Jack's comment)
     * Get's groups the user is in based on the username given
     * @param userName username of the user we want to retrieve the groups of
     * @return lsit of groups
     */
    @GET("getGroupsForUser")
    Call<List<Group>> getGroupsForUser(@Query("user_name") String userName);

    /**
     * (Blake's comment)
     * gets group based on the name of the group
     * @param groupName name of the group to retrieve
     * @return new group object
     */
    @GET("getGroupByName")
    Call<Group> getGroupByName(@Query("group_name") String groupName);

    @GET("getGroupByID")
    Call<Group> getGroupByID(@Query("group_id") int groupId);
}
