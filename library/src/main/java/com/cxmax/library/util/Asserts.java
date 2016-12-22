package com.cxmax.library.util;

import android.app.Activity;
import android.app.Application;

import com.cxmax.library.func.Func1;
import com.cxmax.library.func.Func2;

/**
 * @Author CaiXi on  2016/12/22 23:45.
 * @Github: https://github.com/cxMax
 * @Description
 */

public class Asserts {

    public static <T> T requireNonNull(T o, String message){
        if (o == null) {
            throw new NullPointerException(message);
        }
        return o;
    }

    public static <T> T requireNonNull(T object){
        return requireNonNull(object , "Object cannot be null");
    }

    public static Activity requireNonNull(Activity activity) {
        return requireNonNull(activity , "activity cannot be null" );
    }

    public static Application requireNonNull(Application application){
        return requireNonNull(application , "application cannot be null" );
    }

    public static Func1 requireNonNull(Func1 callbacks) {
        return requireNonNull(callbacks , "Cannot bind null listener");
    }

    public static Func2 requireNonNull(Func2 callbacks) {
        return requireNonNull(callbacks , "Cannot bind null listener");
    }

    public static Activity assertNotFinished(Activity activity){
        requireNonNull(activity);
        if (activity.isFinishing() || activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot bind to a destroyed activity");
        }
        return activity;
    }
}
