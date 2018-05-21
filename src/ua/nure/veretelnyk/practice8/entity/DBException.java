package ua.nure.veretelnyk.practice8.entity;

import java.sql.SQLException;

public class DBException extends Exception {

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause){
        this("Sorry database not available.", cause);
    }

    public DBException(){
        this(new SQLException());
    }
}
