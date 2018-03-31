package Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import client.RestClient;
import in.project.com.upchaar.PatientDash;
import in.project.com.upchaar.R;
import models.LoginUser;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

/**
 * Created by ( Jatin Bansal ) on 22-03-2018.
 */

public class LoginFragment extends Fragment {

    private Context context;
    private UpchaarService libraryServiceAPI = RestClient.getClient();
    private String status;
    private LoginUser user;
    private EditText username;
    private EditText password;
    private Button login_button;
    private TextView status_textview;
    private ProgressDialog mprogress;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        user=new LoginUser();
        mprogress=new ProgressDialog(getActivity());
        pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.login_fragment_layout,container,false);
        username= view.findViewById(R.id.login_username);
        password=view.findViewById(R.id.login_password);
        login_button=  view.findViewById(R.id.login_button);
        status_textview= view.findViewById(R.id.login_status);


        if(savedInstanceState!=null){
            username.setText(user.getUsername());
            password.setText(user.getPassword());
        }

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.setUsername(username.getText().toString().trim());
                user.setPassword(password.getText().toString().trim());
                login();
            }
        });

        return view;
    }

    public void login(){
        mprogress.setMessage("Logging........");
        mprogress.show();

        Call<User> loginRequest = libraryServiceAPI.login(user);
        loginRequest.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                mprogress.dismiss();
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    User user1 = response.body();
                    if (user1 != null) {
                        status="Login Successful";
                        System.out.println(user1);
                        Intent intent = new Intent(getActivity(), PatientDash.class);
                        startActivity(intent);
                        if(user1.getId()==1) {
                            intent = new Intent(getActivity(), PatientDash.class);
                            startActivity(intent);
                            editor.putString("auth-key",user1.getToken());
                            editor.putInt("current", user1.getId());
                            editor.commit();
                        }
                        else
                            if(user1.getId()==2){

                            }
                        status_textview.setText(status);

                    }
                } else {
                    status="Login Unsuccessful";
                    status_textview.setText(status);
                }
            }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                status="Request Failed";
                status_textview.setText(status);
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        String username_local = username.getText().toString().trim();
        String password_local = password.getText().toString().trim();


        if (username_local != null) {
            user.setUsername(username_local);
        } else {
            user.setUsername("");
        }

        if (password_local != null) {
            user.setPassword(password_local);
        } else {
            user.setPassword("");
        }
    }
}
