package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCmd extends Command {

    private static final Logger LOG = Logger.getLogger(LoginCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
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
            req.setAttribute("signinError", Message.WRONG_USERNAME);
        }
        return forward;
    }
}
