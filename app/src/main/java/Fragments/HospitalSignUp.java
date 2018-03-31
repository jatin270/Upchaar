package Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import client.RestClient;
import in.project.com.upchaar.MainActivity;
import in.project.com.upchaar.R;
import models.DoctorUser;
import models.HospitalUser;
import models.Return_Signup_User;
import models.SignUpUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;

public class HospitalSignUp extends Fragment {


    private Context context;
    private UpchaarService libraryServiceAPI = RestClient.getClient();
    private HospitalUser HospitalUserUser;
    EditText Email;
    EditText Password;
    EditText Username;
    EditText Location;
    EditText Beds;
    Button Submit;
    ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_hospital_sign_up, container, false);
        View view =  inflater.inflate(R.layout.fragment_hospital_sign_up, container, false);
        Email = (EditText)view.findViewById(R.id.Email_id);
        Password = (EditText)view.findViewById(R.id.Password);
        Username = (EditText)view.findViewById(R.id.Username);
        Location = (EditText)view.findViewById(R.id.location);
        Submit = (Button)view.findViewById(R.id.Submit);
        Beds = (EditText)view.findViewById(R.id.beds);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.setMessage("Registering....");
                progressDialog.show();
                SignUpUser signUpUser=new SignUpUser();
                signUpUser.setUsername(Username.getText().toString().trim());
                signUpUser.setFirst_name("Hospital_Name");
                signUpUser.setLast_name("User");
                signUpUser.setEmail(Email.getText().toString().trim());
                signUpUser.setRole(3);
                signUpUser.setPassword(Password.getText().toString().trim());
                signUpUser.setGender("M");
                signUpUser.setDate_of_birth("1997-08-16");

                Call<Return_Signup_User> signupCall = libraryServiceAPI.signup(signUpUser);
                signupCall.enqueue(new Callback<Return_Signup_User>() {
                    @Override
                    public void onResponse(Call<Return_Signup_User> call, Response<Return_Signup_User> response) {
                        System.out.println(response.code());
                        if (response.isSuccessful()) {
                            Return_Signup_User muser = response.body();
                            if (muser != null) {
                                System.out.println(muser);
                                progressDialog.dismiss();
                                HospitalUser hospitalUser=new HospitalUser();
                                hospitalUser.setUser(muser.getId());
                                hospitalUser.setNo_of_beds(10);
                                hospitalUser.setHospital_name("Mbbs");
                                hospitalUser.setLatitude(String.valueOf(98.3));
                                hospitalUser.setLongitude(String.valueOf(76.3));

                                Call<HospitalUser> signup_hospital = libraryServiceAPI.signup_hospital(hospitalUser);
                                signup_hospital.enqueue(new Callback<HospitalUser>() {
                                    @Override
                                    public void onResponse(Call<HospitalUser> call, Response<HospitalUser> response) {
                                        System.out.print(response.code());
                                        if (response.isSuccessful()){
                                            HospitalUser user = response.body();
                                            if(user!=null){
                                                MainActivity mainActivity= (MainActivity) getActivity();
                                                mainActivity.show_agreement();
                                            }
                                        }
                                    }
                                    @Override
                                    public void onFailure(Call<HospitalUser> call, Throwable t) {

                                    }
                                });
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<Return_Signup_User> call, Throwable t) {
                        t.printStackTrace();
                    }
                });


            }
        });

        return view;
    }
}