package com.buzz.yora.services;

import android.util.Log;

import com.buzz.yora.infrastructure.YoraApplication;

public class Module {

    private static final String TAG = Module.class.getSimpleName();

    public static void register(YoraApplication application) {
        Log.e(TAG, "IN MEMORY REGISTER METHOD CALLED");
        new InMemoryAccountService(application);
    }
}