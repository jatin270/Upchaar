package in.project.com.upchaar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Constant.Constant;
import client.RestClient;
import models.LoginUser;
import models.User;
import models.books;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import services.UpchaarService;

//https://infinum.co/the-capsized-eight/top-5-android-libraries-every-android-developer-should-know-about

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpchaarService libraryServiceAPI = RestClient.getClient();
        final TextView textView= (TextView) findViewById(R.id.textView);

        LoginUser user=new LoginUser();
        user.setUsername("jatin11");
        user.setPassword("qwerty@123");

        Call<User> loginRequest = libraryServiceAPI.login(user);

        loginRequest.enqueue(new Callback<User>() {
        // calling doctor sign up
        Intent PlayIntent=new Intent(MainActivity.this,DoctorSignUp.class);
//        Log.d("xyxyxyx","" + x);
//        PlayIntent.putExtra("x", x);
//        PlayIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);




            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    User user1 = response.body();

                    if (user1 != null) {
                        textView.setText("Login Successful");
                    }
                } else {
                    textView.setText("Login Unsuccessful");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                textView.setText("Request Failed");
                t.printStackTrace();
            }

        });
    }
}
