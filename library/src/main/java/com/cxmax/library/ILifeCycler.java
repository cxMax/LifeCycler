package com.cxmax.library;

import android.app.Activity;

import com.cxmax.library.functions.Consumer;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-7-13.
 */

public interface ILifeCycler {

    void registerActivityLifecycleCallbacks();

    void unregisterActivityLifecycleCallbacks();

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
