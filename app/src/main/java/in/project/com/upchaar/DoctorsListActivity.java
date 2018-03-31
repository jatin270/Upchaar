package in.project.com.upchaar;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import Fragments.DocListAdapter;
import Fragments.Doctor;
import client.RestClient;
import models.DaySchedule;
import models.DoctorUser;
import models.HospitalUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

public class DoctorsListActivity extends AppCompatActivity {

    private Intent intent;
    ArrayList<DaySchedule> daySchedules;
    ArrayList<DoctorUser> doctorUsers;
    ArrayList<HospitalUser> hospitalUsers;
    List<Doctor> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent=getIntent();
        receiveDaySchedules();
        String string=intent.getStringExtra("location");
        System.out.println(string);



        DocListAdapter adapter = new DocListAdapter(list);
        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_view);
        rv.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void fillList() {

        System.out.println("Check-----------------------");
        for(int i=0;i<daySchedules.size();i++){
            DaySchedule work=daySchedules.get(i);

            String doctor_name="Check";
            String hospital_name="Check";
            for(int j=0;j<doctorUsers.size();j++){
                if(doctorUsers.get(j).getUser()==work.getDoctor()){
                    doctor_name=doctorUsers.get(j).getDepartment();
                    break;
                }
            }

            for(int j=0;j<hospitalUsers.size();j++){
                if(hospitalUsers.get(j).getUser()==work.getHospital()){
                    hospital_name=hospitalUsers.get(j).getHospital_name();
                    break;
                }
            }

            Doctor doctor=new Doctor(doctor_name,hospital_name,"asdf","dsaff");
            list.add(doctor);
        }
    }

    private String doctorName, hospitalName, time, qualifications;

    public DoctorsListActivity(){}

    public DoctorsListActivity(String doctorName, String hospitalName, String time, String qualifications) {
        this.doctorName = doctorName;
        this.hospitalName = hospitalName;
        this.time = time;
        this.qualifications = qualifications;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String name) {
        this.doctorName = name;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String name) {
        this.hospitalName = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String name) {
        this.time = name;
    }

    public String getQualifications() {
        return qualifications;
    }

    public void setQualifications(String name) {
        this.qualifications = name;
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
                    check();
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
                    check();
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
                    check();
                } else {

                }
            }
            @Override
            public void onFailure(Call<ArrayList<HospitalUser>> call, Throwable t) {

            }
        });

    }
    public void check(){
        if(daySchedules!=null&&(doctorUsers!=null)&&(hospitalUsers!=null)){
            fillList();
        }
    }
}


