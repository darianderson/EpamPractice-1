package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Route;
import ua.nure.veretelnyk.db.entity.Ticket;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TicketSearchCmd extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {

        return Path.PAGE_HOME;
    }
}
