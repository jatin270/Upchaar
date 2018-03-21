package in.project.com.upchaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class DoctorSignUp extends AppCompatActivity {

    DatePicker DOB ;
    Button setDate;
    EditText DOBtext;

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

    }

    public void collapse(){
        DOB.setVisibility(View.INVISIBLE);
        DOB.setClickable(false);
        DOBtext.setVisibility(View.VISIBLE);
        setDate.setVisibility(View.VISIBLE);
    }
}
