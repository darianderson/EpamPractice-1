package ua.nure.veretelnyk.practice9.part1_123;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CalcServlet extends HttpServlet {

    private static final String MINUS = "minus";
    private static final String PLUS = "plus";
    private static final String X = "x";
    private static final String Y = "y";
    private static final String OPERATION = "op";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int x = Integer.parseInt(req.getParameter(X));
        int y = Integer.parseInt(req.getParameter(Y));
        String operation = req.getParameter(OPERATION);

        int result = 0;
        if (MINUS.equals(operation))
            result = x - y;
        else if (PLUS.equals(operation))
            result = x + y;

        resp.getWriter().write(
                "<html><body>" +
                        "Result: " + result + "<br>" +
                        "  <a href=\"index.jsp\">back</a>"
        + "</body></html>");
    }
}
