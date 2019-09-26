package academy.mate;


import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class App {

    public static void main( String[] args ) throws Exception {

        Server server = new Server(9090);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        server.setHandler(context);
        context.addServlet(new ServletHolder(), "/authform");
        server.start();
        server.join();





    }
}
