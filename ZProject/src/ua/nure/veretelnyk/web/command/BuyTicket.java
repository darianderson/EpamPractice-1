package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Route;
import ua.nure.veretelnyk.db.entity.Ticket;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BuyTicket extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String routeId = req.getParameter("routeId");
        String carriage = req.getParameter("carriage");
        String place = req.getParameter("place");
        System.out.println("haha");

        String errorMessege;
        DBManager db = DBManager.getInstance();
        String login = (String) req.getSession().getAttribute("login");
        if(login == null){
            errorMessege = "You are nor log in";
        }
        else{
            User user = db.getUser(login);
            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setRoute(db.getRoute(Integer.parseInt(routeId)));
            ticket.setPlaceNo(Integer.parseInt(place));
            ticket.setCarriageNo(Integer.parseInt(carriage));
            boolean res = db.addTicket(ticket);
            if (!res)
                errorMessege = "Woops...";
            System.out.println(" O\n/-\\\n ^");
            List<Ticket> tickets = db.getTicketsForUser(user);
            req.setAttribute("tickets", tickets);
        }

        return Path.PAGE_SETTINGS;
    }
}
