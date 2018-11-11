package com.pancakes;

/**
 * Created by krithikabaskaran on 10/28/18.
 */
public class PanCake {
    public int strawberrySauce=0;
    public int blueberrySauce=0;
    String panCakeId;
    String orderId;
    long creationTimestamp;

    public long getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(long creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }



    public PanCake(String panCakeId) {
        this.panCakeId = panCakeId;
        strawberrySauce=0;
        blueberrySauce=0;

    }

    public String getPanCakeId() {
        return panCakeId;
    }

    public void setPanCakeId(String panCakeId) {
        this.panCakeId = panCakeId;
    }



    public boolean isBlueberrySauce() {
        return blueberrySauce==1;
    }

    public void setBlueberrySauce(boolean blueberrySauce) {
        if(blueberrySauce) this.blueberrySauce=1;
        else this.blueberrySauce=0;
    }

    public boolean isStrawberrySauce() {
        return strawberrySauce==1;
    }

    public void setStrawberrySauce(boolean strawberrySauce) {
        if(strawberrySauce) this.strawberrySauce=1;
        else this.strawberrySauce=0;
    }
}
