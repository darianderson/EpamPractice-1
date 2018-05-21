package ua.nure.veretelnyk.practice8;

import ua.nure.veretelnyk.practice8.entity.DBException;
import ua.nure.veretelnyk.practice8.entity.Group;
import ua.nure.veretelnyk.practice8.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {

    private static final String CONNECTION_URL = "jdbc:mysql://localhost/practice8"
            + "?user=blackwell&password=salt";

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";
    private static final String SQL_FIND_ALL_GROUPS = "SELECT * FROM groups";
    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM users WHERE login = ?";
    private static final String SQL_FIND_GROUP_BY_NAME = "SELECT * FROM groups WHERE name = ?";
    private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?)";
    private static final String SQL_INSERT_GROUP = "INSERT INTO groups VALUES (DEFAULT, ?)";
    private static final String SQL_INSERT_USERS_GROUPS = "INSERT INTO users_groups VALUES (?, ?)";
    private static final String SQL_USERS_GROUPS_PAIR_CHECK = "SELECT COUNT(*) FROM users_groups WHERE user_id = ? AND group_id = ?";

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();

        return instance;
    }

    public boolean insertUser(User user) throws DBException{
        try ( Connection con = getConnection() ) {

            if (getUser(user.getLogin()) != null)
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



    public boolean insertGroup(Group group) throws DBException{
        try ( Connection con = getConnection() ) {

            if (getGroup(group.getName()) != null)
                return false;

            PreparedStatement statement =
                    con.prepareStatement(SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, group.getName());

            if (statement.executeUpdate() > 0) {
                ResultSet set = statement.getGeneratedKeys();
                if (set.next()) {
                    group.setId(set.getInt(1));
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

    public List<Group> findAllGroups() throws DBException{
        List<Group> groups = new ArrayList<>();
        Statement st;

        try (Connection con = getConnection()) {
            st = con.createStatement();
            ResultSet rs = st.executeQuery(SQL_FIND_ALL_GROUPS);
            while (rs.next())
                groups.add(extractGroup(rs));

        } catch (SQLException e) {
            throw new DBException(e);
        }

        return groups;
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
    private Group extractGroup(ResultSet rs) throws DBException {
        Group group = new Group();
        try {
            group.setId(rs.getInt("id"));
            group.setName(rs.getString("name"));
        }catch (SQLException e){
            throw new DBException(e);
        }
        return group;
    }
    public User getUser(String login) throws DBException{
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

    public Group getGroup(String name) throws DBException{
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement(SQL_FIND_GROUP_BY_NAME);
            statement.setString(1, name);

            rs = statement.executeQuery();

            if (rs.next())
                return extractGroup(rs);

        } catch (SQLException e) {
            throw new DBException(e);
        }
        return null;
    }

    // TODO make autocommit = false
    public void setGroupsForUser(User user, Group group) throws DBException{
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){

            statement = con.prepareStatement(SQL_USERS_GROUPS_PAIR_CHECK);
            rs = statement.executeQuery();
            if(rs.next() && rs.getInt(1) >= 1)
                return;

            statement = con.prepareStatement(SQL_INSERT_USERS_GROUPS);

            statement.setString(1, String.valueOf(user.getId()));
            statement.setString(2, String.valueOf(group.getId()));

            rs = statement.executeQuery();

        } catch (SQLException e) {
            throw new DBException(e);
        }

    }
    // TODO remake with transaction
    public void setGroupsForUser(User user, Group... groups) throws DBException {
        if (groups.length <= 0)
            return;

        for (Group group : groups)
            setGroupsForUser(user, group);
    }


//    public List<Group> getUsersGroup(User user) throws DBException{
//        PreparedStatement statement;
//        ResultSet rs;
//
//        try (Connection con = getConnection()){
//
//            statement = con.prepareStatement("SELECT group_id FROM users_group WHERE user_id = ?");
//            statement.setString(1, String.valueOf(user.getId()));
//            rs = statement.executeQuery();
//            while (rs.next()){
//                statement = con.prepareStatement("SELECT name FROM groups WHERE id = ?");
//                statement.setString();
//            }
//
//        } catch (SQLException e) {
//            throw new DBException(e);
//        }
//    }

}
