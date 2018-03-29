package in.project.com.upchaar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import Fragments.DoctorSignUp;
import Fragments.Home_screen;
import Fragments.LoginFragment;
import Fragments.PatientSignUp;
import Fragments.SignupOptions;
import services.MyAlarmReceiver;
import services.MyService;


//https://infinum.co/the-capsized-eight/top-5-android-libraries-every-android-developer-should-know-about

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction trans;
    private FragmentManager manager;
    private DoctorSignUp doctorSignUp;
    private PatientSignUp PatientSignUp;
    private Home_screen home_screen;
    private LoginFragment loginFragment;
    private SignupOptions signupOptions;
    private PatientSignUp patientSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getSupportFragmentManager();
        loginFragment=new LoginFragment();
        signupOptions=new SignupOptions();
        patientSignUp=new PatientSignUp();

    }

    public void display_login_fragment(){
        loginFragment.show(manager,"login");
    }

    public void chose_role_fragment(){

        signupOptions.show(manager,"roles");

    }

    public void display_signup_patient(){
        trans=manager.beginTransaction();
        trans.remove(signupOptions);
        trans.commit();
        patientSignUp.show(manager,"Patient_Signup");

    }


    // Setup a recurring alarm every half hour
    public void scheduleAlarm() {
        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        // Setup periodic alarm every every half hour from this point onwards
        long firstMillis = System.currentTimeMillis(); // alarm is set right away

        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                1000, pIntent);

    }

    public void cancelAlarm() {
        Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this, MyAlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.cancel(pIntent);
    }


}



//    // Starts the IntentService
//    public void onStartService() {
//        Intent i = new Intent(this, MyService.class);
//        i.putExtra("foo", "bar");
//        startService(i);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        // Register for the particular broadcast based on ACTION string
//        IntentFilter filter = new IntentFilter(MyService.ACTION);
//        LocalBroadcastManager.getInstance(this).registerReceiver(testReceiver, filter);
//        // or `registerReceiver(testReceiver, filter)` for a normal broadcast
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        // Unregister the listener when the application is paused
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(testReceiver);
//        // or `unregisterReceiver(testReceiver)` for a normal broadcast
//    }
//
//    // Define the callback for what to do when data is received
//    private BroadcastReceiver testReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            System.out.println("Call returned");
//            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
//            if (resultCode == RESULT_OK) {
//                String resultValue = intent.getStringExtra("resultValue");
//                Toast.makeText(MainActivity.this, resultValue, Toast.LENGTH_SHORT).show();
//            }
//        }
//    };

