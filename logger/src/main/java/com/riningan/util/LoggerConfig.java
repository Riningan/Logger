package com.riningan.util;

import android.annotation.SuppressLint;

import java.text.SimpleDateFormat;
import java.util.Date;


public class LoggerConfig {
    private int mApplicationIdLength = 0;
    private String mPreffix = "";
    @SuppressLint("SimpleDateFormat")
    private SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd: HH:mm:ss");
    private OnMessageListener mListener = null;


    LoggerConfig() {
    }


    @SuppressLint("SimpleDateFormat")
    public LoggerConfig setDateFormat(String format) {
        dt = new SimpleDateFormat(format);
        return this;
    }

    public LoggerConfig removeApplicationId(String applicationId) {
        mApplicationIdLength = applicationId.length() + 1;
        return this;
    }

    public LoggerConfig addPreffix(String preffix) {
        mPreffix = preffix + ": ";
        return this;
    }

    public LoggerConfig setOnMessageListener(OnMessageListener listener) {
        mListener = listener;
        return this;
    }


    String getDateTime() {
        return dt.format(new Date());
    }

    int getApplicationIdLength() {
        return mApplicationIdLength;
    }

    String getPreffix() {
        return mPreffix;
    }

    void onNewMessage(MessageType type, String message) {
        if (mListener != null) {
            mListener.onNewMessage(type, message);
        }
    }
}