package ua.nure.veretelnyk.web.command;

import org.apache.log4j.Logger;
import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Country;
import ua.nure.veretelnyk.db.entity.Station;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GetStationsCmd extends Command{
    private static final Logger LOG = Logger.getLogger(GetStationsCmd.class);
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        DBManager dbManager = DBManager.getInstance();

        LOG.debug("getting countries from db;");
        List<Station> stations = dbManager.getStations();
        List<String> stationsNames = new ArrayList<>();

        for(Station station : stations)
            stationsNames.add(station.getName() +", "+station.getCountry().getFullName());

        req.setAttribute("stations", stationsNames);

        LOG.debug("sending countries and stations back;");
        return Path.PAGE_HOME;
    }
}
