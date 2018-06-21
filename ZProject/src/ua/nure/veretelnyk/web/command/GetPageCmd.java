package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Carriage;
import ua.nure.veretelnyk.db.entity.Station;
import ua.nure.veretelnyk.db.entity.Ticket;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetPageCmd extends Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String page = req.getParameter("page");
        DBManager db = DBManager.getInstance();
        switch (page) {
            default:
            case "index":
                setStations(req);
                return Path.PAGE_HOME;
            case "login":
                return Path.PAGE_LOGIN;
            case "settings":
                String login = (String) req.getSession().getAttribute("login");
                User user = db.getUser(login);
                List<Ticket> tickets = db.getTicketsForUser(user);
                req.setAttribute("tickets", tickets);
                if(user.getName() != null)
                    req.setAttribute("userName", user.getName());
                if(user.getSurname() != null)
                    req.setAttribute("userSurname", user.getSurname());
                return Path.PAGE_SETTINGS;
            case "admin":
                return Path.PAGE_ADMIN;
            case "buy":
                String routeId = req.getParameter("routeId");
                String from = req.getParameter("from");
                String to = req.getParameter("to");

                req.setAttribute("routeId", routeId);
                req.setAttribute("from", from);
                req.setAttribute("to", to);

//                Ticket ticket = new Ticket();
//                ticket.setUser(db.getUser((String) req.getSession().getAttribute("login")));
//                ticket.setRoute(db.getRoute(Integer.parseInt(routeId)));
//                ticket.setFrom(db.getStation(Integer.parseInt(from)));
//                ticket.setTo(db.getStation(Integer.parseInt(to)));
//                db.addTicket(ticket);




                return Path.PAGE_BUY;
        }
    }



    private void setStations(HttpServletRequest req){
        DBManager dbManager = DBManager.getInstance();

        List<Station> stations = dbManager.getStations();
        List<String> stationsNames = new ArrayList<>();

        for(Station station : stations)
            stationsNames.add(station.getName() +", "+station.getCountry().getFullName());

        req.setAttribute("stations", stationsNames);

    }

}
