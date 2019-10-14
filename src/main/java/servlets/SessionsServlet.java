package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.ClazzUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionsServlet extends HttpServlet {

    private final AccountService accountService;
    private static final Logger logger = LogManager.getLogger(ClazzUtil.returnClazzName());

    public SessionsServlet(AccountService accountService) {
        this.accountService = accountService;
        logger.debug("SessionsServlet constructor - accountService is passed to SessionServlet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("entered doGet method");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        logger.debug("entered doPost method");
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        if (login == null) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Status code: " + resp.getStatus());
            return;
        }

        UserProfile profile = accountService.getUserByLogin(login);

        if (profile == null || !profile.getPass().equals(pass)) {
            resp.setContentType("text/html;charset=utf-8");
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().println("Status code: " + resp.getStatus());
            return;
        }

        accountService.addSession(req.getSession().getId(), profile);
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println(json);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
