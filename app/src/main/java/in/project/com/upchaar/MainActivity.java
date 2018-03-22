package in.project.com.upchaar;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Constant.Constant;
import Fragments.*;
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

    private FragmentTransaction trans;
    private FragmentManager manager;
    private LoginFragment loginFragment;
    private Fragments.DoctorSignUp DoctorSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=getSupportFragmentManager();
        trans=manager.beginTransaction();
        loginFragment= (LoginFragment)manager.findFragmentById(R.id.login_fragment);
        trans.hide(loginFragment);
        trans.commit();
        DoctorSignUp  = (Fragments.DoctorSignUp)manager.findFragmentById(R.id.doctorSignUp);
    }
}
