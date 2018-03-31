package services;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.SyncHttpClient;

import java.util.ArrayList;

import client.RestClient;
import in.project.com.upchaar.MainActivity;
import in.project.com.upchaar.R;
import models.Notification;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by ( Jatin Bansal ) on 26-03-2018.
 */

public class MyService extends IntentService {
    public static final String ACTION = "services.MyService";
    private AsyncHttpClient aClient = new SyncHttpClient();
    String url="";

    public MyService() {
        super("test-service");
    }

    public static final String MyPREFERENCES = "MyPrefs" ;

    SharedPreferences sharedpreferences;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("MyTestService", "Service running");
        UpchaarService libraryServiceAPI = RestClient.getClient();
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        Call<ArrayList<Notification>> listBooksCall = libraryServiceAPI.listnotification();
        final SharedPreferences.Editor editor = sharedpreferences.edit();

        listBooksCall.enqueue(new Callback<ArrayList<Notification>>() {
            @Override
            public void onResponse(Call<ArrayList<Notification>> call, Response<ArrayList<Notification>> response) {
                if (response.isSuccessful()) {
                    int curr_user=sharedpreferences.getInt("current",-1);

                    ArrayList<Notification> book = response.body();
                    // Set response Books as listed layout
                    System.out.println(book);
                    for(int i=0;i<book.size();i++){

                        if(book.get(i).getUser_id()!=curr_user)
                            continue;

                        int flag=sharedpreferences.getInt(String.valueOf(book.get(i).getId()),-1);
                        if(flag==-1){
                            NotificationCompat.Builder builder =
                                    (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.bg_cta_button)
                                            .setContentTitle("Alert")
                                            .setContentText(book.get(i).getMessage());

                            Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                            PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                                    PendingIntent.FLAG_UPDATE_CURRENT);
                            builder.setContentIntent(contentIntent);

                            // Add as notification
                            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                            manager.notify(0, builder.build());

                            editor.putInt(String.valueOf(book.get(i).getId()),1);
                            editor.commit();
                        }
                    }

                } else {
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Notification>> call, Throwable t) {

            }
        });


    }
}
