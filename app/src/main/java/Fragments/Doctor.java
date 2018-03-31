package Fragments;

/**
 * Created by bhavyagupta on 30/03/18.
 */

public class Doctor {
    public String name;
    public String hospital;
    public String time;
    public String qual;

    public Doctor(String name,String hospital,String time,String abc){
        this.name = name;
        this.hospital = hospital;
        this.time = time;
        this.qual = abc;
    }

    public String getName(){return this.name ;}
    public String getHospital(){return this.hospital ;}
    public String getTime(){return this.time ;}
    public String getQual(){return this.qual ;}

}
