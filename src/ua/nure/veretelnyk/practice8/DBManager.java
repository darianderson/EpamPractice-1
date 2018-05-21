package ua.nure.veretelnyk.practice8;

import ua.nure.veretelnyk.practice8.entity.DBException;
import ua.nure.veretelnyk.practice8.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost/practice8"
            + "?user=blackwell&password=salt";

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login=?";
    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?)";
    private static final String SQL_INSERT_GROUP = "INSERT INTO groups VALUES (DEFAULT, ?)";
    private static final String SQL_IS_USER_EXISTS = "SELECT COUNT(*) FROM users WHERE login = ?";

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();

        return instance;
    }

    public boolean insertUser(User user) throws DBException{
        try ( Connection con = getConnection() ) {

            if (getUserByLogin(user.getLogin()) != null)
                return false;

            PreparedStatement statement =
                    con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getLogin());

            if (statement.executeUpdate() > 0) {
                ResultSet set = statement.getGeneratedKeys();
                if (set.next()) {
                    user.setId(set.getInt(1));
                    return true;
                }
            }

            return false;
        }catch (SQLException e){
            throw new DBException(e);
        }
    }




    private Connection getConnection() throws DBException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(CONNECTION_URL);
        } catch (ClassNotFoundException e) {
            throw new DBException("Class not find", e);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public List<User> findAllUsers() throws DBException{
        List<User> users = new ArrayList<>();
        Statement st;

        try (Connection con = getConnection()) {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_FIND_ALL_USERS);
            while (rs.next())
                users.add(extractUser(rs));

        } catch (SQLException e) {
            throw new DBException(e);
        }

        return users;
    }

    private User extractUser(ResultSet rs) throws DBException {
        User user = new User();
        try {
            user.setId(rs.getInt("id"));
            user.setLogin(rs.getString("login"));
        }catch (SQLException e){
            throw new DBException(e);
        }
        return user;
    }

    private User getUserByLogin(String login) throws DBException{
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            statement.setString(1, login);

            rs = statement.executeQuery();

            if (rs.next())
                return extractUser(rs);

        } catch (SQLException e) {
            throw new DBException(e);
        }
        return null;
    }
}
