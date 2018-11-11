package com.pancakes.test;

import com.pancakes.Order;
import com.pancakes.PanCake;
import com.pancakes.PancakeRestAPI;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

/**
 * Created by krithikabaskaran on 11/3/18.
 */
public class OrderTest {
    PancakeRestAPI pancakeRestAPI= new PancakeRestAPI();
    @Test
public void createOrder() throws SQLException {
        int plainItems=2;
        int strawberryItems=1;
        int blueberryItems=1;
        String userId=UUID.randomUUID().toString();
        Order order = new Order(UUID.randomUUID().toString());
        for(int i=0; i<plainItems; i++) {
            PanCake panCake = new PanCake(UUID.randomUUID().toString());
            panCake.setStrawberrySauce(false);
            panCake.setCreationTimestamp(System.currentTimeMillis());
            panCake.setOrderId(order.getOrderID());
            pancakeRestAPI.writePancake(panCake);
        }
        for(int i=0; i<strawberryItems; i++) {
            PanCake panCake = new PanCake(UUID.randomUUID().toString());
            panCake.setCreationTimestamp(System.currentTimeMillis());
            panCake.setStrawberrySauce(true);
            panCake.setOrderId(order.getOrderID());
            pancakeRestAPI.writePancake(panCake);
        }
        for(int i=0; i<blueberryItems; i++) {
            PanCake panCake = new PanCake(UUID.randomUUID().toString());
            panCake.setCreationTimestamp(System.currentTimeMillis());
            panCake.setBlueberrySauce(true);
            panCake.setOrderId(order.getOrderID());
            pancakeRestAPI.writePancake(panCake);
        }
        order.setCreationTimestamp(System.currentTimeMillis());
        order.setUserID(userId);
        pancakeRestAPI.writeOrder(order);
        List<Order>orderList= pancakeRestAPI.getOrder(order.getOrderID());
        List<PanCake> panCakeList=pancakeRestAPI.getPancakesInAOrder(order.getOrderID());
        for(PanCake p:panCakeList){
            System.out.println(p.getPanCakeId());
        }
    }

}
