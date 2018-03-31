package in.project.com.upchaar;

import android.app.ListActivity;
import android.content.Intent;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import Fragments.Doctor;
import client.RestClient;
import models.DaySchedule;
import models.DoctorUser;
import models.HospitalUser;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

public class DoctorsListActivity extends AppCompatActivity {

    private Intent intent;
    ArrayList<DaySchedule> daySchedules;
    ArrayList<DoctorUser> doctorUsers;
    ArrayList<HospitalUser> hospitalUsers;
    ArrayList<User> users;
    EditText doctor_id;
    Button submit;

    ArrayList<Doctor> list = new ArrayList<>();
    private ListView simpleList;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);
        intent=getIntent();
        receiveDaySchedules();
        String string=intent.getStringExtra("location");
        doctor_id= (EditText) findViewById(R.id.doctor_id);
        submit= (Button) findViewById(R.id.select_doctor);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("Check------------");
                Intent intent=new Intent(DoctorsListActivity.this,CalenderActivity.class);
                intent.putExtra("did",doctor_id.getText().toString().trim());
                startActivity(intent);
            }
        });

        System.out.println(string);

    }

    private void fillList() {

        System.out.println("Check-----------------------");
        for(int i=0;i<daySchedules.size();i++){
            DaySchedule work=daySchedules.get(i);

            String doctor_name="Check";
            String hospital_name="Check";
            int id=0;
            for(int j=0;j<users.size();j++){
                if(work.getDoctor() == users.get(j).getId()){
                    doctor_name=users.get(j).getUsername();
                    id=work.getDoctor();
                    break;
                }
            }

            for(int j=0;j<hospitalUsers.size();j++){
                if(hospitalUsers.get(j).getUser()==work.getHospital()){
                    hospital_name=hospitalUsers.get(j).getHospital_name();
                    break;
                }
            }

            Doctor doctor=new Doctor(doctor_name,hospital_name,""+id,"");
            list.add(doctor);

            simpleList = (ListView)findViewById(R.id.list_view);
            customAdapter = new CustomAdapter(list,getApplicationContext(),DoctorsListActivity.this);
            simpleList.setAdapter(customAdapter);

        }
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

        Call<ArrayList<User>> listuser = libraryServiceAPI.listuser();
        listuser.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                if (response.isSuccessful()) {
                    users=response.body();
                    System.out.println(users);
                    check();
                } else {

                }
            }
            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });


    }
    public void check(){
        if(daySchedules!=null&&(doctorUsers!=null)&&(hospitalUsers!=null)&&users!=null){
            fillList();
        }
    }
}


