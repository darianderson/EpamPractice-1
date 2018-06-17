package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

public class RegistrCmd extends Command {

    private static final Logger LOG = Logger.getLogger(RegistrCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String passRepeat = req.getParameter("pass_repeat");

        DBManager dbManager = DBManager.getInstance();

        String signinError = "no signin errors;";
        String forward = Path.PAGE_LOGIN;
        if(login == null || pass == null || passRepeat == null){
            signinError = "Wrong input.";
        }
        else if(!pass.equals(passRepeat)){
            signinError = "Password doesn't match.";
        }
        else if(dbManager.getUser(login) != null){
            signinError = "You already registered.";
        }
        else{
            boolean result = dbManager.addUser(login, pass);
            if(!result)
                signinError = "Woops... Something went wrong.";
            else {
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("userRoleId", 2); // 2 default role id
                forward = Path.PAGE_HOME;
            }


        }

        req.setAttribute("isRegistrLoaded", true);
        req.setAttribute("signinError", signinError);
        LOG.debug(signinError);

        return forward;
    }
}
