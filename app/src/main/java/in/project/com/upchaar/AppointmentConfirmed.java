package in.project.com.upchaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

    }
}
