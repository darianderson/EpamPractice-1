package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetPageCmd extends Command {

    private static final Logger LOG = Logger.getLogger(GetPageCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = req.getParameter("page");
        DBManager db = DBManager.getInstance();
        LOG.debug("Request for the page: "+page);
        switch (page) {
            default:
            case "index":
                setStations(req);
                return Path.PAGE_HOME;
            case "login":
                return Path.PAGE_LOGIN;
            case "settings":
                String login = (String) req.getSession().getAttribute("login");
                List<Ticket> tickets = new ArrayList<>();
                if (login != null) {
                    User user = db.getUser(login);
                    tickets = db.getTicketsForUser(user);
                    if (user.getName() != null)
                        req.setAttribute("userName", user.getName());
                    if (user.getSurname() != null)
                        req.setAttribute("userSurname", user.getSurname());
                }
                req.setAttribute("tickets", tickets);
                return Path.PAGE_SETTINGS;
            case "admin":
                List<Station> stations = db.getStations();
                req.setAttribute("stations", stations);

                List<RoutePart> routes = db.getRouteParts();
                req.setAttribute("routes", routes);

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
