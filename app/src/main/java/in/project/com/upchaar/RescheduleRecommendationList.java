package in.project.com.upchaar;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import Fragments.Doctor;

public class RescheduleRecommendationList extends AppCompatActivity {

    ArrayList<Doctor> list = new ArrayList<>();
    private ListView simpleList;
    CustomAdapter customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reschedule_recommendation_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        simpleList = (ListView)findViewById(R.id.list_view);

        // list  = list of objects sent by api

        customAdapter = new CustomAdapter(list,getApplicationContext());
        simpleList.setAdapter(customAdapter);

    }

}
