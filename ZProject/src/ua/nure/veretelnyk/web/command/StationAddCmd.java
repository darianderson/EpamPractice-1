package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StationAddCmd extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String name = req.getParameter("name-of-station");
        String country = req.getParameter("country");

        if (name == null || country == null)
            throw new AppException("Wrong data input");

        DBManager db = DBManager.getInstance();

        if (!db.addStation(name, Integer.parseInt(country)))
            return Path.PAGE_ERROR;

        return Path.PAGE_ADMIN;
    }
}
