package in.project.com.upchaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Constant.Constant;
import client.RestClient;
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

        final TextView textView= (TextView) findViewById(R.id.sample);
        UpchaarService libraryServiceAPI = RestClient.getClient();


        Call<ArrayList<books>> listBooksCall = libraryServiceAPI.listBooks();
        listBooksCall.enqueue(new Callback<ArrayList<books>>() {
            @Override
            public void onResponse(Call<ArrayList<books>> call, Response<ArrayList<books>> response) {
                if (response.isSuccessful()) {
                    ArrayList<books> book = response.body();
                    // Set response Books as listed layout
                    textView.setText(book.toString());
                } else {
                    Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ArrayList<books>> call, Throwable t) {

                Toast.makeText(getBaseContext(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
