package com.pancakes;

/**
 * Created by krithikabaskaran on 11/10/18.
 */
public class Cart {
    String userId;
    int strawberry;
    int blueberry;
    int plaincake;

    public Cart(){}
    public Cart(String userId, int strawberry, int blueberry, int plaincake) {
        this.userId = userId;
        this.strawberry = strawberry;
        this.blueberry = blueberry;
        this.plaincake = plaincake;
    }

    public Cart(Integer plainItems, Integer strawberryItems, Integer blueberryItems) {
        this.strawberry = strawberryItems;
        this.blueberry = blueberryItems;
        this.plaincake = plainItems;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStrawberry() {
        return strawberry;
    }

    public void setStrawberry(int strawberry) {
        this.strawberry = strawberry;
    }

    public int getBlueberry() {
        return blueberry;
    }

    public void setBlueberry(int blueberry) {
        this.blueberry = blueberry;
    }

    public int getPlaincake() {
        return plaincake;
    }

    public void setPlaincake(int plaincake) {
        this.plaincake = plaincake;
    }
}
