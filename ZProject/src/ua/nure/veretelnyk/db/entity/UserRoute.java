package ua.nure.veretelnyk.db.entity;

public class UserRoute {
    public int routeId;
    public String trainModel;
    public String departure;
    public String departureStation;
    public String arrival;
    public String arrivalStation;
    public String roadTime;
    public String compartment;
    public String berth;
    public String common;
    public String price;
    public String detailsLink;
    public String buyLink;


    public int getRouteId() {
        return routeId;
    }

    public String getTrainModel() {
        return trainModel;
    }

    public String getDeparture() {
        return departure;
    }

    public String getArrival() {
        return arrival;
    }

    public String getRoadTime() {
        return roadTime;
    }

    public String getBuyLink() {
        return buyLink;
    }
}
