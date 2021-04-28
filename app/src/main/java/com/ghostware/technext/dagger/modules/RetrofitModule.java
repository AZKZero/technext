package com.ghostware.technext.dagger.modules;

import com.ghostware.technext.api.APICall;
import com.google.gson.Gson;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    String BASE_URL = "https://my-json-server.typicode.com/";


    public RetrofitModule() {
    }

    @Provides
    @Singleton
    public Retrofit retrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .callbackExecutor(Executors.newSingleThreadExecutor())
                .build();
    }

    @Provides
    @Singleton
    public APICall apiCall(Retrofit retrofit) {
        return retrofit.create(APICall.class);
    }
}
