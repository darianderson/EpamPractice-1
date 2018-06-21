package ua.nure.veretelnyk.web.command;

import ua.nure.veretelnyk.Path;
import ua.nure.veretelnyk.db.DBManager;
import ua.nure.veretelnyk.db.entity.Carriage;
import ua.nure.veretelnyk.db.entity.Route;
import ua.nure.veretelnyk.db.entity.Station;
import ua.nure.veretelnyk.db.entity.UserRoute;
import ua.nure.veretelnyk.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketSearchCmd extends Command {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException, AppException {
        String fromStation, fromCountry, toStation, toCountry;
        try {
            fromStation = req.getParameter("from").split(", ")[0];
            fromCountry = req.getParameter("from").split(", ")[1];

            toStation = req.getParameter("to").split(", ")[0];
            toCountry = req.getParameter("to").split(", ")[1];
        } catch (ArrayIndexOutOfBoundsException e){
            throw new AppException("Wrong input data.");
        }


        DBManager db = DBManager.getInstance();
        List<Route> routes = db.getRoutes();

        List<UserRoute> appropriateRoute = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd MMMM yyyy HH:mm");
        DateFormat timeFormater = new SimpleDateFormat("HH:mm");
        for(Route route : routes){
            List<Route.Stop> stops = route.getStations();
            int fromId = -1;
            int toId = -1;

            UserRoute userRoute = new UserRoute();

            userRoute.routeId = route.getId();
            userRoute.trainModel = route.getTrain().getModel().getModel();
            userRoute.buyLink = "/controller?command=get_page&page=buy&routeId="+route.getId() +
                "&from="+req.getParameter("from")+
                    "&to="+req.getParameter("to");
            Date depDate = null, arrDate;

            int counter = 0;
            for(Route.Stop stop : stops){
                Station s = stop.getStation();

                if (fromStation.equals(s.getName()) && fromCountry.equals(s.getCountry().getFullName())) {
                    fromId = counter;
                    depDate = stop.getDeparture();
                    userRoute.departure = df.format(depDate);
                }

                if (toStation.equals(s.getName()) && toCountry.equals(s.getCountry().getFullName()) && depDate != null && fromId != -1) {
                    toId = counter;
                    arrDate = stop.getArrival();
                    userRoute.arrival = df.format(arrDate);

                    long roadTime = arrDate.getTime() - depDate.getTime();
                    userRoute.roadTime = timeFormater.format(new Date(roadTime));
                }

                counter++;
            }

            if(fromId < toId) {
                userRoute.departureStation = fromStation + ", " + fromCountry;
                userRoute.arrivalStation = toStation + ", " + toCountry;

                List<Carriage> carriages = db.getCarriages(route.getTrain());
                userRoute.price = carriages.get(0).getPrice();
                appropriateRoute.add(userRoute);
            }
        }


        req.setAttribute("appRoute", appropriateRoute);
        return Path.PAGE_HOME;
    }
}