package in.project.com.upchaar;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import Fragments.DoctorSignUp;
import Fragments.PatientSignUp;


//https://infinum.co/the-capsized-eight/top-5-android-libraries-every-android-developer-should-know-about

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction trans;
    private FragmentManager manager;
    private DoctorSignUp doctorSignUp;
    private PatientSignUp PatientSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager=getSupportFragmentManager();
 //       doctorSignUp  = (DoctorSignUp)manager.findFragmentById(R.id.doctorSignUp);
        PatientSignUp = (Fragments.PatientSignUp)manager.findFragmentById(R.id.patientSignUp);

    }
}
