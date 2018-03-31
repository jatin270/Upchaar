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
        intent=getIntent();

        receiveDaySchedules();

        String string=intent.getStringExtra("location");
        System.out.println(string);
        appplyfilters();
        showList();


    }

    private void showList() {

    }

    private void appplyfilters() {

    }

    private void receiveDaySchedules() {

        UpchaarService libraryServiceAPI = RestClient.getClient();

        Call<ArrayList<DaySchedule>> listschedules = libraryServiceAPI.listschedules();
        listschedules.enqueue(new Callback<ArrayList<DaySchedule>>() {
            @Override
            public void onResponse(Call<ArrayList<DaySchedule>> call, Response<ArrayList<DaySchedule>> response) {
                if (response.isSuccessful()) {
                    daySchedules=response.body();
                    System.out.println(daySchedules);
                } else {

                }
            }
            @Override
            public void onFailure(Call<ArrayList<DaySchedule>> call, Throwable t) {

            }
        });

        Call<ArrayList<DoctorUser>> listdoctor = libraryServiceAPI.listdoctor();
        listdoctor.enqueue(new Callback<ArrayList<DoctorUser>>() {
            @Override
            public void onResponse(Call<ArrayList<DoctorUser>> call, Response<ArrayList<DoctorUser>> response) {
                if (response.isSuccessful()) {
                    doctorUsers=response.body();
                    System.out.println(doctorUsers);
                } else {

                }
            }
            @Override
            public void onFailure(Call<ArrayList<DoctorUser>> call, Throwable t) {

            }
        });

        Call<ArrayList<HospitalUser>> listhospital = libraryServiceAPI.listhospital();
        listhospital.enqueue(new Callback<ArrayList<HospitalUser>>() {
            @Override
            public void onResponse(Call<ArrayList<HospitalUser>> call, Response<ArrayList<HospitalUser>> response) {
                if (response.isSuccessful()) {
                    hospitalUsers=response.body();
                    System.out.println(hospitalUsers);
                } else {

                }
            }
            @Override
            public void onFailure(Call<ArrayList<HospitalUser>> call, Throwable t) {

            }
        });
    }
}
