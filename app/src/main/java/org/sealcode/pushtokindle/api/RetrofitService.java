package org.sealcode.pushtokindle.api;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Button;
import android.widget.Toast;

import org.sealcode.pushtokindle.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

    public void sendArticle(String url, final String from, final String to, String title, String domain, String receiverId) {
        Call call = service.sendWebsite(ACTION_SEND, url, from, title, receiverId, domain, SAVE_FILE);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = (ResponseBody) response.body();
                    String body = responseBody.string();
                    CharSequence unable =  UNABLE_RETRIEVE;
                    if(body.contains(unable)) showToast(R.string.cant_send);
                    else {
                        showToast(R.string.sent);
                        shared.writeSender(from);
                        shared.writeReceiver(to);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                enableButton();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                if(t.getMessage().contains(UNABLE_RESOLVE)) showToast(R.string.turn_internet);
                else showToast(context.getString(R.string.couldnt_send) + t.getMessage());
                enableButton();
            }
        });
    }

    public void checkArticle(String url) {
        final ProgressDialog progressDialog;
        progressDialog = ProgressDialog.show(context, context.getString(R.string.checking), context.getString(R.string.progress), true);
        Call call = service.checkConversion(ACTION_CHECK, url);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                try {
                    ResponseBody responseBody = (ResponseBody) response.body();
                    String body = responseBody.string();
                    CharSequence unable =  UNABLE_RETRIEVE;
                    if(body.contains(unable)) showToast(R.string.cant_send);
                    else enableButton();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
            @Override
            public void onFailure(Call call, Throwable t) {
                if(t.getMessage().contains(UNABLE_RESOLVE)) showToast(R.string.turn_internet);
                else showToast(context.getString(R.string.couldnt_send) + t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    public void showToast(int id) {
        String text = context.getString(id);
        toast.setText(text);
        toast.show();
    }

    public void showToast(String text) {
        toast.setText(text);
        toast.show();
    }

    private void enableButton() {
        Button send = (Button) ((Activity)context).findViewById(R.id.send);
        send.setAlpha(1f);
        send.setClickable(true);
        send.setEnabled(true);
    }
}
