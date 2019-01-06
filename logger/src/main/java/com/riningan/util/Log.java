package com.riningan.util;

import android.os.Process;
import android.util.Pair;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;


public class Log {
    private final LoggerConfig mConfig;
    private final Semaphore mSemaphore = new Semaphore(1, true);
    private final LinkedList<Pair<MessageType, String>> mLogQueue = new LinkedList<>();
    private Object mThis = null;


    Log(LoggerConfig config) {
        mConfig = config;
    }


    void forThis(Object object) {
        try {
            mSemaphore.acquire();
        } catch (InterruptedException ignored) {
        }
        mThis = object;
    }


    public void debug() {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod();
            addDebug(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void debug(String message) {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod() + " - " + message;
            addDebug(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void debug(String param, String value) {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
            addDebug(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void debug(String param, boolean value) {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
            addDebug(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void debug(String param, int value) {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
            addDebug(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void debug(String param, long value) {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
            addDebug(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void debug(String param, float value) {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
            addDebug(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void debug(String param, double value) {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod() + "(" + param + " = " + value + ")";
            addDebug(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void debug(String param, String value, String... params) {
        if (mConfig.isEnabled()) {
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
            mThis = null;
            mSemaphore.release();
        }
    }


    public void error(String message) {
        if (mConfig.isEnabled()) {
            int line = Thread.currentThread().getStackTrace()[4].getLineNumber();
            String msg = getClassAndMethod() + "(" + Integer.toString(line) + ") - " + message;
            addError(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void error(Throwable throwable) {
        if (mConfig.isEnabled()) {
            int line = Thread.currentThread().getStackTrace()[4].getLineNumber();
            String msg = getClassAndMethod() + "(" + Integer.toString(line) + ") - " + throwable.getMessage();
            addError(msg);
            mThis = null;
            mSemaphore.release();
        }
    }


    public void info() {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod();
            addInfo(msg);
            mThis = null;
            mSemaphore.release();
        }
    }

    public void info(String message) {
        if (mConfig.isEnabled()) {
            String msg = getClassAndMethod() + " - " + message;
            addInfo(msg);
            mThis = null;
            mSemaphore.release();
        }
    }


    private synchronized void addDebug(String message) {
        String fullMessage = getPrefix() + ": " + message;
        android.util.Log.d(MessageType.DEBUG.name(), fullMessage);
        addToQueue(MessageType.DEBUG, fullMessage);
        mConfig.onNewMessage(MessageType.DEBUG, fullMessage);
    }

    private synchronized void addError(String message) {
        String fullMessage = getPrefix() + ": " + message;
        android.util.Log.e(MessageType.ERROR.name(), fullMessage);
        addToQueue(MessageType.ERROR, fullMessage);
        mConfig.onNewMessage(MessageType.ERROR, fullMessage);
    }

    private synchronized void addInfo(String message) {
        String fullMessage = getPrefix() + ": " + message;
        android.util.Log.i(MessageType.INFO.name(), fullMessage);
        addToQueue(MessageType.INFO, fullMessage);
        mConfig.onNewMessage(MessageType.INFO, fullMessage);
    }


    private String getClassAndMethod() {
        String className;
        String methodName;
        if (mThis == null) {
            className = Thread.currentThread().getStackTrace()[5].getClassName();
            methodName = Thread.currentThread().getStackTrace()[5].getMethodName();
        } else {
            className = mThis.getClass().getName();
            methodName = Thread.currentThread().getStackTrace()[4].getMethodName();
        }
        return className.substring(mConfig.getApplicationIdLength()) + "." + methodName;
    }


    private String getPrefix() {
        return mConfig.getPreffix() + "time(" + mConfig.getDateTime() + "): process(" + Process.myPid() + "): thread(" + Thread.currentThread().getId() + ")";
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