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

public class LoginFragment extends DialogFragment {

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
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
        user=new LoginUser();
        mprogress=new ProgressDialog(getActivity());
        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.login_fragment_layout,container,false);
        username= (EditText) view.findViewById(R.id.login_username);
        password=(EditText)view.findViewById(R.id.login_password);
        login_button= (Button) view.findViewById(R.id.login_button);
        status_textview= (TextView) view.findViewById(R.id.login_status);


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
                System.out.println(response.code());
                if (response.isSuccessful()) {
                    User user1 = response.body();
                    if (user1 != null) {
                        status="Login Successful";
                        mprogress.dismiss();
                        System.out.println(user1);
                        if(user1.getId()=="1") {

                            editor.putString("auth-key",user1.getToken());
                            editor.putInt("role", Integer.parseInt(user1.getId()));
                            editor.commit();
                            Intent intent = new Intent(getActivity(), PatientDash.class);
                            startActivity(intent);
                        }
                        else
                            if(user1.getId()=="2"){

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
