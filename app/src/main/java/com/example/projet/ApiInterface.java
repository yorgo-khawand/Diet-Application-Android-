package com.example.projet;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("subscriptionsInfo.php")
    Call<List<UserInfo>>getUsersInfo();
    @GET("NonSubscriptionsInfo.php")
    Call<List<UserInfo>>getNonSubInfo();
    @GET("viewAppointment.php")
    Call<List<AppointmentModel>>getAppointments();
    @GET("getappointment.php")
    Call<List<UserAppointment>>getAppointmentsUser();
    @GET("getdietplan.php")
    Call<List<UserDietPlan>>getDietPlanUser();

}
