package com.pancakes;

/**
 * Created by krithikabaskaran on 10/19/18.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class ApplicationServer extends AbstractHandler
{
    public void handle(String target,
                       Request baseRequest,
                       HttpServletRequest request,
                       HttpServletResponse response)
            throws IOException, ServletException
    {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello World</h1>");
    }

    public static void main(String[] args) throws Exception
    {
        Server server = new Server(8080);
        ServletContextHandler mainHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        mainHandler.setContextPath("/");
        ResourceConfig jerseyServerResourceConfig = new ResourceConfig();
        jerseyServerResourceConfig.packages("com.pancakes");
        jerseyServerResourceConfig.register(JacksonFeature.class);
        ServletContainer servletContainer = new ServletContainer(jerseyServerResourceConfig);
        ServletHolder mainAPIServletholder = new ServletHolder(servletContainer);
        mainAPIServletholder.setInitParameter("cacheControl", "max-age=0,public");
        mainHandler.addServlet(mainAPIServletholder,"/*");
        GzipHandler gzipHandler= new GzipHandler();
        gzipHandler.addIncludedMethods("GET","POST","PUT","DELETE");
        gzipHandler.setHandler(mainHandler);
        server.setHandler(gzipHandler);

        server.start();
        //server.join();
    }
}
