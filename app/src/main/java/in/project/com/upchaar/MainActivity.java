package in.project.com.upchaar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import Fragments.Agreement;
import Fragments.DoctorSignUp;
import Fragments.Home_screen;
import Fragments.HospitalSignUp;
import Fragments.LoginFragment;
import Fragments.PatientSignUp;
import Fragments.SignupOptions;
import services.MyAlarmReceiver;
import services.MyService;


//https://infinum.co/the-capsized-eight/top-5-android-libraries-every-android-developer-should-know-about

public class MainActivity extends AppCompatActivity {

    private FragmentTransaction trans;
    private FragmentManager manager;

    private View loginFragment;
    private Button button ;
    private View signupOptions;
    private View patientSignUp;
    private View doctorSignUp;
    private View hospitalSignUp;
    private SharedPreferences pref;
    private View agreement;
    private  SharedPreferences.Editor editor;
    private View home_screen;
    private Button bypasser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        bypasser= (Button) findViewById(R.id.bypasser);

        bypasser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,ScheduledDoctor.class);
                startActivity(intent);
                finish();
            }
        });



/*
        String authKey=pref.getString("auth-key","null");
        if(!authKey.equals("null")){
            int id=pref.getInt("role",-1);
            if(id==-1){
                display_login_fragment();
            }
            else{
                if(id==1){
                    Intent intent=new Intent(MainActivity.this,PatientDash.class);
                    startActivity(intent);
                }
                else if(id==2){
                    Intent intent=new Intent(MainActivity.this,DocDashActivity.class);
                    startActivity(intent);
                }else
                    if(id==3){
                }
            }
        }
*/
        manager = getSupportFragmentManager();


        home_screen=findViewById(R.id.home_screen_fragment);
        loginFragment=findViewById(R.id.login_fragment);
        loginFragment.setVisibility(View.INVISIBLE);
        signupOptions=findViewById(R.id.signup_choose_fragment);
        signupOptions.setVisibility(View.INVISIBLE);
        patientSignUp=findViewById(R.id.signup_patient_fragment);
        patientSignUp.setVisibility(View.INVISIBLE);
        doctorSignUp=findViewById(R.id.doctor_signup_fragment);
        doctorSignUp.setVisibility(View.INVISIBLE);
        hospitalSignUp=findViewById(R.id.hospital_signup_fragments);
        hospitalSignUp.setVisibility(View.INVISIBLE);
        agreement=findViewById(R.id.agreement_fragment);
        agreement.setVisibility(View.INVISIBLE);

    }

    public void display_login_fragment(){
        loginFragment.setVisibility(View.VISIBLE);
        home_screen.setVisibility(View.INVISIBLE);
        doctorSignUp.setVisibility(View.INVISIBLE);
        patientSignUp.setVisibility(View.INVISIBLE);
        signupOptions.setVisibility(View.INVISIBLE);
        hospitalSignUp.setVisibility(View.INVISIBLE);
        agreement.setVisibility(View.INVISIBLE);


    }

    public void chose_role_fragment(){
        signupOptions.setVisibility(View.VISIBLE);
        home_screen.setVisibility(View.INVISIBLE);
        patientSignUp.setVisibility(View.INVISIBLE);
        loginFragment.setVisibility(View.INVISIBLE);
        doctorSignUp.setVisibility(View.INVISIBLE);
        hospitalSignUp.setVisibility(View.INVISIBLE);
        agreement.setVisibility(View.INVISIBLE);



    }

    public void display_signup_patient(){

        signupOptions.setVisibility(View.INVISIBLE);
        home_screen.setVisibility(View.INVISIBLE);
        patientSignUp.setVisibility(View.VISIBLE);
        loginFragment.setVisibility(View.INVISIBLE);
        doctorSignUp.setVisibility(View.INVISIBLE);
        hospitalSignUp.setVisibility(View.INVISIBLE);
        agreement.setVisibility(View.INVISIBLE);

    }

    public void display_signup_doctor(){
        signupOptions.setVisibility(View.INVISIBLE);
        home_screen.setVisibility(View.INVISIBLE);
        patientSignUp.setVisibility(View.INVISIBLE);
        loginFragment.setVisibility(View.INVISIBLE);
        doctorSignUp.setVisibility(View.VISIBLE);
        hospitalSignUp.setVisibility(View.INVISIBLE);
        agreement.setVisibility(View.INVISIBLE);


    }

    public void display_signup_hospital(){
        signupOptions.setVisibility(View.INVISIBLE);
        home_screen.setVisibility(View.INVISIBLE);
        patientSignUp.setVisibility(View.INVISIBLE);
        loginFragment.setVisibility(View.INVISIBLE);
        doctorSignUp.setVisibility(View.INVISIBLE);
        hospitalSignUp.setVisibility(View.VISIBLE);
        agreement.setVisibility(View.INVISIBLE);

    }

    public void show_agreement(){
        signupOptions.setVisibility(View.INVISIBLE);
        home_screen.setVisibility(View.INVISIBLE);
        patientSignUp.setVisibility(View.INVISIBLE);
        loginFragment.setVisibility(View.INVISIBLE);
        doctorSignUp.setVisibility(View.INVISIBLE);
        hospitalSignUp.setVisibility(View.INVISIBLE);
        agreement.setVisibility(View.VISIBLE);
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


    // Starts the IntentService
    public void onStartService() {
        Intent i = new Intent(this, MyService.class);
        i.putExtra("foo", "bar");
        startService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(MyService.ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(testReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(testReceiver);
    }

    private BroadcastReceiver testReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("Call returned");
            int resultCode = intent.getIntExtra("resultCode", RESULT_CANCELED);
            if (resultCode == RESULT_OK) {
                String resultValue = intent.getStringExtra("resultValue");
                Toast.makeText(MainActivity.this, resultValue, Toast.LENGTH_SHORT).show();
            }
        }
    };


}