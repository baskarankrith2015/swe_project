package com.pancakes;

import javax.ws.rs.*;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by krithikabaskaran on 10/21/18.
 */


@Path("/user")
public class LoginRestAPI {
    private Session session=new Session();
    private HtmlFileReader htmlFileReader = new HtmlFileReader();
    private static DatabaseAccess databaseAccess= new DatabaseAccess();
    ImageRendering imageRendering = new ImageRendering();

    private static PasswordUtils passwordUtils= new PasswordUtils();
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayPlainTextHello() {
        return "Hello user";
    }

    @POST
    @Path("/signup")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String createUserRestApi(@FormParam("user_name") String userName,
                                      @FormParam("user_id") String userId,
                                      @FormParam("password") final String password) {
        User checkUser = null;
        try {
            checkUser = readUser(userId);
        } catch (SQLException e) {
            return "r";
                   // Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
        if (checkUser == null) {
            createUser(userId, userName, password);
            try {
                User newUser = readUser(userId);
                NewCookie newCookie= new NewCookie ("session_cookie",session.createCookie(newUser.getUserId()));
                return htmlFileReader.readFile("src/resource/html/Menupage.html",session.createCookie(newUser.getUserId()));

            } catch (SQLException e) {
                return "w";
                //Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
           return  "q";
                   //Response.status(Response.Status.UNAUTHORIZED).entity("User already exists").build();
        }
        return "Shit happened";
    }
    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String loginUserRestApi(@FormParam("user_id") String userId,
                                      @FormParam("password") final String password) throws IOException {
        User checkUser = null;
        try {
            checkUser = readUser(userId);
        } catch (SQLException e) {
            //return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
        if (checkUser == null) {

              // return  Response.status(Response.Status.UNAUTHORIZED).entity("Wrong credentials").build();

        } else {
            boolean verified = passwordUtils.verifyUserPassword(password, checkUser.password);
            if(!verified){
              // return  Response.status(Response.Status.UNAUTHORIZED).entity("Wrong credentials").build();
            }
            else {


                NewCookie newCookie= new NewCookie ("session_cookie",session.createCookie(checkUser.getUserId()));
    //return  Response.status(Response.Status.OK).cookie(newCookie).entity("User logged in").build();
                return htmlFileReader.readFile("src/resource/html/Menupage.html",session.createCookie(checkUser.getUserId()));
            }
        }
    return "error";
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_HTML)
    public String logoutUserRestApi(@FormParam("user_id") String userId,
                                   @FormParam("password") final String password) throws IOException {
        User checkUser = null;
        try {
            checkUser = readUser(userId);
        } catch (SQLException e) {
            //return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e).build();
        }
        if (checkUser == null) {

            // return  Response.status(Response.Status.UNAUTHORIZED).entity("Wrong credentials").build();

        } else {
            try {
                return htmlFileReader.readFile("src/resource/html/main_page.html");
            } catch (Exception e) {
                return "Something went wrong" + e.getMessage();
            }
        }
        return "error";
    }

    public void createUser(String userId, String userName, String password) {
        String securep=passwordUtils.generateSecurePassword(password);
        String sql="Insert into user_details (user_id,user_name,user_password) VALUES ("
                +"\""+userId +"\""+","+"\""+userName+"\""+","+"\""+securep+"\""+")";
        System.out.println(sql);
        databaseAccess.runDatabaseQuery(sql);

    }
    public User readUser(String userId) throws SQLException {
        String sql="Select * from user_details where user_id ="
                +"\""+userId+"\""+";";
        User user = null;
        SqlReturnClass returnClass = databaseAccess.readDatabaseQuery(sql);
        ResultSet rs=returnClass.getResultSet();
        while(rs.next()){
            //Retrieve by column name
            String id  = rs.getString("user_id");
            String password = rs.getString("user_password");
            String name = rs.getString("user_name");
            user = new User(id,name,password);
        }
        //STEP 6: Clean-up environment
       returnClass.close();
        return user;
    }

    @Path("/order")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String serveOrderPage( @CookieParam("session-cookie") Cookie cookie) {
        try {
            String sessionVal=cookie.getValue();
            return htmlFileReader.readFile("src/resource/html/Orderpage.html",sessionVal);
        } catch (Exception e) {
            return "Something went wrong" + e.getMessage();
        }
    }
    @Path("/images/blueberries-1867398_1920.jpg")
    @GET
    @Produces("image/jpg")
    public byte[] blueberryImage() {
        try {
            return imageRendering.getImage("blueberries-1867398_1920.jpg");


        } catch (Exception e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
        return null;
    }
}
