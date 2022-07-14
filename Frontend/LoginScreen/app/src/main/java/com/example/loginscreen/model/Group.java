package com.example.loginscreen.model;

/**
 * Getters and setters for group object that is retrieved from backend
 */
public class Group {

    int groupId;
    String groupName;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
