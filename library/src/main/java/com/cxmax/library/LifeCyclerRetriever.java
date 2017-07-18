package com.cxmax.library;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.cxmax.library.utils.Asserts;

/**
 * describe :
 * usage :
 * Created by caixi on 17-7-13.
 */

public class LifeCyclerRetriever {

    private static final SparseArray<ILifeCycler> sAppIndex;

    static {
        sAppIndex = new SparseArray<>();
    }

    private static class SingletonHolder {
        public final static LifeCyclerRetriever instance = new LifeCyclerRetriever();
    }

    public static LifeCyclerRetriever getInstance() {
        return SingletonHolder.instance;
    }

    public ILifeCycler get(@Nullable Activity activity) {
        checkAllParamsIsReady(activity);
        return getLifeCyclerByApplication(activity);
    }

    private void checkAllParamsIsReady(@Nullable Activity activity) {
        Asserts.requireNonNull(activity);
        if (!Asserts.isOnMainThread()) {
            throw new IllegalArgumentException("You should register activity lifecycle callbacks in UI thread");
        }
    }

    private ILifeCycler getLifeCyclerByApplication(@NonNull Activity activity) {
        Application application = activity.getApplication();
        ILifeCycler instance = findLifeCyclerByApplication(application);
        if (instance == null) {
            instance = new LifeCyclerImpl(activity);
            sAppIndex.put(application.hashCode() , instance);
        }
        return instance;
    }

    private static ILifeCycler findLifeCyclerByApplication(@NonNull Application app){
        return sAppIndex.get(app.hashCode());
    }

}
