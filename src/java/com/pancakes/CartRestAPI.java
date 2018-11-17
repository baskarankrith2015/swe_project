package com.pancakes;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Created by krithikabaskaran on 11/4/18.
 */
@Path("/cart")
public class CartRestAPI {
    @PUT
    @Path("/{cartID}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrderRestApi(@FormParam("plain") final Integer plainItems,
                                       @FormParam("strawberry") final Integer strawberryItems,
                                       @FormParam("blueberry") final Integer blueberryItems,
                                       @CookieParam("session_cookie") Cookie cookie) throws SQLException {

        Cart c= new Cart(plainItems,strawberryItems,blueberryItems);
        Session session= new Session();
        String userId=session.getUserId(cookie.getValue());
        c.setUserId(userId);
        return null;
    }

    private void writeCart() {

    }
}
