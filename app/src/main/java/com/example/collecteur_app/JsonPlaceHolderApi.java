package com.example.collecteur_app;

import retrofit2.Call;

import com.example.collecteur_app.models.Activites;
import com.example.collecteur_app.models.Agent;
import com.example.collecteur_app.models.Contribuables;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import java.util.List;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @POST("loginagents")
    Call<Agent> getidentifiant(@Body Agent agent);
    @FormUrlEncoded
    @POST("loginagents")
    Call<Agent> postidentifiant(
            @Field("agLogin") String login,
            @Field("agLogin") String password
    );

    @POST("tccontribuables")
    Call<Contribuables> sendcontribuable(@Body Contribuables contrib);

    @GET("tactivites")
    Call<List<Activites>> obtactivite();

    @GET("tactivites/liste/{nameactivite}")
    Call<Activites> obtIdACtivite(@Path("nameactivite") String nameactivite);
}
