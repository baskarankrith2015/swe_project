package com.pancakes;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by krithikabaskaran on 10/28/18.
 */
@Path("/pancake")
public class PancakeRestAPI {
    private static DatabaseAccess databaseAccess= new DatabaseAccess();
    LoginRestAPI loginRestAPI = new LoginRestAPI();
    private Session session=new Session();
    @PUT
    @Path("/order")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrderRestApi(@FormParam("plain") final Integer plainItems,
                                      @FormParam("strawberry") final Integer strawberryItems,
                                      @FormParam("blueberry") final Integer blueberryItems,
                                     @CookieParam("session_cookie") Cookie cookie) throws SQLException {

        String sessionVal=cookie.getValue();
        String userId= session.getUserId(sessionVal);
        User user=loginRestAPI.readUser(userId);
        if(user==null){
            Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Wrong credentials")
                    .build();
        }
        Order order =new Order(UUID.randomUUID().toString());
      for(int i=0; i<plainItems; i++) {
          PanCake panCake = new PanCake(UUID.randomUUID().toString());
          panCake.setCreationTimestamp(System.currentTimeMillis());
          panCake.setOrderId(order.getOrderID());
          writePancake(panCake);
      }
        for(int i=0; i<strawberryItems; i++) {
            PanCake panCake = new PanCake(UUID.randomUUID().toString());
            panCake.setCreationTimestamp(System.currentTimeMillis());
            panCake.setStrawberrySauce(true);
            panCake.setOrderId(order.getOrderID());
            writePancake(panCake);
        }
        for(int i=0; i<blueberryItems; i++) {
            PanCake panCake = new PanCake(UUID.randomUUID().toString());
            panCake.setCreationTimestamp(System.currentTimeMillis());
            panCake.setBlueberrySauce(true);
            panCake.setOrderId(order.getOrderID());
            writePancake(panCake);
        }
        order.setCreationTimestamp(System.currentTimeMillis());
        order.setUserID(userId);
        writeOrder(order);
      return  Response.status(Response.Status.OK)
              .cookie(new NewCookie("session_cookie",session.createCookie(userId)))
              .entity("Order placed")
              .build();
    }
    @POST
    @Path("/order/{orderID}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderRestApi(@FormParam("plain") final Integer plainItems,
                                      @FormParam("strawberry") final Integer strawberryItems,
                                      @FormParam("blueberry") final Integer blueberryItems,
                                      @CookieParam("session_cookie") Cookie cookie) {

        String sessionVal=cookie.getValue();
        String userId= session.getUserId(sessionVal);

        Order order =new Order(UUID.randomUUID().toString());
        for(int i=0; i<plainItems; i++) {
            PanCake panCake = new PanCake(UUID.randomUUID().toString());
            panCake.setCreationTimestamp(System.currentTimeMillis());
            panCake.setOrderId(order.getOrderID());
            writePancake(panCake);
        }
        for(int i=0; i<strawberryItems; i++) {
            PanCake panCake = new PanCake(UUID.randomUUID().toString());
            panCake.setCreationTimestamp(System.currentTimeMillis());
            panCake.setStrawberrySauce(true);
            panCake.setOrderId(order.getOrderID());
            writePancake(panCake);
        }
        for(int i=0; i<blueberryItems; i++) {
            PanCake panCake = new PanCake(UUID.randomUUID().toString());
            panCake.setCreationTimestamp(System.currentTimeMillis());
            panCake.setBlueberrySauce(true);
            panCake.setOrderId(order.getOrderID());
            writePancake(panCake);
        }
        order.setCreationTimestamp(System.currentTimeMillis());
        order.setUserID(userId);
        //writeOrder(order);
        return  Response.status(Response.Status.OK)
                .cookie(new NewCookie("session_cookie",session.createCookie(userId)))
                .entity("Order added")
                .build();
    }

