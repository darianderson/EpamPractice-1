package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SettingsCmd extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String errorMessage = "Data is saved";

        String language = req.getParameter("language");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        String login = (String) req.getSession().getAttribute("login");
        if(login == null)
            errorMessage = "You not sign in.";
        else if (name == null || surname == null || name.isEmpty() || surname.isEmpty())
            errorMessage = "Wrong input";
        else{
            DBManager db = DBManager.getInstance();
            User user = db.getUser(login);
            user.setName(name);
            user.setSurname(surname);
            if(!db.updateUser(user))
                errorMessage="Woops.. Something went wrong.";
        }


        req.setAttribute("errorMessage", errorMessage);
        return Path.PAGE_SETTINGS;
    }
}
