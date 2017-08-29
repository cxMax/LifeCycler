package com.cxmax.library;

import android.app.Activity;
import android.app.Application;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import java.util.concurrent.ConcurrentHashMap;

/**
 * describe :
 * usage :
 * Created by caixi on 17-7-13.
 */

class LifeCyclerRetriever {

    private static SparseArray<ILifeCycler> sAppIndex;

    static {
        sAppIndex = new SparseArray<>();
    }

    private static class SingletonHolder {
        private final static LifeCyclerRetriever instance = new LifeCyclerRetriever();
    }

    static LifeCyclerRetriever getInstance() {
        return SingletonHolder.instance;
    }

    synchronized ILifeCycler get(@NonNull Activity activity) {
        return getLifeCyclerByApplication(activity);
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
