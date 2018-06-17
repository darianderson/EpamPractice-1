package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Role;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCmd extends Command {
    private static final Logger LOG = Logger.getLogger(LoginCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        LOG.debug("Getting user with login="+login);

        DBManager dbManager = DBManager.getInstance();
        User user = dbManager.getUser(login, pass);

        String forward = Path.PAGE_LOGIN;
        if(user != null) {
            req.getSession().setAttribute("login", user.getLogin());
            req.getSession().setAttribute("userRoleId", user.getRole().ordinal()+1);
            LOG.debug("User extracted. Chuwi, we are home.");
            forward = Path.PAGE_HOME;
        }
        else {
            LOG.debug("User not found. Wrong username/password.");
            req.setAttribute("signinError", "Wrong username/password.");
        }
        return forward;
    }
}
