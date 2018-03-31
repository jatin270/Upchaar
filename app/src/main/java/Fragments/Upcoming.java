package Fragments;

/**
 * Created by raghav on 31/3/18.
 */

public class Upcoming {

        String hospital;
        String time;
        String date;
        String token;

        public Upcoming(String hospital,String time,String date,String token){
            this.hospital = hospital;
            this.time = time;
            this.date = date;
            this.token = token;
        }

        public String getHospital(){return this.hospital ;}
        public String getTime(){return this.time ;}
        public String getDate(){return this.date ;}
        public String getToken(){return this.token;}

}