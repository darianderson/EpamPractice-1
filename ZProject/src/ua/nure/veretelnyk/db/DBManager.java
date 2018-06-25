package ua.nure.veretelnyk.db;

import ua.nure.veretelnyk.db.entity.*;
import ua.nure.veretelnyk.exception.AppException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBManager {
    private DataSource dataSource;

    private static DBManager instance;

    public static synchronized DBManager getInstance(){
        if (instance == null)
            instance = new DBManager();
        return instance;
    }


    private DBManager(){
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) envContext.lookup("jdbc/railways");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection(){
        Connection con = null;
        try{
            con = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    public User getUser(String login, String pass){
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            if("".equals(pass)) {
                statement = con.prepareStatement("SELECT * FROM users WHERE login=?");
                statement.setString(1, login);
            }
            else{
                statement = con.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
                statement.setString(1, login);
                statement.setString(2, pass);
            }

            rs = statement.executeQuery();

            if(rs.next())
                return extractUser(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUser(String login){
        return getUser(login, "");
    }

    public boolean addUser(String login, String pass){
        PreparedStatement statement;

        try(Connection con = getConnection()){
            statement = con.prepareStatement("INSERT INTO users(login, password) VALUE (?,?)");
            statement.setString(1, login);
            statement.setString(2, pass);

            statement.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private User extractUser(ResultSet rs){
        User user = User.create();
        try {
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setRole(Role.getFor(rs.getInt("role_id")));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public boolean updateUser(User user){
        PreparedStatement statement;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("UPDATE users SET name=?, surname=? WHERE id=?");
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getId());

            statement.executeUpdate();
            con.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public Country getCountry(int id){
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM countries WHERE id=?");
            statement.setInt(1, id);
            rs = statement.executeQuery();


            if (rs.next()){
                return extractCountry(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Country extractCountry(ResultSet rs) throws SQLException {
        Country country = new Country();

        country.setId(rs.getInt("id"));
        country.setFullName(rs.getString("full_name"));
        country.setShortName(rs.getString("short_name"));

        return country;
    }

    public List<Country> getCountries(){
        List<Country> countries = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM countries");
            rs = statement.executeQuery();

            while (rs.next())
                countries.add(extractCountry(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }


    public List<Station> getStations(){
        List<Station> stations = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM stations");
            rs = statement.executeQuery();

            while (rs.next())
                stations.add(extractStation(rs));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stations;
    }

    public Station getStation(int id){
        PreparedStatement statement;
        ResultSet rs;
        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM stations WHERE id=?");
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()){
                return extractStation(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Station extractStation(ResultSet rs) throws SQLException {
        Station station = new Station();

        station.setId(rs.getInt("id"));
        station.setName(rs.getString("name"));
        int countryId = rs.getInt("country_id");
        station.setCountry(getCountry(countryId));

        return station;
    }



    private Route getRouteFromList(List<Route> routeList, int id){
        for(Route r : routeList)
            if (r.getId() == id)
                return r;
        return null;
    }

    public Model getModel(int id){
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM models WHERE id=?");
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if(rs.next()){
                Model m = new Model();
                m.setId(rs.getInt("id"));
                m.setModel(rs.getString("model"));
                return m;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Train getTrain(int id){
        PreparedStatement statement;
        ResultSet rs;

        List<Country> countries = getCountries();
        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM trains WHERE id=?");
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                Train train = new Train();
                train.setId(rs.getInt("id"));
                train.setModel(getModel(rs.getInt("model_id")));
                return train;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Route> getRoutes(){
        List<Route> routes = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM routes");
            rs = statement.executeQuery();

            while (rs.next()){
                Route route = getRouteFromList(routes, rs.getInt("id"));
                if(route == null) {
                    route = new Route();
                    route.setId(rs.getInt("id"));
                    route.setTrain(getTrain(rs.getInt("train_id")));
                    routes.add(route);
                }

                addStationForRoute(route, rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return routes;
    }



    public Route getRoute(int id){
        Route route = new Route();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM routes WHERE id=?");
            statement.setInt(1,id);
            rs = statement.executeQuery();

            if(rs.next()) {
                route.setId(id);
                route.setTrain(getTrain(rs.getInt("train_id")));
                addStationForRoute(route, rs);
            }

            while (rs.next())
                addStationForRoute(route, rs);



        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return route;
    }

    private void addStationForRoute(Route route, ResultSet rs) throws SQLException, ParseException {
        Station station = getStation(rs.getInt("station_id"));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date arrival = df.parse(rs.getString("arrival"));
        Date departure = df.parse(rs.getString("departure"));
        route.addStation(station, arrival, departure);
    }

    public List<Ticket> getTicketsForUser(User user){
        List<Ticket> tickets = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM tickets WHERE user_id=?");
            statement.setInt(1, user.getId());
            rs = statement.executeQuery();

            while(rs.next()){
                Ticket ticket = new Ticket();

                ticket.setId(rs.getInt("id"));
                ticket.setUser(user);
                ticket.setRoute(getRoute(rs.getInt("route_id")));
                ticket.setCarriageNo(rs.getInt("carriage_no"));
                ticket.setPlaceNo(rs.getInt("place"));
                ticket.setFrom(getStation(rs.getInt("fromSt")));
                ticket.setTo(getStation(rs.getInt("toSt")));
                ticket.setWasPayed(rs.getBoolean("wasPayed"));

                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    public boolean addTicket(Ticket ticket){
        PreparedStatement statement;

        try(Connection con = getConnection()){
            statement = con.prepareStatement("INSERT INTO tickets(user_id, route_id, fromSt, toSt, type, carriage_no, place, wasPayed) VALUE (?,?,?,?,?,?,?,?)");
            int k=1;
            statement.setInt(k++, ticket.getUser().getId());
            statement.setInt(k++, ticket.getRoute().getId());
            statement.setInt(k++, ticket.getFrom().getId());
            statement.setInt(k++,ticket.getTo().getId());
            statement.setInt(k++,ticket.getType().ordinal()+1);
            statement.setInt(k++, ticket.getCarriageNo());
            statement.setInt(k++, ticket.getPlaceNo());
            statement.setBoolean(k++, ticket.isWasPayed());

            statement.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public CarriageType getCarriageType(int id){
        CarriageType type = null;
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM carriage_type WHERE id=?");
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if(rs.next())
                type = CarriageType.valueOf(rs.getString("type"));


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return type;
    }

    public Carriage getCarriage(int id){
        Carriage carriage = new Carriage();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM carriages WHERE id=?");
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while(rs.next()){
                carriage.setId(id);
                carriage.setType(getCarriageType(rs.getInt("type")));
                carriage.setTotalPlaces(rs.getInt("total_places"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carriage;
    }

    public List<Carriage> getCarriages(Train train){
        List<Carriage> carriages = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM train_carriage WHERE train_id=?");
            statement.setInt(1, train.getId());
            rs = statement.executeQuery();

            while(rs.next()){
                Carriage carriage = getCarriage(rs.getInt("carriage_id"));
                if(carriage != null) {
                    carriage.setNumber(rs.getInt("carriage_no"));
                    carriage.setPrice(rs.getInt("price"));
                    carriages.add(carriage);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carriages;
    }


    public List<RoutePart> getRouteParts(){
        List<RoutePart> parts = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM routes");

            rs = statement.executeQuery();

            while(rs.next()){
                RoutePart part = new RoutePart();
                part.setRouteId(rs.getInt("id"));
                part.setTrainId(rs.getInt("train_id"));
                part.setStationId(rs.getInt("station_id"));
                part.setArrival(df.parse(rs.getString("arrival")));
                part.setDeparture(df.parse(rs.getString("departure")));
                parts.add(part);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return parts;
    }


    public boolean addRoutePart(RoutePart part){
        PreparedStatement statement;

        try(Connection con = getConnection()){
            statement = con.prepareStatement("INSERT INTO routes VALUE (?,?,?,?,?)");
            statement.setInt(1, part.getRouteId());
            statement.setInt(2, part.getTrainId());
            statement.setInt(3, part.getStationId());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            statement.setString(4, df.format(part.getArrival()));
            statement.setString(5, df.format(part.getDeparture()));

            statement.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteRoutePart(RoutePart part){
        PreparedStatement statement;

        try(Connection con = getConnection()){
            statement = con.prepareStatement("DELETE FROM routes WHERE id=? AND train_id=? AND station_id=?");
            statement.setInt(1, part.getRouteId());
            statement.setInt(2, part.getTrainId());
            statement.setInt(3, part.getStationId());

            statement.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addStation(String name, int countryId){
        PreparedStatement statement;

        try(Connection con = getConnection()){
            statement = con.prepareStatement("INSERT INTO stations (name, country_id) VALUE (?,?)");
            statement.setString(1, name);
            statement.setInt(2, countryId);

            statement.executeUpdate();
            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteStation(String name, int countryId){
        PreparedStatement statement;

        try(Connection con = getConnection()){
            statement = con.prepareStatement("SET FOREIGN_KEY_CHECKS=0");
            statement.executeQuery();

            statement = con.prepareStatement("DELETE FROM stations WHERE name=? AND country_id=?");
            statement.setString(1, name);
            statement.setInt(2, countryId);

            statement.executeUpdate();

            statement = con.prepareStatement("SET FOREIGN_KEY_CHECKS=1");
            statement.executeQuery();

            con.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
