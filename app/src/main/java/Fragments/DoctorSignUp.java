package Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.InputType;
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
import models.DoctorUser;
import services.UpchaarService;


public class DoctorSignUp extends Fragment {

    private Context context;
    private UpchaarService libraryServiceAPI = RestClient.getClient();
    private String status;
    private DoctorUser DoctorUser;
    private EditText username;
    private EditText password;
    private Button login_button;
    private TextView status_textview;
    DatePicker DOB ;
    Button setDate;
    EditText DOBtext;
    Spinner spinner_specialization;
    EditText Email;
    EditText Password;
    TextView Medical_specs;
    EditText Username;
    EditText Address;
    Spinner spinner_qualification;
    Spinner gender;
    Button Submit;
    private String OtherText ="";
    CustomSpinnerAdapter adapter_qual;
    CustomSpinnerAdapter adapter;
    private String OtherText2 ="";

    String defaultTextForSpinner1 = "Medical Specifications ";
    ArrayList<String> arrayForSpinner = new ArrayList<String>(Arrays.asList("One", "Two", "Three","Other"));
    ArrayList<String> QualificationArray = new ArrayList<String>(Arrays.asList("A", "B", "C","Other"));
    ArrayList<String> genderArray = new ArrayList<String>(Arrays.asList("M", "F","Other"));
    String defaultTextForSpinner2 = "Qualification ";
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
        DoctorUser=new DoctorUser();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_doctor_sign_up,container,false);


        DOB = (DatePicker)view.findViewById(R.id.editText7);
        setDate = (Button)view.findViewById(R.id.button);
        DOBtext = (EditText)view.findViewById(R.id.DateOfBirthtext);
        Email = (EditText)view.findViewById(R.id.Email_id);
        Password = (EditText)view.findViewById(R.id.Password);
        Username = (EditText)view.findViewById(R.id.Username);
        Address = (EditText)view.findViewById(R.id.Address);
        Medical_specs = (TextView)view.findViewById(R.id.Medical_specs);
        spinner_specialization = (Spinner) view.findViewById(R.id.spinner_specialization);
        spinner_qualification = (Spinner)view.findViewById(R.id.spinner_qualifications);
        gender = (Spinner)view.findViewById(R.id.spinner_gender);
        Submit = (Button)view.findViewById(R.id.Submit);


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
                spinner_qualification.setVisibility(View.INVISIBLE);
                gender.setVisibility(View.INVISIBLE);
                Address.setVisibility(View.INVISIBLE);
                Submit.setVisibility(View.INVISIBLE);


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
                        DOBtext.setText("" + dob.date + "/" + dob.month + "/" + dob.year);
                        collapse();
                    }
                });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check if all values are entered and set the values for DoctorUser
                // send DoctorUser
            }
        });
        adapter = new CustomSpinnerAdapter(this.getActivity(), R.layout.spinner_item, arrayForSpinner, defaultTextForSpinner1);
        spinner_specialization.setAdapter(adapter);
        AdapterView.OnItemSelectedListener itemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("selected" , + i + "/n" + l);
                String selected_option = arrayForSpinner.get(i);
                if(selected_option.equalsIgnoreCase("Other")) {
                    getOtherText(1);
                    }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner_specialization.setOnItemSelectedListener(itemSelectedListener);

        adapter_qual = new CustomSpinnerAdapter(this.getActivity(), R.layout.spinner_item, QualificationArray, defaultTextForSpinner2);
        spinner_qualification.setAdapter(adapter_qual);
        AdapterView.OnItemSelectedListener itemSelectedListenerQual = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selected_option = QualificationArray.get(i);
                if(selected_option.equalsIgnoreCase("Other")) {
                    getOtherText(2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };
        spinner_qualification.setOnItemSelectedListener(itemSelectedListenerQual);

        final CustomSpinnerAdapter genderAdapter = new CustomSpinnerAdapter(this.getActivity(), R.layout.spinner_item, genderArray, "Gender");
        gender.setAdapter(genderAdapter);

        return view;
    }

    public void getOtherText(final int i){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("Add Specialization");

        final EditText input = new EditText(this.getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(i == 1) {
                    OtherText = input.getText().toString();
                    call1();
                }
                else{
                    OtherText2 = input.getText().toString();
                    call2();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
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
        spinner_qualification.setVisibility(View.VISIBLE);
        gender.setVisibility(View.VISIBLE);
        Address.setVisibility(View.VISIBLE);
        Submit.setVisibility(View.VISIBLE);
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
    public void call1(){
        if(OtherText!=null && !OtherText.isEmpty())
            arrayForSpinner.add(OtherText);
        adapter.notifyDataSetChanged();
    }
    public void call2(){
        if(OtherText2!=null && !OtherText2.isEmpty())
            QualificationArray.add(OtherText2);
        adapter_qual.notifyDataSetChanged();
    }
    @Override
    public void onPause() {
        super.onPause();

    }
}