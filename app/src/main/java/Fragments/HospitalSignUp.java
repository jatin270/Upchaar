package Fragments;

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
import in.project.com.upchaar.R;
import models.HospitalUser;
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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
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
                // check if all values are entered and set the values for DoctorUser
                // send DoctorUser
            }
        });

        return view;
    }
}