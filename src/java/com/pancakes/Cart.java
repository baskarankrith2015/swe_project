package com.pancakes;

/**
 * Created by krithikabaskaran on 11/10/18.
 */
public class Cart {
    String cartId;
    int strawberry;
    int blueberry;
    int plaincake;

    public Cart(String cartId, int strawberry, int blueberry, int plaincake) {
        this.cartId = cartId;
        this.strawberry = strawberry;
        this.blueberry = blueberry;
        this.plaincake = plaincake;
    }
    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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
