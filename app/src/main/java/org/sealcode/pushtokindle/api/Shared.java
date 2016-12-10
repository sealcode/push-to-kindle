package org.sealcode.pushtokindle.api;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Maciej on 10.12.2016.
 */

public class Shared {

    //SHARED DATA AND SINGLETON---------------------------------------------------------------------
    private Context context;
    private static Shared singleton = null;

    //SHARED SINGLETON CONSTRUCTOR------------------------------------------------------------------
    private Shared(Context context) {
        this.context = context;
    }

    //GETTING SINGLETON-----------------------------------------------------------------------------
    public static Shared getInstance(Context context) {
        if (singleton == null) singleton = new Shared(context);
        return singleton;
    }

    public void writeSender(String sender) {
        SharedPreferences sharedPref = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("sender", sender);
        editor.commit();
    }

    public String readSender() {
        SharedPreferences sharedPref = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString("sender", "none");
    }

    public void writeReceiver(String receiver) {
        SharedPreferences sharedPref = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("receiver", receiver);
        editor.commit();
    }

    public String readReceiver() {
        SharedPreferences sharedPref = ((Activity) context).getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString("receiver", "none");
    }
}
