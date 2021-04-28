package com.ghostware.technext.api;

import com.ghostware.technext.models.BlogSuper;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APICall {

    @GET("sohel-cse/simple-blog-api/db")
    Call<BlogSuper> getBlogList();
}
