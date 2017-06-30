package com.mereexams.mereexamscalendar.Helpers;

import com.mereexams.mereexamscalendar.MainActivity;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bilal on 30-Jun-17.
 */

public class ApiClient {
    public static final String BASE_URL = MainActivity.vars.get("base_url"); // Your base URL
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
