package org.sealcode.pushtokindle.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import org.sealcode.pushtokindle.R;
import org.sealcode.pushtokindle.api.Shared;

public class MainActivity extends AppCompatActivity {

    EditText sender, receiver, subject;
    TextInputLayout senderLayout, receiverLayout;
    Button send;

    Shared shared;
    
    String from, to, url, title, domain;
    boolean doubleBack;

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

}
