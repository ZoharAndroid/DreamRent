package com.leslie.dream.mxzlw.util;

import android.os.Looper;

/**
 * Created by dmhl on 2017/7/5.
 */

public class ThreadUtil {



    /**
     * Throws an {@link java.lang.IllegalArgumentException} if called on a thread other than the main thread.
     */
    public static void assertMainThread() {
        if (!isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    /**
     * Throws an {@link java.lang.IllegalArgumentException} if called on the main thread.
     */
    public static void assertBackgroundThread() {
        if (!isOnBackgroundThread()) {
            throw new IllegalArgumentException("YOu must call this method on a background thread");
        }
    }

    /**
     * Returns {@code true} if called on the main thread, {@code false} otherwise.
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * Returns {@code true} if called on the main thread, {@code false} otherwise.
     */
    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }


}