    @DELETE
    @Path("/order/{orderID}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderRestApi(@PathParam("orderID") String orderUUID,
                                    @FormParam("plain") final Integer plainItems,
                                    @FormParam("strawberry") final Integer strawberryItems,
                                    @FormParam("blueberry") final Integer blueberryItems,
                                    @CookieParam("session_cookie") Cookie cookie) {

        String sessionVal=cookie.getValue();
        String userId= session.getUserId(sessionVal);
        try {
            List<PanCake> plainList = getPlainPancakesInAOrder(orderUUID);
            deleteItemsinOrder(plainList, plainItems);
            List<PanCake> strawberryList = getStrawberryPancakesInAOrder(orderUUID);
            deleteItemsinOrder(strawberryList,strawberryItems);
            List<PanCake> blueberryList = getBlueberryPancakesInAOrder(orderUUID);
            deleteItemsinOrder(blueberryList,blueberryItems);
        } catch (SQLException e) {
            e.printStackTrace();
        }



        return  Response.status(Response.Status.OK)
                .cookie(new NewCookie("session_cookie",session.createCookie(userId)))
                .entity("Order added")
                .build();
    }

    @GET
    @Path("/orders/{orderID}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEntireOrderRestApi(@PathParam("orderID") String orderUUID,
                                             @CookieParam("session_cookie") Cookie cookie) {

        String sessionVal=cookie.getValue();
        String userId= session.getUserId(sessionVal);
        deleteOrder(orderUUID);
        return  Response.status(Response.Status.OK)
                .cookie(new NewCookie("session_cookie",session.createCookie(userId)))
                .entity("Order added")
                .build();
    }
    @DELETE
    @Path("/orders/{orderID}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEntireOrderRestApi(@PathParam("orderID") String orderUUID,
                                       @CookieParam("session_cookie") Cookie cookie) {

        String sessionVal=cookie.getValue();
        String userId= session.getUserId(sessionVal);
        deleteOrder(orderUUID);
        return  Response.status(Response.Status.OK)
                .cookie(new NewCookie("session_cookie",session.createCookie(userId)))
                .entity("Order added")
                .build();
    }
    private void deleteItemsinOrder(List<PanCake> plainList,int items) {


                List<String> idList = plainList.stream()
                        .limit(items)
                        .map(PanCake::getPanCakeId).
                                collect(Collectors.toList());
                for (String anIdList : idList) {
                    deletePancake(anIdList);
                }
        }


