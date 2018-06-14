package ua.nure.veretelnyk.part2_3;

import ua.nure.veretelnyk.part2_2.SportRow;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Part2Select extends HttpServlet  {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<SportRow> list = (List<SportRow>) getServletContext().getAttribute("list3");


        System.out.println("Part2Select#doGet");
        String sport = req.getParameter("sport");
        String name = req.getParameter("name");

        //start new session
        HttpSession session = req.getSession();
        String sessionName = (String) session.getAttribute("name");
        if (name.equals(sessionName)){
            resp.getWriter().write("wrong input");
            return;
        }
        session.setAttribute("name",name);

        for(SportRow row : list)
            if (row.getSport().equals(sport))
                row.insertName(name);

        getServletContext().setAttribute("list3", list);
        StringBuilder table = new StringBuilder("<html><body><table>");
        for(SportRow row : list)
            table.append("<tr><td>").append(row.getSport())
                    .append("</td><td>").append(row.getCount()).append("</td><td>")
                    .append("</td><td>").append(row.getNames()).append("</td></tr>");
        table.append("</table></body></html>");
        resp.getWriter().write(table.toString());
    }

}
