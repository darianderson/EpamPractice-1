package ua.nure.veretelnyk.db.entity;

import java.util.Date;

public class RoutePart {
    private int routeId;
    private int trainId;
    private int stationId;
    private Date arrival;
    private Date departure;


    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getTrainId() {
        return trainId;
    }

    public void setTrainId(int trainId) {
        this.trainId = trainId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    @Override
    public String
    toString() {
        return "RoutePart{" +
                "routeId=" + routeId +
                ", trainId=" + trainId +
                ", stationId=" + stationId +
                ", arrival=" + arrival +
                ", departure=" + departure +
                '}';
    }
}
