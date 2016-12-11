package org.sealcode.pushtokindle.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import org.sealcode.pushtokindle.R;
import org.sealcode.pushtokindle.api.RetrofitService;
import org.sealcode.pushtokindle.api.Shared;

public class MainActivity extends AppCompatActivity {

    EditText sender, receiver, subject;
    TextInputLayout senderLayout, receiverLayout;
    Button send;

    RetrofitService retrofit;
    Shared shared;

    String from, to, url, title, domain;
    boolean doubleBack;

    static final String SENDER_KEY = "SENDER";
    static final String RECEIVER_KEY = "RECEIVER";
    static final String SUBJECT_KEY = "SUBJECT";
    static final String URL_KEY = "URL";
    static final String BUTTON_KEY = "BUTTON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sender = (EditText) findViewById(R.id.sender);
        receiver = (EditText) findViewById(R.id.receiver);
        subject = (EditText) findViewById(R.id.subject);
        senderLayout = (TextInputLayout) findViewById(R.id.senderLayout);
        receiverLayout = (TextInputLayout) findViewById(R.id.receiverLayout);
        send = (Button) findViewById(R.id.send);

        initObjects();

    }

    @Override
    public void onBackPressed() {
        if(doubleBack) {
            super.onBackPressed();
            return;
        }
        doubleBack = true;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack = false;
            }
        }, 2000);
    }

    private void initObjects() {
        retrofit = new RetrofitService(this);
        shared = Shared.getInstance(this);
        doubleBack = false;
    }

    private void enableButton() {
        send.setAlpha(1f);
        send.setClickable(true);
        send.setEnabled(true);
    }

    private void disableButton() {
        send.setAlpha(.5f);
        send.setClickable(false);
        send.setEnabled(false);
    }

}
