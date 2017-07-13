package com.cxmax.library;

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

    void addOnActivityCreatedListener(Consumer consumer);

    void addOnActivitySaveInstanceStateListener(Consumer consumer);

    void addOnActivityResumedListener(Consumer consumer);

    void addOnActivityStartedListener(Consumer consumer);

    void addOnActivityPausedListener(Consumer consumer);

    void addOnActivityStoppedListener(Consumer consumer);

    void addOnActivityDestroyedListener(Consumer consumer);

    void removeOnActivityCreatedListener(Consumer consumer);

    void removeOnActivitySaveInstanceStateListener(Consumer consumer);

    void removeOnActivityResumedListener(Consumer consumer);

    void removeOnActivityStartedListener(Consumer consumer);

    void removeOnActivityPausedListener(Consumer consumer);

    void removeOnActivityStoppedListener(Consumer consumer);

    void removeOnActivityDestroyedListener(Consumer consumer);
}
