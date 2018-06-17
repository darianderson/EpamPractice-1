package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCmd extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        DBManager dbManager = DBManager.getInstance();
        User user = dbManager.getUser(login, pass);

        String forward;
        if(user != null) {
            req.getSession().setAttribute("login", user.getLogin());
            forward = Path.PAGE_HOME;
        }
        else {
            req.getSession().setAttribute("login", "null");
            throw new AppException("user not found");
        }
        return forward;
    }
}
