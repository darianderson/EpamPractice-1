package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrCmd extends Command {

    private static final Logger LOG = Logger.getLogger(RegistrCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOG.debug("Registration process start");

        String login = req.getParameter("login");
        String pass = req.getParameter("pass");
        String passRepeat = req.getParameter("pass_repeat");

        DBManager dbManager = DBManager.getInstance();

        String signinError = Message.EVERYTHING_IS_FINE;
        String forward = Path.PAGE_LOGIN;
        if(login == null || pass == null || passRepeat == null){
            signinError = Message.WRONG_INPUT;
        }
        else if(!pass.equals(passRepeat)){
            signinError = Message.PASSWORD_DONOT_MATCH;
        }
        else if(dbManager.getUser(login) != null){
            signinError = Message.YOU_ALREADY_REG;
        }
        else{
            boolean result = dbManager.addUser(login, pass);
            if(!result)
                signinError = Message.WOOPS;
            else {
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("userRoleId", 2); // 2 default role id
                forward = Path.PAGE_HOME;
            }


        }

        req.setAttribute("isRegistrLoaded", true);
        req.setAttribute("signinError", signinError);
        LOG.debug("Registration output: " + signinError);

        return forward;
    }
}
