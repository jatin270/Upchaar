package in.project.com.upchaar;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class DoctorSignUp extends AppCompatActivity {

    DatePicker DOB ;
    Button setDate;
    EditText DOBtext;
    Spinner spinner_specialization;
    EditText OtherOption;

    String defaultTextForSpinner1 = "Medical Specifications ";
    ArrayList<String> arrayForSpinner = new ArrayList<String>(Arrays.asList("One", "Two", "Three","Other"));

    private class dateOfBirth {
        int date;
        int month;
        int year;
    }
    dateOfBirth dob = new dateOfBirth();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

        DOB = (DatePicker)findViewById(R.id.editText7);
        setDate = (Button)findViewById(R.id.button);
        DOBtext = (EditText)findViewById(R.id.DateOfBirthtext);
        OtherOption = (EditText)findViewById(R.id.OtherOption);

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DOB.setVisibility(View.VISIBLE);
                DOB.setClickable(true);
                DOBtext.setVisibility(View.INVISIBLE);
                setDate.setVisibility(View.INVISIBLE);
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


        spinner_specialization = (Spinner) findViewById(R.id.spinner_specialization);
        final CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, R.layout.spinner_item, arrayForSpinner, defaultTextForSpinner1);
        spinner_specialization.setAdapter(adapter);

        OnItemSelectedListener itemSelectedListener = new OnItemSelectedListener() {
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

    }

    public void collapse(){
        DOB.setVisibility(View.INVISIBLE);
        DOB.setClickable(false);
        DOBtext.setVisibility(View.VISIBLE);
        setDate.setVisibility(View.VISIBLE);
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
}
