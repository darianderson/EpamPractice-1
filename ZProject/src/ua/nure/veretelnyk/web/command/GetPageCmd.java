package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetPageCmd extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String page = req.getParameter("page");
        switch (page){
            default:
            case "index": return Path.PAGE_HOME;
            case "login": return Path.PAGE_LOGIN;
            case "settings": return Path.PAGE_SETTINGS;
            case "admin": return Path.PAGE_ADMIN;
        }
    }
}
