package ua.nure.veretelnyk.db.entity;

public class Ticket {
    private int id;
    private User user;
    private Route route;
    private Station from;
    private Station to;
    private CarriageType type;
    private int carriageNo;
    private int placeNo;
    private boolean wasPayed;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

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

    public CarriageType getType() {
        return type;
    }
    public void setType(CarriageType type) {
        this.type = type;
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

    public Station getFrom() {
        return from;
    }
    public void setFrom(Station from) {
        this.from = from;
    }

    public Station getTo() {
        return to;
    }
    public void setTo(Station to) {
        this.to = to;
    }

    public boolean isWasPayed() {
        return wasPayed;
    }
    public void setWasPayed(boolean wasPayed) {
        this.wasPayed = wasPayed;
    }
}
