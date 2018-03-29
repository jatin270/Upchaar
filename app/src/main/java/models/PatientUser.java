package models;

/**
 * Created by ( Jatin Bansal ) on 22-03-2018.
 */

public class PatientUser {


    String patient_desc;
    int user;

    public int getUser_id() {
        return user;
    }

    public void setUser_id(int user) {
        this.user = user;
    }

    public String getPatient_desc() {
        return patient_desc;
    }
    public void setPatient_desc(String patient_desc) {
        this.patient_desc = patient_desc;
    }

}
