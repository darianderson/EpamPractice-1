package ua.nure.veretelnyk.web;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Route;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;
import ua.nure.veretelnyk.web.command.Command;
import ua.nure.veretelnyk.web.command.CommandContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Controller extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("doGet request in Controller");
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOG.debug("doPost request in Controller");
        process(req, resp);
    }


    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String cmdName = req.getParameter("command");
        LOG.debug("Command name: " + cmdName);

        Command cmd = CommandContainer.get(cmdName);

        String forward = Path.PAGE_ERROR;
        try {
            forward = cmd.execute(req, resp);
        } catch (AppException e) {
            e.printStackTrace();
            LOG.error("Getting exception from executing command.");
        }

        LOG.debug("Forward to " + forward);
        req.getRequestDispatcher(forward).forward(req, resp);
    }
}


