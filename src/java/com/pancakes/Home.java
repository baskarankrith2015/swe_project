package com.pancakes;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by krithikabaskaran on 11/15/18.
 */
@Path("/")
public class Home {
    private HtmlFileReader htmlFileReader = new HtmlFileReader();

   // @Path("/home")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveHomePage() {
        try {
            return htmlFileReader.readFile("src/resource/html/main_page.html");
        } catch (Exception e) {
            return "Something went wrong" + e.getMessage();
        }
    }
    @Path("/style.css")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveStylePage() {
        try {
            return htmlFileReader.readFile("src/resource/html/style.css");
        } catch (Exception e) {
            return "Something went wrong" + e.getMessage();
        }
    }

    @Path("/signup")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveSignupPage() {
        try {
            return htmlFileReader.readFile("src/resource/html/Signup_Page.html");
        } catch (Exception e) {
            return "Something went wrong" + e.getMessage();
        }
    }
}
