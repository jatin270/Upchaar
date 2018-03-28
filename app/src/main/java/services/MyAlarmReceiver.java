package services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by ( Jatin Bansal ) on 26-03-2018.
 */

public class MyAlarmReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "in.project.com.upchaar";


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, MyService.class);
        i.putExtra("foo", "bar");
        context.startService(i);
    }
}
