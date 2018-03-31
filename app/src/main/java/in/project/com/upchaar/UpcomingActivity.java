package in.project.com.upchaar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import java.util.ArrayList;

import Fragments.Doctor;
import Fragments.Upcoming;

/**
 * Created by raghav on 31/3/18.
 */

public class UpcomingActivity extends AppCompatActivity {

    ArrayList<Upcoming> list = new ArrayList<>();
    private ListView simpleList;
    CustomAdapterUpcoming customAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming_appointment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        simpleList = (ListView)findViewById(R.id.list_view);

        // list  = list of objects sent by api


        Upcoming upcoming = new Upcoming("Fortis", "10:00", "1-4-18", "27");
        list.add(upcoming);
        Upcoming upcoming2 = new Upcoming("Max", "14:00", "1-4-18", "15");
        list.add(upcoming2);

        customAdapter = new CustomAdapterUpcoming(list,getApplicationContext());
        simpleList.setAdapter(customAdapter);
    }
}
