package ua.nure.veretelnyk.db.entity;

public class Ticket {
    private User user;
    private Route route;
    private int carriageNo;
    private int placeNo;

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }
    public void setRoute(Route route) {
        this.route = route;
    }

    public int getCarriageNo() {
        return carriageNo;
    }
    public void setCarriageNo(int carriageNo) {
        this.carriageNo = carriageNo;
    }

    public int getPlaceNo() {
        return placeNo;
    }
    public void setPlaceNo(int placeNo) {
        this.placeNo = placeNo;
    }
}
