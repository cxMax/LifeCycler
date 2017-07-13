package com.cxmax.library;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.cxmax.library.functions.Consumer;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-7-13.
 */

public class LifeCyclerImpl implements ILifeCycler{

    @NonNull private Activity activity;

    private SparseArray<Consumer> onActivityCreated = new SparseArray<>();
    private SparseArray<Consumer> onActivitySaveInstanceState = new SparseArray<>();
    private SparseArray<Consumer> onActivityStarted = new SparseArray<>();
    private SparseArray<Consumer> onActivityResumed = new SparseArray<>();
    private SparseArray<Consumer> onActivityPaused = new SparseArray<>();
    private SparseArray<Consumer> onActivityStopped = new SparseArray<>();
    private SparseArray<Consumer> onActivityDestroyed = new SparseArray<>();

    public LifeCyclerImpl(@NonNull Activity activity) {
        this.activity = activity;
        registerActivityLifecycleCallbacks();
    }

    @NonNull private Application.ActivityLifecycleCallbacks lifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            handleLifeCycleEvent(onActivityCreated , activity , savedInstanceState);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            handleLifeCycleEvent(onActivityStarted , activity , null);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            handleLifeCycleEvent(onActivityResumed , activity , null);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            handleLifeCycleEvent(onActivityPaused , activity , null);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            handleLifeCycleEvent(onActivityStopped , activity , null);
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            handleLifeCycleEvent(onActivitySaveInstanceState , activity , null);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            handleLifeCycleEvent(onActivityDestroyed , activity , null);
        }
    };

    @Override
    public void registerActivityLifecycleCallbacks() {
        activity.getApplication().registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    @Override
    public void unregisterActivityLifecycleCallbacks() {
        activity.getApplication().unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
    }


    @Override
    public void addOnActivityCreatedListener(Consumer consumer) {
        onActivityCreated.put(activity.hashCode(), consumer);
    }

    @Override
    public void addOnActivitySaveInstanceStateListener(Consumer consumer) {
        onActivitySaveInstanceState.put(activity.hashCode(), consumer);
    }

    @Override
    public void addOnActivityResumedListener(Consumer consumer) {
        onActivityResumed.put(activity.hashCode(), consumer);
    }

    @Override
    public void addOnActivityStartedListener(Consumer consumer) {
        onActivityStarted.put(activity.hashCode(), consumer);
    }

    @Override
    public void addOnActivityPausedListener(Consumer consumer) {
        onActivityPaused.put(activity.hashCode(), consumer);
    }

    @Override
    public void addOnActivityStoppedListener(Consumer consumer) {
        onActivityStopped.put(activity.hashCode(), consumer);
    }

    @Override
    public void addOnActivityDestroyedListener(Consumer consumer) {
        onActivityDestroyed.put(activity.hashCode(), consumer);
    }

    @Override
    public void removeOnActivityCreatedListener(Consumer consumer) {
        onActivityCreated.remove(activity.hashCode());
    }

    @Override
    public void removeOnActivitySaveInstanceStateListener(Consumer consumer) {
        onActivitySaveInstanceState.remove(activity.hashCode());
    }

    @Override
    public void removeOnActivityResumedListener(Consumer consumer) {
        onActivityResumed.remove(activity.hashCode());
    }

    @Override
    public void removeOnActivityStartedListener(Consumer consumer) {
        onActivityStarted.remove(activity.hashCode());
    }

    @Override
    public void removeOnActivityPausedListener(Consumer consumer) {
        onActivityPaused.remove(activity.hashCode());
    }

    @Override
    public void removeOnActivityStoppedListener(Consumer consumer) {
        onActivityStopped.remove(activity.hashCode());

    }

    @Override
    public void removeOnActivityDestroyedListener(Consumer consumer) {
        onActivityDestroyed.remove(activity.hashCode());
    }

    private void handleLifeCycleEvent(SparseArray<Consumer> lifecycles, Activity activity, Bundle bundle){
        if (lifecycles.size() <= 0 || activity == null) {
            return;
        }
        Consumer consumer = lifecycles.get(activity.hashCode());
        if (consumer != null) {
            consumer.run(activity , bundle);
        }
    }
}
