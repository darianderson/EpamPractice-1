package ua.nure.veretelnyk.practice9.part2_1;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Part2Select extends HttpServlet  {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String, Integer> map = (Map<String, Integer>) getServletContext().getAttribute("map");

        System.out.println("Part2Select#doGet");
        String sport = req.getParameter("sport");

        map.put(sport, map.get(sport) + 1);
        System.out.println(map);

        getServletContext().setAttribute("map", map);
        resp.getWriter().write(map.toString());
    }

}
