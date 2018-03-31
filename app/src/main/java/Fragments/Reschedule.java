package Fragments;

/**
 * Created by bhavyagupta on 31/03/18.
 */

public class Reschedule {

    String doctor;
    String hospital;
    String time;
    String date;
    String token;

    public Reschedule(String doctor,String hospital,String time,String date,String token){
        this.doctor = doctor;
        this.hospital = hospital;
        this.time = time;
        this.date = date;
        this.token = token;
    }

    public String getDoctor(){return this.doctor ;}
    public String getHospital(){return this.hospital ;}
    public String getTime(){return this.time ;}
    public String getDate(){return this.date ;}
    public String getToken(){return this.token;}


}
