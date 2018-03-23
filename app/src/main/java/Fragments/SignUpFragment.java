package Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import client.RestClient;
import in.project.com.upchaar.R;
import models.SignUpUser;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

/**
 * Created by ( Jatin Bansal ) on 22-03-2018.
 */

public class SignUpFragment extends Fragment {


    private Button patient_button;
    private Button doctor_button;
    private SignUpUser user;
    private UpchaarService libraryServiceAPI = RestClient.getClient();
    private TextView status_textview;
    private String status;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.signup_fragment_common,container,false);

        patient_button= (Button) view.findViewById(R.id.patient_role);
        doctor_button= (Button) view.findViewById(R.id.doctor_role);
        status_textview= (TextView) view.findViewById(R.id.signup_status);

        patient_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        doctor_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    public void signup(){
        user.setUsername("jatin");
        user.setEmail("jatin@gmail.com");
        user.setFirst_name("Jatin");
        user.setLast_name("Bansal");
        user.setPassword("password");
        user.setGender("M");
        user.setDate_of_birth("16-08-1997");
        user.setRole(2);
        user.setAddress("");

        Call<SignUpUser> signupRequest = libraryServiceAPI.signup(user);
        signupRequest.enqueue(new Callback<SignUpUser>() {
            @Override
            public void onResponse(Call<SignUpUser> call, Response<SignUpUser> response) {
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    SignUpUser user1 = response.body();
                    if (user1 != null) {
                        status="Login Successful";
                        status_textview.setText(status);

                    }
                } else {
                    status="Login Unsuccessful";
                    status_textview.setText(status);

                }
            }
            @Override
            public void onFailure(Call<SignUpUser> call, Throwable t) {
                status="Request Failed";
                status_textview.setText(status);
                t.printStackTrace();
            }
        });

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
