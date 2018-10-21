package com.pancakes;

/**
 * Created by krithikabaskaran on 10/19/18.
 */
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/hello")
public class HelloServlet {
private HtmlFileReader htmlFileReader= new HtmlFileReader();
    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey";
    }

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        try {
            return htmlFileReader.readFile("src/resource/hello.html");
        }catch (Exception e){
            return "Something went wrong"+e.getMessage();
        }
        //return "<html> " + "<title>" + "Hello Jersey html" + "</title>"
        //        + "<body><h1>" + "Hello Jersey html" + "</body></h1>" + "</html> ";
    }

}