package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Carriage;
import ua.nure.veretelnyk.db.entity.Route;
import ua.nure.veretelnyk.db.entity.Ticket;
import ua.nure.veretelnyk.db.entity.User;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class BuyTicket extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String routeId = req.getParameter("routeId");
        String carriageStr = req.getParameter("carriage");
        String placeStr = req.getParameter("place");
        System.out.println("haha");



        String errorMessage = "Everything is fine.";
        String forward = Path.PAGE_BUY;
        DBManager db = DBManager.getInstance();
        String login = (String) req.getSession().getAttribute("login");


        List<Carriage> carriages = db.getCarriages(db.getRoute(Integer.parseInt(routeId)).getTrain());
        int carriage = Integer.parseInt(carriageStr);
        int place = Integer.parseInt(placeStr);

        System.out.println(carriage);
        System.out.println(place);
        System.out.println(carriages.size());
        System.out.println(carriages.get(carriage).getTotalPlaces());

        if (carriage > carriages.size() || carriage < 0)
            errorMessage = "Wrong carriage";
        else if(place > carriages.get(carriage).getTotalPlaces() || place < 0)
            errorMessage = "Wrong place";
        else if(login == null)
            errorMessage = "You are nor log in";
        else{
            User user = db.getUser(login);
            Ticket ticket = new Ticket();
            ticket.setUser(user);
            ticket.setRoute(db.getRoute(Integer.parseInt(routeId)));
            ticket.setPlaceNo(place);
            ticket.setCarriageNo(carriage);
            // TODO set from and to here


            boolean res = db.addTicket(ticket);
            if (!res)
                errorMessage = "Woops...";
            else
                forward = Path.PAGE_SETTINGS;
            System.out.println(" O\n/-\\\n ^");
        }

        req.setAttribute("errorMessage", errorMessage);
        return forward;
    }
}
