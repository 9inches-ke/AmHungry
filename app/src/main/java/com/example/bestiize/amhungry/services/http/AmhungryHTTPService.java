package com.example.bestiize.amhungry.services.http;

import android.content.Context;

import com.example.bestiize.amhungry.models.Filter;
import com.example.bestiize.amhungry.models.Restaurant;
import com.example.bestiize.amhungry.services.http.api.AmhungryAPI;

import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Bestiize on 15/11/2558.
 */
public class AmhungryHTTPService {

    public static final String AMHUNGRY_BASE_URL = "http://10.2.35.144:8181";

    private Retrofit retrofit;
    private Context context;
    // private GitAPI gitAPI;
    private AmhungryAPI amhungryAPI;

    public interface OnResponseCallBack<T>{
        void onResponse(boolean success, Throwable error, T data, String errorMessage);
    }

    public AmhungryHTTPService(Context context){
        this.context = context;

        retrofit = new Retrofit.Builder()
                .baseUrl(AMHUNGRY_BASE_URL )
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        this.amhungryAPI = retrofit.create(AmhungryAPI.class);
    }

    public void getRestaurant(Filter filter,  final OnResponseCallBack<List<Restaurant>> callBack){
        Call<List<Restaurant>> callRes = this.amhungryAPI.callRestaurant(filter);
        callRes.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Response<List<Restaurant>> response, Retrofit retrofit) {

                callBack.onResponse(response.isSuccess(),null,response.body(),"");
            }

            @Override
            public void onFailure(Throwable t) {
                callBack.onResponse(false, t, null, "Not found");

            }
        });



    }
    public void getListRestaurant(final OnResponseCallBack<List<Restaurant>> callBack){
        Call<List<Restaurant>> callRes = this.amhungryAPI.listRestaurant();
        callRes.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Response<List<Restaurant>> response, Retrofit retrofit) {

                callBack.onResponse(response.isSuccess(),null,response.body(),"");
            }

            @Override
            public void onFailure(Throwable t) {
                callBack.onResponse(false, t, null, "Not found");

            }
        });



    }
}
