package ua.nure.veretelnyk.db.entity;

import java.util.*;

public class Route {

    private int id;
    private Train train;
    private List<Stop> stations = new ArrayList<>();

    public class Stop{
        Station station;
        Date arrival;
        Date departure;

        Stop(Station station, Date arrival, Date departure) {
            this.station = station;
            this.arrival = arrival;
            this.departure = departure;
        }

        public Station getStation() {
            return station;
        }

        public Date getArrival() {
            return arrival;
        }

        public Date getDeparture() {
            return departure;
        }
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    // DO NOT DELETE !!!
    public Train getTrain() {
        return train;
    }
    public void setTrain(Train train) {
        this.train = train;
    }

    public void addStation(Station s, Date a, Date d){
        stations.add(new Stop(s,a,d));
    }

    public List<Stop> getStations() {
        return stations;
    }
}