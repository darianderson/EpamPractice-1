package ua.nure.veretelnyk.db.entity;

public enum Role {
    ADMIN, CLIENT;

    public static Role getFor(int num){
        switch (num){
            case 1: return ADMIN;
            default:
            case 2: return CLIENT;
        }
    }

}
