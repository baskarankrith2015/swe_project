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
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong"+e1.getMessage();
            }
        }
        if (checkUser == null) {
            createUser(userId, userName, password);
            try {
                User newUser = readUser(userId);
                return htmlFileReader.readFile("src/resource/html/Menupage.html",session.createCookie(newUser.getUserId()));

            } catch (SQLException | IOException e) {
                try {
                    return htmlFileReader.readFile("src/resource/html/error_page.html");
                } catch (IOException e1) {
                    return "Something Horribly went wrong"+e1.getMessage();
                }
            }
        } else {
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong"+e1.getMessage();
            }
        }

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
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong";
            }
        }
        if (checkUser == null) {

            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong"+e1.getMessage();
            }

        } else {
            boolean verified = passwordUtils.verifyUserPassword(password, checkUser.password);
            if(!verified){
                try {
                    return htmlFileReader.readFile("src/resource/html/error_page.html");
                } catch (IOException e1) {
                    return "Something Horribly went wrong"+e1.getMessage();
                }
            }
            else {
                return htmlFileReader.readFile("src/resource/html/Menupage.html",session.createCookie(checkUser.getUserId()));
            }
        }
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
            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong"+e1.getMessage();
            }
        }
        if (checkUser == null) {

            try {
                return htmlFileReader.readFile("src/resource/html/error_page.html");
            } catch (IOException e1) {
                return "Something Horribly went wrong "+ e1.getMessage();
            }

        } else {
            try {
                return htmlFileReader.readFile("src/resource/html/main_page.html");
            } catch (Exception e) {
                return "Something went wrong" + e.getMessage();
            }
        }

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
            try {
                return htmlFileReader.readFile("src/resource/html/main_page.html");
            } catch (Exception e1) {
                return "Something went wrong" + e.getMessage();
            }
        }
    }
    @Path("/images/blueberries-1867398_1920.jpg")
    @GET
    @Produces("image/jpg")
    public byte[] blueberryImage() throws IOException {

        return imageRendering.getImage("blueberries-1867398_1920.jpg");

    }
}
