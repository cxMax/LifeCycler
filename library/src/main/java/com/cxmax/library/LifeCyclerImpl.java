package com.cxmax.library;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.SparseArray;

import com.cxmax.library.functions.Consumer;
import com.cxmax.library.utils.Asserts;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * describe :
 * usage :
 * Created by caixi on 17-7-13.
 */

public class LifeCyclerImpl implements ILifeCycler {

    @NonNull private Activity activity;

    private final byte[] createdLock;
    private volatile ConcurrentHashMap<Integer, LinkedList<Consumer>> onActivityCreated = new ConcurrentHashMap<>();
    private final byte[] saveInstanceStateLock;
    private volatile ConcurrentHashMap<Integer, LinkedList<Consumer>> onActivitySaveInstanceState = new ConcurrentHashMap<>();
    private final byte[] startedLock;
    private volatile ConcurrentHashMap<Integer, LinkedList<Consumer>> onActivityStarted = new ConcurrentHashMap<>();
    private final byte[] resumedLock;
    private volatile ConcurrentHashMap<Integer, LinkedList<Consumer>> onActivityResumed = new ConcurrentHashMap<>();
    private final byte[] pausedLock;
    private volatile ConcurrentHashMap<Integer, LinkedList<Consumer>> onActivityPaused = new ConcurrentHashMap<>();
    private final byte[] stoppedLock;
    private volatile ConcurrentHashMap<Integer, LinkedList<Consumer>> onActivityStopped = new ConcurrentHashMap<>();
    private final byte[] destroyedLock;
    private volatile ConcurrentHashMap<Integer, LinkedList<Consumer>> onActivityDestroyed = new ConcurrentHashMap<>();

    private final Lock handleLock;

    {
        createdLock = new byte[0];
        saveInstanceStateLock = new byte[0];
        startedLock = new byte[0];
        resumedLock = new byte[0];
        pausedLock = new byte[0];
        stoppedLock = new byte[0];
        destroyedLock = new byte[0];
        handleLock = new ReentrantLock();
    }

    public LifeCyclerImpl(@NonNull Activity activity) {
        this.activity = activity;
        registerActivityLifecycleCallbacks();
    }

