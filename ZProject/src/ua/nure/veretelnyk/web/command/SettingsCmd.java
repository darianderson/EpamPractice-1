package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class SettingsCmd extends Command {

    private static final String ENGLISH = "English";
    private static final String SPANISH = "Spanish";
    private static final String RUSSIAN = "Russian";
    private static final Logger LOG = Logger.getLogger(SettingsCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String errorMessage = Message.DATA_IS_SAVED;

        String language = req.getParameter("locale");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        Locale loc;
        switch (language){
            default:
            case ENGLISH:
                loc = new Locale("en");
                req.setAttribute("locale", "en");
                break;
            case SPANISH:
                loc = new Locale("es");
                req.setAttribute("locale", "es");
                break;
            case RUSSIAN:
                loc = new Locale("ru");
                req.setAttribute("locale", "ru");
                break;
        }
        Locale.setDefault(loc);
        LOG.debug("Set locale " + loc);

        String login = (String) req.getSession().getAttribute("login");
        if(login == null)
            errorMessage = Message.NOT_LOGIN;
        else if (name == null || surname == null || name.isEmpty() || surname.isEmpty())
            errorMessage = Message.WRONG_INPUT;
        else{
            DBManager db = DBManager.getInstance();
            User user = db.getUser(login);
            user.setName(name);
            user.setSurname(surname);
            if(!db.updateUser(user))
                errorMessage=Message.WOOPS;
        }

        LOG.debug("Settings command output " + errorMessage);
        req.setAttribute(Message.ERROR_MESSAGE_ATTRIBUTE, errorMessage);
        return Path.PAGE_SETTINGS;
    }
}
