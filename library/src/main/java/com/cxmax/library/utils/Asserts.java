package com.cxmax.library.utils;

import android.os.Looper;

import java.util.Collection;
import java.util.Map;

/**
 * describe :
 * usage :
 * Created by caixi on 17-7-13.
 */

public class Asserts {

    public static <T> T requireNonNull(T object) {
        return requireNonNull(object, " sth was null in : " + Asserts.class.getName());
    }

    public static <T> boolean isEmpty(T t) {
        if (t instanceof Map) {
            return ((Map) t).isEmpty();
        } else if (t instanceof Collection) {
            return ((Collection) t).isEmpty();
        }
        return isNullable(t);
    }

    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    private static boolean isNullable(Object o) {
        return o == null;
    }

    private static <T> T requireNonNull(T o, String message) {
        if (o == null) {
            throw new NullPointerException(message);
        }
        return o;
    }


}
