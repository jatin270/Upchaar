package models;

/**
 * Created by raghav on 30/3/18.
 */

public class HospitalUser {

    String url;
    int user;
    String hospital_name;
    int no_of_beds;

    String latitude;
    String longitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public int getNo_of_beds() {
        return no_of_beds;
    }

    public void setNo_of_beds(int no_of_beds) {
        this.no_of_beds = no_of_beds;
    }

}