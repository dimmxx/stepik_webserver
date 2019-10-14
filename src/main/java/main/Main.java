package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AllRequestsServlet;
import servlets.SessionsServlet;
import util.ClazzUtil;

public class Main {

    private static final Logger logger = LogManager.getLogger(ClazzUtil.returnClazzName());

    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/api/v1/sessions");


        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        logger.debug("server is started");
        server.join();
        logger.debug("server is joined");


    }
}
