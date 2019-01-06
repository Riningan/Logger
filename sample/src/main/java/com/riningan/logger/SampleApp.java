package com.riningan.logger;

import android.app.Application;
import android.util.Log;

import com.riningan.util.Logger;
import com.riningan.util.MessageType;
import com.riningan.util.OnMessageListener;


public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.Config
                .setEnabled(false)
                .removeApplicationId(BuildConfig.APPLICATION_ID)
                .addPreffix(BuildConfig.VERSION_NAME)
                .setOnMessageListener(new OnMessageListener() {
                    @Override
                    public void onNewMessage(MessageType type, String message) {
//                        Log.d("!@#$%^&*(" + type.name(), message);
                    }
                });
    }
}
