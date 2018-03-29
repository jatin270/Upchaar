package services;

import android.app.Activity;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.SyncHttpClient;

import cz.msebera.android.httpclient.Header;
import in.project.com.upchaar.R;


/**
 * Created by ( Jatin Bansal ) on 26-03-2018.
 */

public class MyService extends IntentService {
    public static final String ACTION = "services.MyService";
    private AsyncHttpClient aClient = new SyncHttpClient();
    String url="";

    public MyService() {
        super("test-service");
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("MyTestService", "Service running");
        aClient.get(this, url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(MyService.this);

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.androidauthority.com/"));
                PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, 0);
                mBuilder.setContentIntent(pendingIntent);
                mBuilder.setSmallIcon(R.drawable.bg_cta_button);
                mBuilder.setContentTitle("Upchaar");
                mBuilder.setContentText("Hello World!");
                NotificationManager mNotificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(001, mBuilder.build());
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {



            }
        });
    }
}
