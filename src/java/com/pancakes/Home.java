package com.pancakes;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by krithikabaskaran on 11/15/18.
 */
@Path("/")
public class Home {
    private HtmlFileReader htmlFileReader = new HtmlFileReader();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveHomePage() {
        try {
            return htmlFileReader.readFile("src/resource/html/main_page.html");
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }
    @Path("/style.css")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveStylePage() {
        try {
            return htmlFileReader.readFile("src/resource/html/style.css");
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }

    @Path("/signup")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveSignupPage() {
        try {
            return htmlFileReader.readFile("src/resource/html/create_account_page.html");
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }

    @Path("/order")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveOrderPage( @CookieParam("session-cookie") Cookie cookie) {
        try {
           String sessionVal=cookie.getValue();
            return htmlFileReader.readFile("src/resource/html/Orderpage.html",sessionVal);
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }
    @Path("/carts")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveCartPage( @CookieParam("session-cookie") Cookie cookie) {
        try {
            String sessionVal=cookie.getValue();
            return htmlFileReader.readFile("src/resource/html/cart_page.html",sessionVal);
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }

    @Path("/location")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveLocationPage( @CookieParam("session-cookie") Cookie cookie) {
        try {
            String sessionVal=cookie.getValue();
            return htmlFileReader.readFile("src/resource/html/location_page.html",sessionVal);
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }
    @Path("/menu")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveMenuPage( @CookieParam("session-cookie") Cookie cookie) {
        try {
            String sessionVal=cookie.getValue();
            return htmlFileReader.readFile("src/resource/html/Menupage.html",sessionVal);
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }
    @Path("/track")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveTrackingPage( @CookieParam("session-cookie") Cookie cookie) {
        try {
            String sessionVal=cookie.getValue();
            return htmlFileReader.readFile("src/resource/html/tracking_page.html",sessionVal);
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }
    @Path("/logout")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveLogoutPage( @CookieParam("session-cookie") Cookie cookie) {
        try {
            String sessionVal="";
            return htmlFileReader.readFile("src/resource/html/thank_you_page.html",sessionVal);
        } catch (Exception e) {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
    }
}
