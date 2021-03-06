package com.riningan.logger;

import android.app.Application;

import com.riningan.util.Logger;
import com.riningan.util.MessageType;
import com.riningan.util.OnMessageListener;


public class SampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Logger.Config
                .removeApplicationId(BuildConfig.APPLICATION_ID)
                .addPreffix(BuildConfig.VERSION_NAME)
                .setOnMessageListener(new OnMessageListener() {
                    @Override
                    public void onNewMessage(MessageType type, String message) {
                    }
                });
    }
}