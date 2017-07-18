package com.cxmax.library;

import android.app.Activity;
import android.app.Application;

import com.cxmax.library.functions.Consumer;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-7-13.
 */

public interface ILifeCycler {

    /**
     * you needn't register manually, it will register automatically
     * @see LifeCyclerImpl Construct
     */
    void registerActivityLifecycleCallbacks();

    /**
     * unregister {@link Application.ActivityLifecycleCallbacks}
     * and {@link LifeCycler} can not monitor all Activities' lifecycle event in current application
     */
    void unregisterActivityLifecycleCallbacks();

    /**
     * clear all activity call back listeners
     */
    void clear();

    ILifeCycler addOnActivityCreatedListener(Consumer consumer);

    ILifeCycler addOnActivitySaveInstanceStateListener(Consumer consumer);

    ILifeCycler addOnActivityResumedListener(Consumer consumer);

    ILifeCycler addOnActivityStartedListener(Consumer consumer);

    ILifeCycler addOnActivityPausedListener(Consumer consumer);

    ILifeCycler addOnActivityStoppedListener(Consumer consumer);

    ILifeCycler addOnActivityDestroyedListener(Consumer consumer);

    ILifeCycler removeOnActivityCreatedListener(Activity activity);

    ILifeCycler removeOnActivitySaveInstanceStateListener(Activity activity);

    ILifeCycler removeOnActivityResumedListener(Activity activity);

    ILifeCycler removeOnActivityStartedListener(Activity activity);

    ILifeCycler removeOnActivityPausedListener(Activity activity);

    ILifeCycler removeOnActivityStoppedListener(Activity activity);

    ILifeCycler removeOnActivityDestroyedListener(Activity activity);
}
