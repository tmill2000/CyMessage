package com.demo.User;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class CyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int userId;

    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    public CyUser() {
    }
    

    
    public CyUser(int userId, String userName, String password, String email) {
         this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CyUser)) {
            return false;
        }
        CyUser cyUser = (CyUser) o;
        return userId == cyUser.userId && Objects.equals(userName, cyUser.userName) && Objects.equals(password, cyUser.password) && Objects.equals(email, cyUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, password, email);
    }


    @Override
    public String toString() {
        return "{" + " userId='" + getUserId() + "'" + ", userName='" + getUserName() + "'" + ", password='"
                + getPassword() + "'" + ", email='" + getEmail() + "'" + "}";
    }

}
