package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Message;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.RoutePart;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class RouteDeleteCmd extends Command {

    private static final Logger LOG = Logger.getLogger(RouteDeleteCmd.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws AppException {
        String routeIdStr = req.getParameter("route_id");
        String trainIdStr = req.getParameter("train_id");
        String stationIdStr = req.getParameter("station_id");

        LOG.debug("Deleting route " + routeIdStr);

        if (routeIdStr == null || trainIdStr == null || stationIdStr == null) {
            AppException ex = new AppException(Message.WRONG_INPUT);
            LOG.debug("Throw new AppException " + ex);
            throw ex;
        }

        RoutePart part = new RoutePart();
        part.setRouteId(Integer.parseInt(routeIdStr));
        part.setTrainId(Integer.parseInt(trainIdStr));
        part.setStationId(Integer.parseInt(stationIdStr));

        DBManager db = DBManager.getInstance();
        if(!db.deleteRoutePart(part)) throw new AppException(Message.WOOPS);

        LOG.debug("Route deleting finished");
        return Path.PAGE_ADMIN;
    }
}
