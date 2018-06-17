package ua.nure.veretelnyk.db;

import ua.nure.veretelnyk.db.entity.Country;
import ua.nure.veretelnyk.db.entity.Role;
import ua.nure.veretelnyk.db.entity.Station;
import ua.nure.veretelnyk.db.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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




    public List<Country> getCountries(){
        List<Country> countries = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM countries");
            rs = statement.executeQuery();


            while (rs.next()){
                Country country = new Country();

                country.setId(rs.getInt("id"));
                country.setFullName(rs.getString("full_name"));
                country.setShortName(rs.getString("short_name"));

                countries.add(country);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return countries;
    }


    public List<Station> getStations(){
        List<Station> stations = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        List<Country> countries = getCountries();
        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM stations");
            rs = statement.executeQuery();

            while (rs.next()){
                Station station = new Station();

                station.setId(rs.getInt("id"));
                station.setName(rs.getString("name"));
                int countryId = rs.getInt("country_id");
                for(Country country : countries)
                    if(country.getId() == countryId) {
                        station.setCountry(country);
                        break;
                    }
                stations.add(station);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stations;
    }



}
