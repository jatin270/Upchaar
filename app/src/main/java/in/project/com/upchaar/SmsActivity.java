package in.project.com.upchaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class SmsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        String ApiUrl = "YourAuthKey";
        String user = "user";
        String pass = "*****";
        String mobiles = "9999999";
        String sid = "102234";
        String message = "Test message";

        URLConnection myURLConnection=null;
        URL myURL=null;
        BufferedReader reader=null;

        String encoded_message=URLEncoder.encode(message);
        String mainUrl="https://broadnet.me/api/xxxx.php?";
        StringBuilder sbPostData= new StringBuilder(mainUrl);
        sbPostData.append("user="+user);
        sbPostData.append("&pass="+pass);
        sbPostData.append("&mobiles="+mobiles);
        sbPostData.append("&message="+encoded_message);
        sbPostData.append("&sid="+sid);


        mainUrl = sbPostData.toString();
        try
        {
            myURL = new URL(mainUrl);
            myURLConnection = myURL.openConnection();
            myURLConnection.connect();
            reader= new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
            String response;
            while ((response = reader.readLine()) != null)
                Log.d("RESPONSE", ""+response);
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
