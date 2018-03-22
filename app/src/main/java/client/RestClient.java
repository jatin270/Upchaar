package client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import Constant.Constant;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;
import services.UpchaarService;

/**
 * Created by ( Jatin Bansal ) on 20-03-2018.
 */

public class RestClient {


    private static UpchaarService upchaarService;

    public static UpchaarService getClient() {
        if (upchaarService == null) {

            Gson gson = new GsonBuilder()
                    .setDateFormat(Constant.DATE_FORMAT)
                    .create();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.interceptors().add(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build()).build();

            upchaarService = retrofit.create(UpchaarService.class);
        }
        return upchaarService;
    }

}
