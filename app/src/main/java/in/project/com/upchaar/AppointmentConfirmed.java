package in.project.com.upchaar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.NotificationCompat;
import android.widget.TextView;
import client.RestClient;
import models.MessageTemplate;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

public class AppointmentConfirmed extends AppCompatActivity {

    Intent intent;
    String date;
    int token;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_confirmed);
        intent=getIntent();
        textView= (TextView) findViewById(R.id.displaytext);
        date=intent.getStringExtra("date");
        token=intent.getIntExtra("token_no",1);
        textView.setText("Your Appointment confirmed for "+date+".Your token no-"+token);

        MessageTemplate messageTemplate=new MessageTemplate();
        messageTemplate.setMessage("Checking");
        messageTemplate.setPhone("919211277070");


        UpchaarService libraryServiceAPI = RestClient.getClient();

        Call<MessageTemplate> addBookCall = libraryServiceAPI.sendmessageandnotification(messageTemplate);
        addBookCall.enqueue(new Callback<MessageTemplate>() {
            @Override
            public void onResponse(Call<MessageTemplate> call, Response<MessageTemplate> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    MessageTemplate added = response.body();
                    if (added != null) {


                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<MessageTemplate> call, Throwable t) {
                t.printStackTrace();
            }
        });


        NotificationCompat.Builder builder =
                (NotificationCompat.Builder) new NotificationCompat.Builder(getApplicationContext())
                        .setSmallIcon(R.drawable.bg_cta_button)
                        .setContentTitle("Appointment")
                        .setContentText("Your Appointment has been confirmed.");

        Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());


    }
}
