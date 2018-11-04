package com.pancakes.test;

import com.pancakes.LoginRestAPI;
import com.pancakes.PasswordUtils;
import com.pancakes.User;
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
}
