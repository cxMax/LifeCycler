package com.cxmax.library.utils;

import android.os.Looper;

/**
 * describe :
 * usage :
 * Created by caixi on 17-7-13.
 */

public class Asserts {
    public static <T> T requireNonNull(T o, String message) {
        if (o == null) {
            throw new NullPointerException(message);
        }
        return o;
    }

    public static <T> T requireNonNull(T object) {
        return requireNonNull(object, " sth was null in : " + Asserts.class.getName());
    }

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}
