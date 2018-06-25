package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LogoutCmd extends Command {
    private static final Logger LOG = Logger.getLogger(LogoutCmd.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        LOG.debug("User " + req.getSession().getAttribute("login") + " logout.");
        req.getSession().invalidate();
        return Path.PAGE_LOGIN;
    }

}
