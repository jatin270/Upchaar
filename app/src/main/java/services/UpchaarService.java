package services;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.LoginUser;
import models.SignUpUser;
import models.User;
import models.books;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by ( Jatin Bansal ) on 19-03-2018.
 */

public interface UpchaarService {

    //Get Request-------------------------------------------------------------------------------------------------------------------------
    @GET("books")
    Call<ArrayList<books>> listBooks();


    //Post Request-------------------------------------------------------------------------------------------------------------------------
    @POST("login/")
    Call<User> login(@Body LoginUser user);

    @POST("users/")
    Call<SignUpUser> signup(@Body SignUpUser user);



    //Get Request with search id-------------------------------------------------------------------------------------------------------------------------
    @GET("books/{id}/")
    Call<books> getBookInfo(@Path("id") int bookId);



    //Delete Request-------------------------------------------------------------------------------------------------------------------------
    @DELETE("books/{id}/")
    Call<Void> deleteBook(@Path("id") int bookId);


    //Update Request-------------------------------------------------------------------------------------------------------------------------
    @PUT("books/{id}/")
    Call<books> updateBook(@Path("id") String bookId, @Body books book);



    //Complete Delete Request-------------------------------------------------------------------------------------------------------------------------
    @DELETE("clean/")
    Call<Void> deleteAll();

}

