package com.example.loginscreen.model;


/**
 * Getters and setters for user association object that is retrieved from backend
 */
public class UserAssociation {
    int id;
    String userName;
    int groupId;
    String role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getRole() { return role; }

    public void setRole(String role) {
        this.role = role;
    }
}
