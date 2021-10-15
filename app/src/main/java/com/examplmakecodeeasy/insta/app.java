package com.examplmakecodeeasy.insta;

import android.app.Application;

import com.parse.Parse;

public class app extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.app_id))
                // if defined
                .clientKey(getString(R.string.client_key))
                .server("https://parseapi.back4app.com/")
                .build()
        );


    }
}
