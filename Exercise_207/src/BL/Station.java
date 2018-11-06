package BL;

import java.io.Serializable;

public class Station implements Comparable, Serializable {

    private String place;
    private int level;
    private double temp;
    private int hum;

    public Station(String place, int level, double temp, int hum) throws Exception {
            this.place = place;
            this.level = level;
            setTemp(temp);
            setHum(hum);


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

    public void setTemp(double temp) throws Exception {
        if (!(temp < -35 || temp > 45)) {
            this.temp = temp;
        } else {
            throw new Exception("Please input a valid temperature!");
        }
    }

    public void setHum(int hum) throws Exception {
        if (!(hum < 0 || hum > 100)) {
            this.hum = hum;
        } else {
            throw new Exception("Please input a valid humidity!");
        }
    }

    @Override
    public int compareTo(Object o) {
        Station s = (Station) o;
        return this.place.toLowerCase().compareTo(s.place.toLowerCase());
    }
}
