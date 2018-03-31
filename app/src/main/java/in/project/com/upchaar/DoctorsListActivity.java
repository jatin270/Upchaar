package in.project.com.upchaar;

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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import Fragments.Doctor;

public class DoctorsListActivity extends AppCompatActivity {

    ArrayList<Doctor> list = new ArrayList<>();
    private ListView simpleList;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpleList = (ListView)findViewById(R.id.list_view);

        // list  = list of objects sent by api


        Doctor doc = new Doctor("Abhinav", "Medanta", "10:00", "cardiologist");
        list.add(doc);
        doc = new Doctor("Abhinav", "Medanta", "10:00", "cardiologist");
        list.add(doc);

        customAdapter = new CustomAdapter(list,getApplicationContext());
        simpleList.setAdapter(customAdapter);
    }

}


