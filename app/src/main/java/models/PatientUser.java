package models;

/**
 * Created by ( Jatin Bansal ) on 22-03-2018.
 */

public class PatientUser {


    String patient_desc;
    int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPatient_desc() {
        return patient_desc;
    }
    public void setPatient_desc(String patient_desc) {
        this.patient_desc = patient_desc;
    }

}
