package com.mereexams.mereexamscalendar.Helpers;

import com.mereexams.mereexamscalendar.Models.ExamDate;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by Bilal on 30-Jun-17.
 */

public interface ApiInterface {

    @GET
    Call<ExamDate.ExamDatesResponse> getExamDates(
            @Url String url,
            @Header("token") String token);

}
