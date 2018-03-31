package services;

import java.util.ArrayList;

import models.AppointmentModel;
import models.DaySchedule;
import models.DoctorUser;
import models.HospitalUser;
import models.LoginUser;
import models.MessageTemplate;
import models.PatientUser;
import models.Return_Patient_User;
import models.Return_Signup_User;
import models.SignUpUser;
import models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by ( Jatin Bansal ) on 19-03-2018.
 */

public interface UpchaarService {

    //Get Request-------------------------------------------------------------------------------------------------------------------------
//    @GET("books")
//    Call<ArrayList<books>> listBooks();

    @GET("dayschedule/")
    Call<ArrayList<DaySchedule>> listschedules();

    @GET("appointment/")
    Call<ArrayList<AppointmentModel>> listappointment();

    @GET("doctors/")
    Call<ArrayList<DoctorUser>> listdoctor();

    @GET("hospitals/")
    Call<ArrayList<HospitalUser>> listhospital();

    @GET("users/")
    Call<ArrayList<User>> listuser();

    @GET("notifications")
    Call<ArrayList<models.Notification>> listnotification();


//
//    @GET("notifications/")
//    Call<ArrayList<Notification>>


    //Post Request-------------------------------------------------------------------------------------------------------------------------
    @POST("login/")
    Call<User> login(@Body LoginUser user);

    @POST("users/")
    Call<Return_Signup_User> signup(@Body SignUpUser signUpUser);

    @POST("patients/")
    Call<Return_Patient_User> signup_patient(@Body PatientUser patientUser);

    @POST("doctors/")
    Call<DoctorUser> signup_doctor(@Body DoctorUser doctorUser);

    @POST("hospitals/")
    Call<HospitalUser> signup_hospital(@Body HospitalUser hospitalUser);

    @POST("appointment/")
    Call<AppointmentModel> register_appoint(@Body AppointmentModel appointmentModel);

    @POST("send-sms/")
    Call<MessageTemplate> sendmessageandnotification(@Body MessageTemplate messageTemplate);



    //Get Request with search id-------------------------------------------------------------------------------------------------------------------------
//    @GET("books/{id}/")
//    Call<books> getBookInfo(@Path("id") int bookId);
//


    //Delete Request-------------------------------------------------------------------------------------------------------------------------
    @DELETE("books/{id}/")
    Call<Void> deleteBook(@Path("id") int bookId);


    //Update Request-------------------------------------------------------------------------------------------------------------------------
//    @PUT("books/{id}/")
//    Call<books> updateBook(@Path("id") String bookId, @Body books book);
//
//

    //Complete Delete Request-------------------------------------------------------------------------------------------------------------------------
    @DELETE("clean/")
    Call<Void> deleteAll();

}

