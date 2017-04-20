package com.news.http;


import com.news.model.LocalNews;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.Call;

public interface LocalNewsApi {

    @GET("news/local/{city}")
    Call<LocalNews> getLocalNewsForCity(@Path("city") String city);

}
