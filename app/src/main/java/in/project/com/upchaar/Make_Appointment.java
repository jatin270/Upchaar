package in.project.com.upchaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class Make_Appointment extends AppCompatActivity {

    private Button from_button;
    private Button to_button;
    private EditText from_edittext;
    private EditText to_edittext;
    private DatePicker from;
    private DatePicker to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make__appointment);
        from_button= (Button) findViewById(R.id.from);
        to_button= (Button) findViewById(R.id.to);
        from_edittext= (EditText) findViewById(R.id.from_text);
        to_edittext= (EditText) findViewById(R.id.to_text);
        from= (DatePicker) findViewById(R.id.from_datepicker);
        to= (DatePicker) findViewById(R.id.to_datepicker);

        from_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                from.setVisibility(View.VISIBLE);
                from.setClickable(true);

            }
        });

        to_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                to.setVisibility(View.VISIBLE);
                to.setClickable(true);
            }
        });

    }
}
