package com.cxmax.library;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.cxmax.library.functions.Consumer;

/**
 * describe :
 * usage :
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
            handleLifeCycleEventThenRemoveIt(onActivityCreated , activity , savedInstanceState);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityStarted , activity , null);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityResumed , activity , null);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityPaused , activity , null);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityStopped , activity , null);
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            handleLifeCycleEventThenRemoveIt(onActivitySaveInstanceState , activity , null);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityDestroyed , activity , null);
        }
    };

    /**
     * you needn't register manually, it will register automatically
     * @see LifeCyclerImpl Construct
     */
    @Override
    public void registerActivityLifecycleCallbacks() {
        activity.getApplication().registerActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    /**
     * unregister {@link Application.ActivityLifecycleCallbacks}
     * and {@link LifeCycler} can not monitor all Activities' lifecycle event in current application
     */
    @Override
    public void unregisterActivityLifecycleCallbacks() {
        activity.getApplication().unregisterActivityLifecycleCallbacks(lifecycleCallbacks);
    }

    /**
     * clear all activity call back listeners
     */
    @Override
    public void clear() {
        onActivityCreated.clear();
        onActivitySaveInstanceState.clear();
        onActivityStarted.clear();
        onActivityResumed.clear();
        onActivityPaused.clear();
        onActivityStopped.clear();
        onActivityDestroyed.clear();
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

    private void handleLifeCycleEventThenRemoveIt(SparseArray<Consumer> lifecycles, Activity activity, Bundle bundle) {
        if (activity == null || lifecycles == null || lifecycles.size() <= 0) {
            return;
        }
        Consumer consumer = lifecycles.get(activity.hashCode());
        if (consumer != null) {
            consumer.run(activity, bundle);
            lifecycles.remove(activity.hashCode());
        }
    }
}
