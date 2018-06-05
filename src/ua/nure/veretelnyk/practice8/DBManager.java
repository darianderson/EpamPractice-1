package ua.nure.veretelnyk.practice8;

import ua.nure.veretelnyk.practice8.entity.DBEntry;
import ua.nure.veretelnyk.practice8.entity.DBException;
import ua.nure.veretelnyk.practice8.entity.Group;
import ua.nure.veretelnyk.practice8.entity.User;

import java.awt.*;
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
    private static final String SQL_SELECT_ID_FROM_USERS_GROUP = "SELECT group_id FROM users_groups WHERE user_id = ?";
    private static final String SQL_SELECT_NAME_FROM_GROUPs_WHERE_ID = "SELECT name FROM groups WHERE id = ?";
    private static final String SQL_UPDATE_GROUPS_WHERE_NAME = "UPDATE groups SET name = ? WHERE id = ?";
    private static final String SQL_DELETE_GROUP_WHERE_ID = "DELETE FROM groups WHERE id = ?";
    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null)
            instance = new DBManager();

        return instance;
    }

    // Insert user to database
    public boolean insertUser(User user) throws DBException{
        try ( Connection con = getConnection() ) {
            if (getUser(user.getLogin()) != null)
                return false;

            PreparedStatement statement =
                    con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, user.getLogin());

            return setIdFromStatement(statement, user);
        }catch (SQLException e){
            throw new DBException(e);
        }
    }
    // Insert group to database
    public boolean insertGroup(Group group) throws DBException{
        try ( Connection con = getConnection() ) {

            if (getGroup(group.getLogin()) != null)
                return false;

            PreparedStatement statement =
                    con.prepareStatement(SQL_INSERT_GROUP, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, group.getLogin());

            return setIdFromStatement(statement, group);
        }catch (SQLException e){
            throw new DBException(e);
        }
    }

    // setting up id for DBEntry from statement
    private boolean setIdFromStatement(PreparedStatement statement, DBEntry entry) throws SQLException{
        if (statement.executeUpdate() > 0) {
            ResultSet set = statement.getGeneratedKeys();
            if (set.next()) {
                entry.setId(set.getInt(1));
                return true;
            }
        }
        return false;
    }

    // returning connection to db
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

    // getting list of all users
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
    // returns the list of all groups
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

    // get user from ResultSet
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
    // get group from ResultSet
    private Group extractGroup(ResultSet rs) throws DBException {
        Group group = new Group();
        try {
            group.setId(rs.getInt("id"));
            group.setLogin(rs.getString("name"));
        }catch (SQLException e){
            throw new DBException(e);
        }
        return group;
    }

    // get user from db
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
    //get group from db
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

    // set one pair user - group with connection con
    private void setGroupsForUser(Connection con, User user, Group group) throws SQLException{
        PreparedStatement statement;
        ResultSet rs;


            // checking if there is no such pair in db
            statement = con.prepareStatement(SQL_USERS_GROUPS_PAIR_CHECK);
            statement.setString(1, String.valueOf(user.getId()));
            statement.setString(2, String.valueOf(group.getId()));
            rs = statement.executeQuery();
            if (rs.next() && rs.getInt(1) >= 1)
                return;

            statement = con.prepareStatement(SQL_INSERT_USERS_GROUPS);

            statement.setString(1, String.valueOf(user.getId()));
            statement.setString(2, String.valueOf(group.getId()));

            statement.executeUpdate();

    }
    // creating connection and setting up user - group pair
    public void setGroupsForUser(User user, Group group) throws DBException {
        System.out.println(user);
        System.out.println(group);
        Connection con = null;
        try {
            try {
                con = getConnection();
                con.setAutoCommit(false);
                setGroupsForUser(con, user, group);
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw new DBException(e);
            } finally {
                if (con != null)
                    con.close();
            }
        }catch (SQLException | NullPointerException e) {
            e.printStackTrace();
            //throw new DBException(e);
        }
    }
    // setting up multiple group - user pairs
    public void setGroupsForUser(User user, Group... groups) throws DBException {
        if (groups.length <= 0)
            return;
        Connection con = null;
        try {
            try {
                con = getConnection();
                con.setAutoCommit(false);
                for (Group group : groups)
                    setGroupsForUser(con, user, group);
                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw new DBException(e);
            } finally {
                if (con != null)
                    con.close();
            }
        }catch (SQLException | NullPointerException e) {
            throw new DBException(e);
        }
    }

    // getting list of all groups for one special user
    public List<Group> getUsersGroup(User user) throws DBException{
        List<Group> groups = new ArrayList<>();
        PreparedStatement statement;
        ResultSet rs;

        try (Connection con = getConnection()){
            statement = con.prepareStatement(SQL_SELECT_ID_FROM_USERS_GROUP);
            statement.setString(1, String.valueOf(user.getId()));
            rs = statement.executeQuery();

            while (rs.next()){
                Group g = new Group();
                g.setId(rs.getInt("group_id"));
                groups.add(g);
            }
            for(Group g : groups){
                statement = con.prepareStatement(SQL_SELECT_NAME_FROM_GROUPs_WHERE_ID);
                statement.setString(1, String.valueOf(g.getId()));
                rs = statement.executeQuery();
                if(rs.next())
                    g.setLogin(rs.getString(1));
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            throw new DBException(e);
        }
        return groups;
    }

    public void deleteGroup(Group group) throws DBException{
        PreparedStatement statement;
        //ResultSet rs;

        try(Connection con = getConnection()){
            statement = con.prepareStatement(SQL_DELETE_GROUP_WHERE_ID);
            statement.setString(1, String.valueOf(group.getId()));
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new DBException(e);
        }

    }
    public void updateGroup(Group group) throws DBException{
        PreparedStatement statement;
        //ResultSet rs;

        try(Connection con = getConnection()){
            statement = con.prepareStatement(SQL_UPDATE_GROUPS_WHERE_NAME);
            statement.setString(1, group.getName());
            statement.setString(2, String.valueOf(group.getId()));
            statement.executeUpdate();
        } catch (SQLException e) {
            //e.printStackTrace();
            throw new DBException(e);
        }
    }

}
