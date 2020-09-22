package model;

import java.util.Date;

public class Car {

    private String id;
    private CarType type;
    private Date enterTime;
    private Date exitTime;

    public Car(String id, CarType type, Date enterTime, Date exitTime) {
        this.id = id;
        this.type = type;
        this.enterTime = enterTime;
        this.exitTime = exitTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public CarType getType() {
        return type;
    }

    public void setType(CarType type) {
        this.type = type;
    }

    public Date getEnterTime() {
        return enterTime;
    }

    public void setEnterTime(Date enterTime) {
        this.enterTime = enterTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }
}
