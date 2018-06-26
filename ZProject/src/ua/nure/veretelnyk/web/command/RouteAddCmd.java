package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.RoutePart;
import ua.nure.veretelnyk.db.entity.Station;
import ua.nure.veretelnyk.db.entity.Train;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RouteAddCmd extends Command {

    private static final Logger LOG = Logger.getLogger(RouteAddCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        DBManager db = DBManager.getInstance();
        String routeIdStr = req.getParameter("route_id");
        String trainIdStr = req.getParameter("train_id");
        String stationIdStr = req.getParameter("station_id");
        String arrivalStr = req.getParameter("arrival");
        String departureStr = req.getParameter("departure");

        LOG.debug("Adding a route: " + routeIdStr + "; time: " + arrivalStr + " - " + departureStr);

        if (routeIdStr == null || trainIdStr == null || stationIdStr == null ||
                arrivalStr == null || departureStr == null || routeIdStr.isEmpty() ||
                trainIdStr.isEmpty() || stationIdStr.isEmpty() || arrivalStr.isEmpty() ||
                departureStr.isEmpty()) {
            LOG.debug("Something is null. Throw new AppExcrption " + Message.WRONG_INPUT);
            throw new AppException(Message.WRONG_INPUT);
        }


        // check for train, station, date
        Train train = db.getTrain(Integer.parseInt(trainIdStr));
        Station station = db.getStation(Integer.parseInt(stationIdStr));
        if (train == null){
            req.setAttribute(Message.ERROR_MESSAGE_ATTRIBUTE, "Wrong train id");
            return Path.PAGE_ERROR;
        }
        if (station == null){
            //db.addCountryNo(Integer.parseInt(stationIdStr));
            req.setAttribute(Message.ERROR_MESSAGE_ATTRIBUTE, "Wrong station id");
            return Path.PAGE_ERROR;
        }

        RoutePart part = new RoutePart();
        part.setRouteId(Integer.parseInt(routeIdStr));
        part.setTrainId(Integer.parseInt(trainIdStr));
        part.setStationId(Integer.parseInt(stationIdStr));

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH");
        try {
            Date arr = df.parse(arrivalStr);
            Date dep = df.parse(departureStr);

            if (dep.getTime() < arr.getTime()){
                req.setAttribute(Message.ERROR_MESSAGE_ATTRIBUTE, "Departure date can not be before arrival");
                return Path.PAGE_ERROR;
            }

            part.setArrival(arr);
            part.setDeparture(dep);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(!db.addRoutePart(part)){
            LOG.debug("Not adding route to db");
            throw new AppException(Message.WOOPS);
        }

        LOG.debug("Route add finish");
        return Path.PAGE_ADMIN;
    }

}
