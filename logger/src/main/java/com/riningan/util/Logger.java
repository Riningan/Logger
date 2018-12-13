package com.riningan.util;


public class Logger {
    public static final LoggerConfig Config = new LoggerConfig();
    private static final Log log = new Log(Config);


    private Logger() {
    }


    public static synchronized Log forThis(Object object) {
        log.forThis(object);
        return log;
    }


    public static synchronized void debug() {
        log.debugThread();
    }

    public static synchronized void debug(String message) {
        log.debugThread(message);
    }

    public static synchronized void debug(String param, String value) {
        log.debugThread(param, value);
    }

    public static synchronized void debug(String param, int value) {
        log.debugThread(param, value);
    }

    public static synchronized void debug(String param, boolean value) {
        log.debugThread(param, value);
    }

    public static synchronized void debug(String param, long value) {
        log.debugThread(param, value);
    }

    public static synchronized void debug(String param, double value) {
        log.debugThread(param, value);
    }

    public static synchronized void debug(String param, float value) {
        log.debugThread(param, value);
    }

    public static synchronized void debug(String param, String value, String... params) {
        log.debugThread(param, value, params);
    }


    public static synchronized void error(String message) {
        log.errorThread(message);
    }

    public static synchronized void error(Throwable throwable) {
        log.errorThread(throwable);
    }


    public static synchronized void info() {
        log.infoThread();
    }

    public static synchronized void info(String message) {
        log.infoThread(message);
    }
}
