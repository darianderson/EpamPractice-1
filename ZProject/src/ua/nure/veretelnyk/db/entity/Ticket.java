package ua.nure.veretelnyk.db.entity;

import java.util.Date;

public class Ticket {
    private int id;
    private User user;
    private Route route;
    private String arrivalStr;
    private String departureStr;
    private Station from;
    private Station to;
    private CarriageType type;
    private int carriageNo;
    private int placeNo;
    private boolean wasPayed;
    private String linkToRoute;

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + user +
                ", route=" + route +
                ", carriageNo=" + carriageNo +
                ", placeNo=" + placeNo +
                '}';
    }

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

    public String getLinkToRoute() {
        return linkToRoute;
    }

    public void setLinkToRoute(String linkToRoute) {
        this.linkToRoute = linkToRoute;
    }

    public String getArrivalStr() {
        return arrivalStr;
    }

    public void setArrivalStr(String arrivalStr) {
        this.arrivalStr = arrivalStr;
    }

    public String getDepartureStr() {
        return departureStr;
    }

    public void setDepartureStr(String departureStr) {
        this.departureStr = departureStr;
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
