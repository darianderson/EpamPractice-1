package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StationDeleteCmd extends Command {

    private static final Logger LOG = Logger.getLogger(StationDeleteCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        String name = req.getParameter("name-of-station");
        String country = req.getParameter("country");

        LOG.debug("Deleting station " + name + " in " + country);

        if (name == null || country == null)
            throw new AppException(Message.WRONG_INPUT);

        DBManager db =DBManager.getInstance();

        if (!db.deleteStation(name, Integer.parseInt(country)))
            return Path.PAGE_ERROR;

        LOG.debug("Station deleted");
        return Path.PAGE_ADMIN;
    }
}
