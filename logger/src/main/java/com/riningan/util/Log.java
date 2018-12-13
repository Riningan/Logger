package com.riningan.util;

import android.os.Process;
import android.util.Pair;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;


public class Log {
    private LoggerConfig config;
    private Object mThis = null;
    private final Semaphore mSemaphore = new Semaphore(1, true);
    private final LinkedList<Pair<MessageType, String>> mLogQueue = new LinkedList<>();


    Log(LoggerConfig config) {
        this.config = config;
    }


    public void debug() {
        String msg = getClassAndMethodOfThis();
        addDebug(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void debug(String message) {
        String msg = getClassAndMethodOfThis() + " - " + message;
        addDebug(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void debug(String param, String value) {
        String msg = getClassAndMethodOfThis() + "(" + param + " = " + value + ")";
        addDebug(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void debug(String param, int value) {
        String msg = getClassAndMethodOfThis() + "(" + param + " = " + value + ")";
        addDebug(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void debug(String param, boolean value) {
        String msg = getClassAndMethodOfThis() + "(" + param + " = " + value + ")";
        addDebug(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void debug(String param, long value) {
        String msg = getClassAndMethodOfThis() + "(" + param + " = " + value + ")";
        addDebug(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void debug(String param, double value) {
        String msg = getClassAndMethodOfThis() + "(" + param + " = " + value + ")";
        addDebug(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void debug(String param, float value) {
        String msg = getClassAndMethodOfThis() + "(" + param + " = " + value + ")";
        addDebug(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void debug(String param, String value, String... params) {
        StringBuilder msg = new StringBuilder(getClassAndMethodOfThis() + "(" + param + " = " + value);
        for (int i = 0; i < params.length - 1; i += 2) {
            msg.append(", ").append(params[i]).append(" = ").append(params[i + 1]);
        }
        if (params.length % 2 == 0) {
            msg.append(")");
        } else {
            msg.append(") - ").append(params[params.length - 1]);
        }
        addDebug(msg.toString());
        mThis = null;
        mSemaphore.release();
    }


    public void error(String message) {
        int line = Thread.currentThread().getStackTrace()[4].getLineNumber();
        String msg = getClassAndMethodOfThis() + "(" + Integer.toString(line) + ") - " + message;
        addError(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void error(Throwable throwable) {
        int line = Thread.currentThread().getStackTrace()[4].getLineNumber();
        String msg = getClassAndMethodOfThis() + "(" + Integer.toString(line) + ") - " + throwable.getMessage();
        addError(msg);
        mThis = null;
        mSemaphore.release();
    }


    public void info() {
        String msg = getClassAndMethodOfThis();
        addInfo(msg);
        mThis = null;
        mSemaphore.release();
    }

    public void info(String message) {
        String msg = getClassAndMethodOfThis() + " - " + message;
        addInfo(msg);
        mThis = null;
        mSemaphore.release();
    }


    void forThis(Object object) {
        try {
            mSemaphore.acquire();
        } catch (InterruptedException ignored) {
        }
        mThis = object;
    }


    void debugThread() {
        String msg = getClassAndMethod();
        addDebug(msg);
    }

    void debugThread(String message) {
        String msg = getClassAndMethod() + " - " + message;
        addDebug(msg);
    }

    void debugThread(String param, String value) {
        String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
        addDebug(msg);
    }

    void debugThread(String param, int value) {
        String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
        addDebug(msg);
    }

    void debugThread(String param, boolean value) {
        String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
        addDebug(msg);
    }

    void debugThread(String param, long value) {
        String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
        addDebug(msg);
    }

    void debugThread(String param, double value) {
        String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
        addDebug(msg);
    }

    void debugThread(String param, float value) {
        String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
        addDebug(msg);
    }

    void debugThread(String param, String value, String... params) {
        StringBuilder msg = new StringBuilder(getClassAndMethod() + "(" + param + " = " + value);
        for (int i = 0; i < params.length - 1; i += 2) {
            msg.append(", ").append(params[i]).append(" = ").append(params[i + 1]);
        }
        if (params.length % 2 == 0) {
            msg.append(")");
        } else {
            msg.append(") - ").append(params[params.length - 1]);
        }
        addDebug(msg.toString());
    }


    void errorThread(String message) {
        int line = Thread.currentThread().getStackTrace()[5].getLineNumber();
        String msg = getClassAndMethod() + "(" + Integer.toString(line) + ") - " + message;
        addError(msg);
    }

    void errorThread(Throwable throwable) {
        int line = Thread.currentThread().getStackTrace()[5].getLineNumber();
        String msg = getClassAndMethod() + "(" + Integer.toString(line) + ") - " + throwable.getMessage();
        addError(msg);
    }


    void infoThread() {
        String msg = getClassAndMethod();
        addInfo(msg);
    }

    void infoThread(String message) {
        String msg = getClassAndMethod() + " - " + message;
        addInfo(msg);
    }


    private synchronized void addDebug(String message) {
        String fullMessage = getPrefix() + ": " + message;
        android.util.Log.d(MessageType.DEBUG.name(), fullMessage);
        addToQueue(MessageType.DEBUG, fullMessage);
        config.onNewMessage(MessageType.DEBUG, fullMessage);
    }

    private synchronized void addError(String message) {
        String fullMessage = getPrefix() + ": " + message;
        android.util.Log.e(MessageType.ERROR.name(), fullMessage);
        addToQueue(MessageType.ERROR, fullMessage);
        config.onNewMessage(MessageType.ERROR, fullMessage);
    }

    private synchronized void addInfo(String message) {
        String fullMessage = getPrefix() + ": " + message;
        android.util.Log.i(MessageType.INFO.name(), fullMessage);
        addToQueue(MessageType.INFO, fullMessage);
        config.onNewMessage(MessageType.INFO, fullMessage);
    }


    private String getClassAndMethod() {
        String className = Thread.currentThread().getStackTrace()[5].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[5].getMethodName();
        return className.substring(config.getApplicationIdLength()) + "." + methodName;
    }

    private String getClassAndMethodOfThis() {
        String className = mThis.getClass().getName();
        String methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
        return className.substring(config.getApplicationIdLength()) + "." + methodName;
    }

    private String getPrefix() {
        return config.getPreffix() + "time(" + config.getDateTime() + "): process(" + Process.myPid() + "): thread(" + Thread.currentThread().getId() + ")";
    }

    private void addToQueue(MessageType type, String msg) {
        synchronized (mLogQueue) {
            if (mLogQueue.size() > 100) {
                mLogQueue.removeFirst();
            }
            mLogQueue.add(new Pair<>(type, msg));
        }
    }
}
