package com.example.bestiize.amhungry.services.http.api;

import com.example.bestiize.amhungry.models.Filter;
import com.example.bestiize.amhungry.models.Restaurant;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by Bestiize on 15/11/2558.
 */
public interface AmhungryAPI {
    @POST("/api/restaurant/rule/")
    @Headers(
            "Content-type: application/json"
    )
    Call<List<Restaurant>> callRestaurant(@Body Filter filter);

    @GET("/api/restaurant/all/")
    Call<List<Restaurant>> listRestaurant();

}
