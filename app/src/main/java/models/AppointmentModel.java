package models;

/**
 * Created by ( Jatin Bansal ) on 31-03-2018.
 */

public class AppointmentModel {

    String url;
    int id;
    int patient;
    int doctor;
    int hospital;
    String appointment_date;
    String time_slot_from;
    int status;
    int token_no;

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

    public int getPatient() {
        return patient;
    }

    public void setPatient(int patient) {
        this.patient = patient;
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

    public String getAppointment_date() {
        return appointment_date;
    }

    public void setAppointment_date(String appointment_date) {
        this.appointment_date = appointment_date;
    }

    public String getTime_slot_from() {
        return time_slot_from;
    }

    public void setTime_slot_from(String time_slot_from) {
        this.time_slot_from = time_slot_from;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getToken_no() {
        return token_no;
    }

    public void setToken_no(int token_no) {
        this.token_no = token_no;
    }
}
