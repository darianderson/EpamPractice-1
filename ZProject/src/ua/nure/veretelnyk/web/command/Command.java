package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

public abstract class Command implements Serializable {

    public abstract String execute(HttpServletRequest req,
                                   HttpServletResponse resp) throws IOException, ServletException, AppException;

}
