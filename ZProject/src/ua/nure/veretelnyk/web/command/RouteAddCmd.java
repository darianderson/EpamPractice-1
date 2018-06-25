package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.RoutePart;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class RouteAddCmd extends Command {

    private static final Logger LOG = Logger.getLogger(RouteAddCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        String routeIdStr = req.getParameter("route_id");
        String trainIdStr = req.getParameter("train_id");
        String stationIdStr = req.getParameter("station_id");
        String arrivalStr = req.getParameter("arrival");
        String departureStr = req.getParameter("departure");

        System.out.println(arrivalStr);
        System.out.println(departureStr);
        LOG.debug("Adding a route: " + routeIdStr + "; time: " + arrivalStr + " - " + departureStr);

        if (routeIdStr == null || trainIdStr == null || stationIdStr == null ||
                arrivalStr == null || departureStr == null) {
            LOG.debug("Something is null. Throw new AppExcrption " + Message.WRONG_INPUT);
            throw new AppException(Message.WRONG_INPUT);
        }

        RoutePart part = new RoutePart();
        part.setRouteId(Integer.parseInt(routeIdStr));
        part.setTrainId(Integer.parseInt(trainIdStr));
        part.setStationId(Integer.parseInt(stationIdStr));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            part.setArrival(df.parse(arrivalStr));
            part.setDeparture(df.parse(departureStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        DBManager db = DBManager.getInstance();
        if(!db.addRoutePart(part)){
            LOG.debug("Not adding route to db");
            throw new AppException(Message.WOOPS);
        }

        LOG.debug("Route add finish");
        return Path.PAGE_ADMIN;
    }
}
