package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;
import util.ClazzUtil;

public class App {

    private static final Logger logger = LogManager.getLogger(ClazzUtil.returnClazzName());

    public static void main( String[] args ) throws Exception {

        String pathSpec = "/*";
        AllRequestsServlet allRequestsServlet = new AllRequestsServlet();
        logger.debug("allRequestServlet created");
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(allRequestsServlet), pathSpec);
        logger.debug("context - servlet added, pathSpec: " + pathSpec);

        Server server = new Server(8080);
        logger.debug("server created");
        server.setHandler(context);

        server.start();
        logger.debug("server started");
        server.join();
    }
}
