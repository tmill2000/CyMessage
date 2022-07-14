package com.example.loginscreen.api;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlimCallback<T> implements Callback<T> {

    LambdaInterface<T> lambdaInterface;
    String logTag;

    public SlimCallback(LambdaInterface<T> lambdaInterface, String customTag) {
        this.lambdaInterface = lambdaInterface;
        this.logTag = customTag;
    }

    public SlimCallback(LambdaInterface<T> lambdaInterface){
        this.lambdaInterface = lambdaInterface;

    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        lambdaInterface.doSomething(response.body());
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Log.d(logTag, "Thrown: " + t.getMessage());
    }
}
