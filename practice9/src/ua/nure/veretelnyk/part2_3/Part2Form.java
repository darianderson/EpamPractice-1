package ua.nure.veretelnyk.part2_3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Part2Form extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder sb = new StringBuilder("<html><body><form action=\'part2_3select\'>")
                .append( "<input type=\"text\" name=\"name\">  <select name=\"sport\">");

        String[] sports = getServletContext().getInitParameter("list").split(" ");
        for (String s : sports)
            sb.append("<option value=\"").append(s).append("\">").append(s).append("</option>");

        sb.append("</select>     <input type=\"submit\">")
                .append("</form></body></html>");

        resp.getWriter().write(sb.toString());

    }
}