    @NonNull
    private Application.ActivityLifecycleCallbacks lifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            handleLifeCycleEventThenRemoveIt(onActivityCreated, activity, savedInstanceState);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityStarted, activity, null);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityResumed, activity, null);
        }

        @Override
        public void onActivityPaused(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityPaused, activity, null);
        }

        @Override
        public void onActivityStopped(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityStopped, activity, null);
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            handleLifeCycleEventThenRemoveIt(onActivitySaveInstanceState, activity, null);
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            handleLifeCycleEventThenRemoveIt(onActivityDestroyed, activity, null);
        }
    };

    /**
     * you needn't register manually, it will register automatically
     *
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
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public ILifeCycler addOnActivityCreatedListener(Consumer consumer) {
        synchronized (createdLock) {
            if (!onActivityCreated.containsKey(activity.hashCode())) {
                LinkedList<Consumer> consumers = new LinkedList<>();
                onActivityCreated.put(activity.hashCode(), consumers);
            }
            LinkedList<Consumer> container = onActivityCreated.get(activity.hashCode());
            container.add(consumer);
            return this;
        }
    }

    @Override
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public ILifeCycler addOnActivitySaveInstanceStateListener(Consumer consumer) {
        synchronized (saveInstanceStateLock) {
            if (!onActivitySaveInstanceState.containsKey(activity.hashCode())) {
                LinkedList<Consumer> consumers = new LinkedList<>();
                onActivitySaveInstanceState.put(activity.hashCode(), consumers);
            }
            LinkedList<Consumer> container = onActivitySaveInstanceState.get(activity.hashCode());
            container.add(consumer);
            return this;
        }
    }

    @Override
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public ILifeCycler addOnActivityStartedListener(Consumer consumer) {
        synchronized (startedLock) {
            if (!onActivityStarted.containsKey(activity.hashCode())) {
                LinkedList<Consumer> consumers = new LinkedList<>();
                onActivityStarted.put(activity.hashCode(), consumers);
            }
            LinkedList<Consumer> container = onActivityStarted.get(activity.hashCode());
            container.add(consumer);
            return this;
        }
    }

    @Override
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public ILifeCycler addOnActivityResumedListener(Consumer consumer) {
        synchronized (resumedLock) {
            if (!onActivityResumed.containsKey(activity.hashCode())) {
                LinkedList<Consumer> consumers = new LinkedList<>();
                onActivityResumed.put(activity.hashCode(), consumers);
            }
            LinkedList<Consumer> container = onActivityResumed.get(activity.hashCode());
            container.add(consumer);
            return this;
        }
    }


    @Override
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public ILifeCycler addOnActivityPausedListener(Consumer consumer) {
        synchronized (pausedLock) {
            if (!onActivityPaused.containsKey(activity.hashCode())) {
                LinkedList<Consumer> consumers = new LinkedList<>();
                onActivityPaused.put(activity.hashCode(), consumers);
            }
            LinkedList<Consumer> container = onActivityPaused.get(activity.hashCode());
            container.add(consumer);
            return this;
        }
    }

    @Override
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public ILifeCycler addOnActivityStoppedListener(Consumer consumer) {
        synchronized (stoppedLock) {
            if (!onActivityStopped.containsKey(activity.hashCode())) {
                LinkedList<Consumer> consumers = new LinkedList<>();
                onActivityStopped.put(activity.hashCode(), consumers);
            }
            LinkedList<Consumer> container = onActivityStopped.get(activity.hashCode());
            container.add(consumer);
            return this;
        }
    }

    @Override
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    public ILifeCycler addOnActivityDestroyedListener(Consumer consumer) {
        synchronized (destroyedLock) {
            if (!onActivityDestroyed.containsKey(activity.hashCode())) {
                LinkedList<Consumer> consumers = new LinkedList<>();
                onActivityDestroyed.put(activity.hashCode(), consumers);
            }
            LinkedList<Consumer> container = onActivityDestroyed.get(activity.hashCode());
            container.add(consumer);
            return this;
        }
    }

    @Override
    public ILifeCycler removeOnActivityCreatedListener(Activity activity) {
        synchronized (createdLock) {
            onActivityCreated.remove(activity.hashCode());
            return this;
        }
    }

    @Override
    public ILifeCycler removeOnActivitySaveInstanceStateListener(Activity activity) {
        synchronized (saveInstanceStateLock) {
            onActivitySaveInstanceState.remove(activity.hashCode());
            return this;
        }
    }

    @Override
    public ILifeCycler removeOnActivityResumedListener(Activity activity) {
        synchronized (resumedLock) {
            onActivityResumed.remove(activity.hashCode());
            return this;
        }
    }

    @Override
    public ILifeCycler removeOnActivityStartedListener(Activity activity) {
        synchronized (startedLock) {
            onActivityStarted.remove(activity.hashCode());
            return this;
        }
    }

    @Override
    public ILifeCycler removeOnActivityPausedListener(Activity activity) {
        synchronized (pausedLock) {
            onActivityPaused.remove(activity.hashCode());
            return this;
        }
    }

    @Override
    public ILifeCycler removeOnActivityStoppedListener(Activity activity) {
        synchronized (stoppedLock) {
            onActivityStopped.remove(activity.hashCode());
        }
        return this;

    }

    @Override
    public ILifeCycler removeOnActivityDestroyedListener(Activity activity) {
        synchronized (destroyedLock) {
            onActivityDestroyed.remove(activity.hashCode());
            return this;
        }
    }

    private void handleLifeCycleEventThenRemoveIt(ConcurrentHashMap<Integer, LinkedList<Consumer>> lifeCycles, Activity activity, Bundle bundle) {
        if (handleLock.tryLock()) {
            try {
                if (activity == null || lifeCycles == null || lifeCycles.size() <= 0) {
                    return;
                }
                LinkedList<Consumer> consumers = lifeCycles.get(activity.hashCode());
                if (!Asserts.isEmpty(consumers)) {
                    for (Consumer consumer : consumers) {
                        if (consumer != null) {
                            consumer.run(activity, bundle);
                        }
                    }
                }
                lifeCycles.remove(activity.hashCode());
            } finally {
                handleLock.unlock();
            }
        }
    }
}
