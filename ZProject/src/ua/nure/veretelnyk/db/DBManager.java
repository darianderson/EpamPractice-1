package ua.nure.veretelnyk.db;

import ua.nure.veretelnyk.db.entity.Role;
import ua.nure.veretelnyk.db.entity.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
        System.out.println("Extracting user: "+login+" "+pass);
        try (Connection con = getConnection()){
            statement = con.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
            statement.setString(1, login);
            statement.setString(2, pass);

            rs = statement.executeQuery();

            if(rs.next())
                return extractUser(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    private User extractUser(ResultSet rs){
        User user = User.create();
        try {
            System.out.println(rs.getString("login"));
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




}
