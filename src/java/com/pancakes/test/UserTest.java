package com.pancakes.test;


import com.pancakes.*;
import org.junit.Test;

import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by krithikabaskaran on 10/21/18.
 */
public class UserTest {
    LoginRestAPI loginRestAPI = new LoginRestAPI();

    @Test
    public void createUserTest() throws SQLException {

        String id = UUID.randomUUID().toString();
        loginRestAPI.createUser(id, "name", "pass");
        User user = loginRestAPI.readUser(id);
        if (user.getUserId().equals(id)) {
            assert (true);
        } else {
            assert (false);
        }
    }

    @Test
    public void nonExistentUser() throws SQLException {

        String id = UUID.randomUUID().toString();
        User user = loginRestAPI.readUser(id);
        if (user == null) {
            assert (true);
        } else {
            assert (false);
        }
    }

    @Test
    public void encryptDecryptPasswordTest() throws Exception {
        String password = "Password123";
        PasswordUtils passwordUtils = new PasswordUtils();
        String encryptedPassword = passwordUtils.generateSecurePassword(password);
        boolean res = passwordUtils.verifyUserPassword(password, encryptedPassword);
        assert (res);
    }

    @Test
    public void tst(){
        PanCake panCake= new PanCake("dd");
        panCake.setOrderId("o");
        panCake.setCreationTimestamp(System.currentTimeMillis());
        String sql="Insert into pancake ( pancake_id,strawberry,blueberry ,order_id,creation_timestamp) VALUES ("
                +"\""+panCake.getPanCakeId() +"\""+","+"\""+panCake.isStrawberrySauce() +"\""+","+"\""+panCake.isBlueberrySauce()+"\""+","+"\""+panCake.getOrderId()+"\""+
                ","+"\""+panCake.getCreationTimestamp()+"\""+ ")";
        System.out.println(sql);
    }


    @Test
    public void ordrtst() {
        Order order= new Order(UUID.randomUUID().toString());
        String sql = "Insert into order (order_id,user_id,creation_timestamp) VALUES ("
                + "\"" + order.getOrderID() + "\"" + "," + "\"" + order.getUserID() + "\"" + "," + "\"" + order.getCreationTimestamp() + "\"" + ")";
        System.out.println(sql);
    }
}

