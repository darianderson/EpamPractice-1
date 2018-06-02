package ua.nure.veretelnyk.practice9.part2_1;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Part2Form extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder sb = new StringBuilder("<html><body><form action=\'part2_1select\'>")
                .append( "<select name=\"sport\">");

        String[] sports = getServletContext().getInitParameter("list").split(" ");
        for (String s : sports)
            sb.append("<option value=\"").append(s).append("\">").append(s).append("</option>");

        sb.append("</select>     <input type=\"submit\">")
                .append("</form></body></html>");

        resp.getWriter().write(sb.toString());

    }
}
