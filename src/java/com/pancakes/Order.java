package com.pancakes;

import java.util.List;

/**
 * Created by krithikabaskaran on 10/28/18.
 */
public class Order {
    String orderID;
    String userID;
    long creationTimestamp;

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }



    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }


    public Order(String orderID){
        this.orderID=orderID;
    }
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

}
