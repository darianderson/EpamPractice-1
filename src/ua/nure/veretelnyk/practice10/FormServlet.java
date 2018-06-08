package ua.nure.veretelnyk.practice10;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


public class FormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> names = (ArrayList<String>) req.getSession().getAttribute("names");
        String rem = req.getParameter("remove");
        if(rem != null && "true".equals(rem))
            names.clear();

        resp.sendRedirect("part3.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> names = (ArrayList<String>) req.getSession().getAttribute("names");
        if (names == null){
            names = new ArrayList<>();
            req.getSession().setAttribute("names",names);
        }
        String myname = req.getParameter("myname");
        if (myname != null)
            names.add(myname);

        resp.sendRedirect("part3.jsp");
    }
}


// download tomcat core->zip
// unzip it to java folder
// project structure -> libraries -> add -> tomcat/lib/servlet-api.jar
// add web.xml
// add tomcat in running configuration tick with javascript debuger

/*

 */