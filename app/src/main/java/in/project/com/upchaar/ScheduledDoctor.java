package in.project.com.upchaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

import Fragments.Doctor;
import client.RestClient;
import models.DaySchedule;
import models.DoctorUser;
import models.HospitalUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

public class ScheduledDoctor extends AppCompatActivity {

    private Intent intent;
    ArrayList<DaySchedule> daySchedules;
    ArrayList<DoctorUser> doctorUsers;
    ArrayList<HospitalUser> hospitalUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_doctor);


    }


}
