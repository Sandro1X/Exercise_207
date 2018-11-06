package BL;

import java.io.Serializable;

public class Station implements Comparable, Serializable{
    
    private String place;
    private int level;
    private double temp;
    private int hum;

    public Station(String place, int level, double temp, int hum) {
        this.place = place;
        this.level = level;
        this.temp = temp;
        this.hum = hum;
    }

    public String getPlace() {
        return place;
    }

    public int getLevel() {
        return level;
    }

    public double getTemp() {
        return temp;
    }

    public int getHum() {
        return hum;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public void setHum(int hum) {
        this.hum = hum;
    }

    @Override
    public int compareTo(Object o) {
        Station s = (Station) o;
        return this.place.toLowerCase().compareTo(s.place.toLowerCase());
    }
}
