package org.sealcode.pushtokindle.api;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maciej on 11.12.2016.
 */

public class RetrofitService {

    private static final String BASE_URL = "http://fivefilters.org/kindle-it/";
    private static final String UNABLE_RETRIEVE = "Unable to retrieve content";
    private static final String UNABLE_RESOLVE = "Unable to resolve host \"fivefilters.org\"";

    private static final String ACTION_SEND = "send";
    private static final String ACTION_CHECK = "iframe";
    private static final String SAVE_FILE = "yes";

    private Rest service;
    private Context context;
    private Shared shared;
    private Toast toast;

    public RetrofitService(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(Rest.class);
        shared = Shared.getInstance(context);
        toast =  Toast.makeText(context, "", Toast.LENGTH_LONG);
        this.context = context;
    }
}