    public void writePancake(PanCake panCake){
        String sql="Insert into pancake ( pancake_id,strawberry,blueberry ,order_id,creation_timestamp) VALUES ("
                +"\""+panCake.getPanCakeId() +"\""+","+panCake.isStrawberrySauce() +","+panCake.isBlueberrySauce()+","+"\""+panCake.getOrderId()+"\""+
                ","+panCake.getCreationTimestamp()+ ");";
            databaseAccess.runDatabaseQuery(sql);

    }
    public void writeOrder(Order order){
        String sql="Insert into orders (order_id,user_id,creation_timestamp) VALUES ("
                +"\""+order.getOrderID() +"\""+","+"\""+order.getUserID() +"\""+","+order.getCreationTimestamp()+ ");";
        System.out.println(sql);
        databaseAccess.runDatabaseQuery(sql);

    }
    public void deletePancake(String pancakeID){
        String sql="delete from pancake where pancake_id='"
                +"\""+ pancakeID+";";
        System.out.println(sql);
        databaseAccess.runDatabaseQuery(sql);

    }
    public void deleteOrder(String orderID){
        String sql="delete from orders where order_id='"
                +"\""+ orderID+";";
        System.out.println(sql);
        databaseAccess.runDatabaseQuery(sql);

    }
    public List<Order> getOrder(String orderId) throws SQLException {
        String sql="Select * from orders where order_id ="
                +"\""+orderId+"\""+";";
        SqlReturnClass returnClass = databaseAccess.readDatabaseQuery(sql);
        ResultSet rs=returnClass.getResultSet();
        List<Order>orderList= new ArrayList<>();
        while(rs.next()){
            //Retrieve by column name
            String userId  = rs.getString("user_id");
            String id = rs.getString("order_id");
            String timestamp = rs.getString("creation_timestamp");
            Order order= new Order(id);
            order.setUserID(userId);
            order.setCreationTimestamp(Long.parseLong(timestamp));
            orderList.add(order);
        }
        //STEP 6: Clean-up environment
        returnClass.close();
        return orderList;
    }
    public List<PanCake> getPancakesInAOrder(String orderId) throws SQLException {
        String sql="Select * from pancake where order_id ="
                +"\""+orderId+"\""+";";
        SqlReturnClass returnClass = databaseAccess.readDatabaseQuery(sql);
        ResultSet rs=returnClass.getResultSet();
        List<PanCake>panCakeList= new ArrayList<>();
        while(rs.next()){
            //Retrieve by column name
            String pancakeId  = rs.getString("pancake_id");
            boolean strawberry = rs.getBoolean("strawberry");
            boolean blueberry = rs.getBoolean("blueberry");
            String order_id= rs.getString("order_id");
            String timestamp = rs.getString("creation_timestamp");
            PanCake panCake=new PanCake(pancakeId);
            panCake.setBlueberrySauce(blueberry);
            panCake.setStrawberrySauce(strawberry);
            panCake.setOrderId(orderId);
            panCake.setCreationTimestamp(Long.parseLong(timestamp));
            panCakeList.add(panCake);
        }
        //STEP 6: Clean-up environment
        returnClass.close();
        return panCakeList;
    }
    public List<PanCake> getStrawberryPancakesInAOrder(String orderId) throws SQLException {
        String sql="Select * from pancake where order_id ="
                +"\""+orderId+"\"" +"and strawberry=true"+";";
        SqlReturnClass returnClass = databaseAccess.readDatabaseQuery(sql);
        ResultSet rs=returnClass.getResultSet();
        List<PanCake>panCakeList= new ArrayList<>();
        while(rs.next()){
            //Retrieve by column name
            String pancakeId  = rs.getString("pancake_id");
            boolean strawberry = rs.getBoolean("strawberry");
            boolean blueberry = rs.getBoolean("blueberry");
            String order_id= rs.getString("order_id");
            String timestamp = rs.getString("creation_timestamp");
            PanCake panCake=new PanCake(pancakeId);
            panCake.setBlueberrySauce(blueberry);
            panCake.setStrawberrySauce(strawberry);
            panCake.setOrderId(orderId);
            panCake.setCreationTimestamp(Long.parseLong(timestamp));
            panCakeList.add(panCake);
        }
        //STEP 6: Clean-up environment
        returnClass.close();
        return panCakeList;
    }
    public List<PanCake> getBlueberryPancakesInAOrder(String orderId) throws SQLException {
        String sql="Select * from pancake where order_id ="
                +"\""+orderId+"\"" +"and blueberry=true"+";";
        SqlReturnClass returnClass = databaseAccess.readDatabaseQuery(sql);
        ResultSet rs=returnClass.getResultSet();
        List<PanCake>panCakeList= new ArrayList<>();
        while(rs.next()){
            //Retrieve by column name
            String pancakeId  = rs.getString("pancake_id");
            boolean strawberry = rs.getBoolean("strawberry");
            boolean blueberry = rs.getBoolean("blueberry");
            String order_id= rs.getString("order_id");
            String timestamp = rs.getString("creation_timestamp");
            PanCake panCake=new PanCake(pancakeId);
            panCake.setBlueberrySauce(blueberry);
            panCake.setStrawberrySauce(strawberry);
            panCake.setOrderId(orderId);
            panCake.setCreationTimestamp(Long.parseLong(timestamp));
            panCakeList.add(panCake);
        }
        //STEP 6: Clean-up environment
        returnClass.close();
        return panCakeList;
    }
    public List<PanCake> getPlainPancakesInAOrder(String orderId) throws SQLException {
        String sql="Select * from pancake where order_id ="
                +"\""+orderId+"\"" +"and blueberry=false and strawberry=false"+";";
        SqlReturnClass returnClass = databaseAccess.readDatabaseQuery(sql);
        ResultSet rs=returnClass.getResultSet();
        List<PanCake>panCakeList= new ArrayList<>();
        while(rs.next()){
            //Retrieve by column name
            String pancakeId  = rs.getString("pancake_id");
            boolean strawberry = rs.getBoolean("strawberry");
            boolean blueberry = rs.getBoolean("blueberry");
            String order_id= rs.getString("order_id");
            String timestamp = rs.getString("creation_timestamp");
            PanCake panCake=new PanCake(pancakeId);
            panCake.setBlueberrySauce(blueberry);
            panCake.setStrawberrySauce(strawberry);
            panCake.setOrderId(orderId);
            panCake.setCreationTimestamp(Long.parseLong(timestamp));
            panCakeList.add(panCake);
        }
        //STEP 6: Clean-up environment
        returnClass.close();
        return panCakeList;
    }
}