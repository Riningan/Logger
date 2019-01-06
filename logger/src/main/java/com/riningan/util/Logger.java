package com.riningan.util;


public class Logger {
    public static final LoggerConfig Config = new LoggerConfig();
    private static final Log mLog = new Log(Config);


    private Logger() {
    }


    public static synchronized Log forThis(Object object) {
        mLog.forThis(object);
        return mLog;
    }


    public static synchronized void debug() {
        forThis(null).debug();
    }

    public static synchronized void debug(String message) {
        forThis(null).debug(message);
    }

    public static synchronized void debug(String param, String value) {
        forThis(null).debug(param, value);
    }

    public static synchronized void debug(String param, boolean value) {
        forThis(null).debug(param, value);
    }

    public static synchronized void debug(String param, int value) {
        forThis(null).debug(param, value);
    }

    public static synchronized void debug(String param, long value) {
        forThis(null).debug(param, value);
    }

    public static synchronized void debug(String param, float value) {
        forThis(null).debug(param, value);
    }

    public static synchronized void debug(String param, double value) {
        forThis(null).debug(param, value);
    }

    public static synchronized void debug(String param, String value, String... params) {
        forThis(null).debug(param, value, params);
    }


    public static synchronized void error(String message) {
        forThis(null).error(message);
    }

    public static synchronized void error(Throwable throwable) {
        forThis(null).error(throwable);
    }


    public static synchronized void info() {
        forThis(null).info();
    }

    public static synchronized void info(String message) {
        forThis(null).info(message);
    }
}