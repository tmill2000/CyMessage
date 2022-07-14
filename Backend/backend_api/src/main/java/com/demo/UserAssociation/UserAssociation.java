package com.demo.UserAssociation;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_group_association")
public class UserAssociation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "group_id")
    private int groupId;

    @Column(name = "role")
    private String role;

    public UserAssociation() {
    }

    public UserAssociation(int id, String userName, int groupId, String role) {
        this.id = id;
        this.userName = userName;
        this.groupId = groupId;
        this.role = role;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getGroupId() {
        return this.groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", userName='" + getUserName() + "'" + ", groupId='" + getGroupId() + "'"
                + ", role='" + getRole() + "'" + "}";
    }

}