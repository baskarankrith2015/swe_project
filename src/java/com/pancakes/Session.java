package com.pancakes;

/**
 * Created by krithikabaskaran on 10/28/18.
 */
public class Session {


    String createCookie(String userId){
    return userId+":"+System.currentTimeMillis();
}
String getUserId(String cookie){
        return cookie.split(":")[0];
}
}
