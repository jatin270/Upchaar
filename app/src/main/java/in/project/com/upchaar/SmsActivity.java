package in.project.com.upchaar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.sms.SmsSubmissionResult;
import com.nexmo.client.sms.messages.TextMessage;
import java.io.IOException;

public class SmsActivity extends AppCompatActivity {

    String NEXMO_API_KEY ="e43bbd7d" ;
    String NEXMO_API_SECRET ="w7mD1e7IoJCNOu3U" ;
    String phoneno="9211277070";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        AuthMethod auth = new TokenAuthMethod(NEXMO_API_KEY, NEXMO_API_SECRET);
        NexmoClient client = new NexmoClient(auth);

        SmsSubmissionResult[] responses = new SmsSubmissionResult[0];
        try {
            responses = client.getSmsClient().submitMessage(new TextMessage(
                    "Acme Inc",
                    phoneno,
                    "A text message sent using the Nexmo SMS API"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NexmoClientException e) {
            e.printStackTrace();
        }
        for (SmsSubmissionResult response : responses) {

            System.out.println(response);
        }
    }
}
