package ua.nure.veretelnyk.web;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
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

public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("hahah");
        process(req, resp);
    }


    private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String cmdName = req.getParameter("command");
        System.out.println(cmdName);
        Command cmd = CommandContainer.get(cmdName);


        String forward = Path.PAGE_ERROR;
        try {
            forward = cmd.execute(req, resp);
        } catch (AppException e) {
            e.printStackTrace();
        }
        System.out.println(forward);
        req.getRequestDispatcher(forward).forward(req, resp);
    }


}
