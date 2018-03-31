package in.project.com.upchaar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import client.RestClient;
import models.Notification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

public class Notification_list extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    private ListView lv;
    List<String> your_array_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list);
        UpchaarService libraryServiceAPI = RestClient.getClient();
        Call<ArrayList<Notification>> listBooksCall = libraryServiceAPI.listnotification();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        lv = (ListView) findViewById(R.id.notifications_list);
        your_array_list= new ArrayList<String>();

        listBooksCall.enqueue(new Callback<ArrayList<Notification>>() {
            @Override
            public void onResponse(Call<ArrayList<Notification>> call, Response<ArrayList<Notification>> response) {
                if (response.isSuccessful()) {
                    int curr_user=sharedpreferences.getInt("current",-1);

                    ArrayList<Notification> book = response.body();

                    System.out.println(book);

                    for(int i=0;i<book.size();i++){

                        if(book.get(i).getUser_id()!=curr_user)
                            continue;
                        your_array_list.add(book.get(i).getMessage());
                    }

                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(Notification_list.this, android.R.layout.simple_list_item_1, your_array_list );
                    lv.setAdapter(arrayAdapter);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Notification>> call, Throwable t) {

            }
        });


    }
}
