package models;

/**
 * Created by ( Jatin Bansal ) on 31-03-2018.
 */

public class DaySchedule {

    String url;
    int id;
    int day;
    String time_slot_from;
    String time_slot_to;
    int doctor;
    int hospital;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTime_slot_from() {
        return time_slot_from;
    }

    public void setTime_slot_from(String time_slot_from) {
        this.time_slot_from = time_slot_from;
    }

    public String getTime_slot_to() {
        return time_slot_to;
    }

    public void setTime_slot_to(String time_slot_to) {
        this.time_slot_to = time_slot_to;
    }

    public int getDoctor() {
        return doctor;
    }

    public void setDoctor(int doctor) {
        this.doctor = doctor;
    }

    public int getHospital() {
        return hospital;
    }

    public void setHospital(int hospital) {
        this.hospital = hospital;
    }
}
