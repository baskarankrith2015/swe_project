package com.pancakes;

/**
 * Created by krithikabaskaran on 10/21/18.
 */
public class User {
    String userId;
    String userName;
    String password;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }
}
