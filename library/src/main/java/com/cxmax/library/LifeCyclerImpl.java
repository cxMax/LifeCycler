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
    public ILifeCycler addOnActivityCreatedListener(Consumer consumer) {
        onActivityCreated.put(activity.hashCode(), consumer);
        return this;
    }

    @Override
    public ILifeCycler addOnActivitySaveInstanceStateListener(Consumer consumer) {
        onActivitySaveInstanceState.put(activity.hashCode(), consumer);
        return this;
    }

    @Override
    public ILifeCycler addOnActivityResumedListener(Consumer consumer) {
        onActivityResumed.put(activity.hashCode(), consumer);
        return this;
    }

    @Override
    public ILifeCycler addOnActivityStartedListener(Consumer consumer) {
        onActivityStarted.put(activity.hashCode(), consumer);
        return this;
    }

    @Override
    public ILifeCycler addOnActivityPausedListener(Consumer consumer) {
        onActivityPaused.put(activity.hashCode(), consumer);
        return this;
    }

    @Override
    public ILifeCycler addOnActivityStoppedListener(Consumer consumer) {
        onActivityStopped.put(activity.hashCode(), consumer);
        return this;
    }

    @Override
    public ILifeCycler addOnActivityDestroyedListener(Consumer consumer) {
        onActivityDestroyed.put(activity.hashCode(), consumer);
        return this;
    }

    @Override
    public ILifeCycler removeOnActivityCreatedListener(Activity activity) {
        onActivityCreated.remove(activity.hashCode());
        return this;
    }

    @Override
    public ILifeCycler removeOnActivitySaveInstanceStateListener(Activity activity) {
        onActivitySaveInstanceState.remove(activity.hashCode());
        return this;
    }

    @Override
    public ILifeCycler removeOnActivityResumedListener(Activity activity) {
        onActivityResumed.remove(activity.hashCode());
        return this;
    }

    @Override
    public ILifeCycler removeOnActivityStartedListener(Activity activity) {
        onActivityStarted.remove(activity.hashCode());
        return this;
    }

    @Override
    public ILifeCycler removeOnActivityPausedListener(Activity activity) {
        onActivityPaused.remove(activity.hashCode());
        return this;
    }

    @Override
    public ILifeCycler removeOnActivityStoppedListener(Activity activity) {
        onActivityStopped.remove(activity.hashCode());
        return this;

    }

    @Override
    public ILifeCycler removeOnActivityDestroyedListener(Activity activity) {
        onActivityDestroyed.remove(activity.hashCode());
        return this;
    }

    private void handleLifeCycleEvent(SparseArray<Consumer> lifecycles, Activity activity, Bundle bundle) {
        if (lifecycles.size() <= 0 || activity == null) {
            return;
        }
        Consumer consumer = lifecycles.get(activity.hashCode());
        if (consumer != null) {
            consumer.run(activity, bundle);
        }
    }
}
