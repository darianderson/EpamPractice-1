package ua.nure.veretelnyk;

import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Route;

import java.util.List;

public class Path {
    public static final String ERROR_MESSAGE = "errorMessage";

    private static final String JSP = "/WEB-INF/jsp/";
    public static final String PAGE_ERROR = JSP + "error_page.jsp";
    public static final String PAGE_HOME = JSP + "index.jsp";
    public static final String PAGE_LOGIN = JSP + "login.jsp";
    public static final String PAGE_SETTINGS = JSP + "settings.jsp";
    public static final String PAGE_ADMIN = JSP + "admin.jsp";

}
