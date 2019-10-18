package servlets;

import accounts.AccountService;
import accounts.UserProfile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private final AccountService accountService = null;

//    public SignUpServlet(AccountService accountService){
//        this.accountService = accountService;
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserProfile profile = new UserProfile(login, password, login + "@mail.com");
        accountService.addNewUser(profile);
        //accountService.addSession(req.getSession().getId(), profile);

        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("Status code: " + resp.getStatus() + "<br>");
        resp.getWriter().println("User " + login + " is registered");

    }
}
