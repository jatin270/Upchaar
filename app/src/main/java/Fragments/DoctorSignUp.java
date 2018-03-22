package Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import client.RestClient;
import in.project.com.upchaar.R;
import models.LoginUser;
import models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.UpchaarService;


public class DoctorSignUp extends Fragment {

    private Context context;
    private UpchaarService libraryServiceAPI = RestClient.getClient();
    private String status;
    private User user;
    private EditText username;
    private EditText password;
    private Button login_button;
    private TextView status_textview;
    DatePicker DOB ;
    Button setDate;
    EditText DOBtext;
    Spinner spinner_specialization;
    EditText OtherOption;
    EditText Email;
    EditText Password;
    TextView Medical_specs;
    EditText Username;

    String defaultTextForSpinner1 = "Medical Specifications ";
    ArrayList<String> arrayForSpinner = new ArrayList<String>(Arrays.asList("One", "Two", "Three","Other"));

    private class dateOfBirth {
        int date;
        int month;
        int year;
    }
    dateOfBirth dob = new dateOfBirth();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //user=new User();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_doctor_sign_up,container,false);

//        if(savedInstanceState!=null){
//            username.setText(user.getUsername());
//            password.setText(user.getPassword());
//        }
//
//        login_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user.setUsername(username.getText().toString().trim());
//                user.setPassword(password.getText().toString().trim());
//                login();
//            }
//        });

        DOB = (DatePicker)view.findViewById(R.id.editText7);
        setDate = (Button)view.findViewById(R.id.button);
        DOBtext = (EditText)view.findViewById(R.id.DateOfBirthtext);
        OtherOption = (EditText)view.findViewById(R.id.OtherOption);
        Email = (EditText)view.findViewById(R.id.Email_id);
        Password = (EditText)view.findViewById(R.id.Password);
        Username = (EditText)view.findViewById(R.id.Username);
        Medical_specs = (TextView)view.findViewById(R.id.Medical_specs);
        spinner_specialization = (Spinner) view.findViewById(R.id.spinner_specialization);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DOB.setVisibility(View.VISIBLE);
                DOB.setClickable(true);
                DOBtext.setVisibility(View.INVISIBLE);
                setDate.setVisibility(View.INVISIBLE);
                Email.setVisibility(View.INVISIBLE);
                Password.setVisibility(View.INVISIBLE);
                Username.setVisibility(View.INVISIBLE);
                Medical_specs.setVisibility(View.INVISIBLE);
                spinner_specialization.setVisibility(View.INVISIBLE);

            }
        });

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        DOB.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int month, int dayOfMonth) {
                        dob.date = dayOfMonth;
                        dob.month = month + 1;
                        dob.year = year;
//                Log.d("Date","" + dob.date + dob.month+dob.year);
                        DOBtext.setText("" + dob.date + "/" + dob.month + "/" + dob.year);
                        collapse();
                    }
                });



        final CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this.getActivity(), R.layout.spinner_item, arrayForSpinner, defaultTextForSpinner1);
        spinner_specialization.setAdapter(adapter);

        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("selected" , + i + "/n" + l);
                String selected_option = arrayForSpinner.get(i);
                if(selected_option.equalsIgnoreCase("Other")) {

                    OtherOption.setVisibility(View.VISIBLE);
                    String text = (String)OtherOption.getText().toString();
                    arrayForSpinner.add(text);
                    adapter.notifyDataSetChanged();
                    removeVisibility();
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner_specialization.setOnItemSelectedListener(itemSelectedListener);


        return view;
    }

    public void collapse(){
        DOB.setVisibility(View.INVISIBLE);
        DOB.setClickable(false);
        DOBtext.setVisibility(View.VISIBLE);
        setDate.setVisibility(View.VISIBLE);
        Email.setVisibility(View.VISIBLE);
        Password.setVisibility(View.VISIBLE);
        Medical_specs.setVisibility(View.VISIBLE);
        Username.setVisibility(View.VISIBLE);
        spinner_specialization.setVisibility(View.VISIBLE);
    }

    public void removeVisibility(){
        OtherOption.setVisibility(View.INVISIBLE);
    }

    public class CustomSpinnerAdapter extends ArrayAdapter<String> {

        Context context;
        ArrayList<String> objects;
        String firstElement;
        boolean isFirstTime;

        public CustomSpinnerAdapter(Context context, int textViewResourceId, ArrayList<String> objects, String defaultText) {
            super(context, textViewResourceId, objects);
            this.context = context;
            this.objects = objects;
            this.isFirstTime = true;
            setDefaultText(defaultText);

        }

        @Override
        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            if(isFirstTime) {
                objects.set(0,firstElement);
                isFirstTime = false;
            }
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            notifyDataSetChanged();
            return getCustomView(position, convertView, parent);
        }

        public void setDefaultText(String defaultText) {
            this.firstElement = objects.get(0);
            objects.set(0,defaultText);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.spinner_item, parent, false);
            TextView label = (TextView) row.findViewById(R.id.spinner_item);
            label.setText(objects.get(position));

            return row;
        }

    }

//    public void login(){
//        Call<User> loginRequest = libraryServiceAPI.login(user);
//        loginRequest.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(Call<User> call, Response<User> response) {
//                System.out.println(response.code());
//                if (response.isSuccessful()) {
//                    User user1 = response.body();
//                    if (user1 != null) {
//                        status="Login Successful";
//                        status_textview.setText(status);
//
//                    }
//                } else {
//                    status="Login Unsuccessful";
//                    status_textview.setText(status);
//
//                }
//            }
//            @Override
//            public void onFailure(Call<User> call, Throwable t) {
//                status="Request Failed";
//                status_textview.setText(status);
//                t.printStackTrace();
//            }
//        });
//    }

    @Override
    public void onPause() {
        super.onPause();

    }
}