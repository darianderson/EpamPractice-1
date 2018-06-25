package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Carriage;
import ua.nure.veretelnyk.db.entity.CarriageType;
import ua.nure.veretelnyk.db.entity.Ticket;
import ua.nure.veretelnyk.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class BuyTicketCmd extends Command {

    private static final Logger LOG = Logger.getLogger(BuyTicketCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        String routeId = req.getParameter("routeId");
        String carriageStr = req.getParameter("carriage");
        String placeStr = req.getParameter("place");
        String type = req.getParameter("type");

        String errorMessage = Message.EVERYTHING_IS_FINE;
        String forward = Path.PAGE_BUY;
        DBManager db = DBManager.getInstance();
        String login = (String) req.getSession().getAttribute("login");


        List<Carriage> carriages = db.getCarriages(db.getRoute(Integer.parseInt(routeId)).getTrain());
        int carriage = Integer.parseInt(carriageStr);
        int place = Integer.parseInt(placeStr);
        User user = db.getUser(login);

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setType(CarriageType.valueOf(type));
        ticket.setRoute(db.getRoute(Integer.parseInt(routeId)));
        ticket.setPlaceNo(place);
        ticket.setCarriageNo(carriage);
        LOG.debug("User is buying ticket: "+ticket);

        int fromId = Integer.parseInt(req.getParameter("from"));
        int toId = Integer.parseInt(req.getParameter("to"));

        ticket.setFrom(db.getStation(fromId));
        ticket.setTo(db.getStation(toId));

        req.setAttribute("from", fromId);
        req.setAttribute("to", toId);

        if (carriage > carriages.size() || carriage < 0)
            errorMessage = Message.WRONG_CARRIAGE;
        else if(place > carriages.get(carriage).getTotalPlaces() || place < 0)
            errorMessage = Message.WRONG_PLACE;
        else if(login == null)
            errorMessage = Message.NOT_LOGIN;
        else{


            List<Ticket> tickets = db.getTicketsForUser(user);
            boolean isTicketBought = false;
            for(Ticket t : tickets)
                if (t.getTo().getId() == ticket.getTo().getId() && t.getFrom().getId() == ticket.getFrom().getId() &&
                        t.getCarriageNo() == ticket.getCarriageNo() && t.getPlaceNo() == ticket.getPlaceNo()){
                        isTicketBought = true;
                }

            boolean res = db.addTicket(ticket);
            if (isTicketBought)
                errorMessage = Message.ALREADY_BUY_THIS_TICKET;
            else if (!res)
                errorMessage = Message.WOOPS;
            else {
                forward = Path.PAGE_SETTINGS;
            }
        }

        req.setAttribute("routeId", routeId);
        req.setAttribute("carriage", carriageStr);
        req.setAttribute("placeStr", placeStr);

        LOG.debug("Result of buying ticket: "+errorMessage);
        req.setAttribute(Message.ERROR_MESSAGE_ATTRIBUTE, errorMessage);
        return forward;
    }
}
