package ua.nure.veretelnyk.practice8.entity;

public class DBException extends Exception {

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBException(Throwable cause){
        this("Sorry database not available.", cause);
    }

}
