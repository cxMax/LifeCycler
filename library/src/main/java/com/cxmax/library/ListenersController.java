package com.cxmax.library;

import android.app.Activity;
import android.os.Bundle;

import com.cxmax.library.func.Func1;
import com.cxmax.library.func.Func2;
import com.cxmax.library.util.Util;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author CaiXi on  2016/12/23 00:08.
 * @Github: https://github.com/cxMax
 * @Description store object which hooks activity events , and add lifecycle listener
 */

public class ListenersController {
    private int activityId;

    private List<ListenAll> onAll;

    private List<Func1> onActivityStarted;
    private List<Func1> onActivityResumed;
    private List<Func1> onActivityPaused;
    private List<Func1> onActivityStopped;
    private List<Func1> onActivityDestroyed;

    private List<Func2> onActivitySaveInstanceState;
    private List<Func2> onActivityCreated;

    ListenersController(int activityHashCode) {
        this.activityId = activityHashCode;
    }

    public int getActivityId(){
        return activityId;
    }

    /**onActivityStarted**/
    public void triggerOnActivityStarted(Activity activity) {
        if (!Util.isEmpty(onAll) ) {
            for (ListenAll callback : onAll) {
                callback.onActivityStarted(activity);
            }
        }

        if (!Util.isEmpty(onActivityStarted)) {
            for (Func1 func1 : onActivityStarted) {
                func1.run(activity);
            }
        }
    }

    /**onActivityResumed**/
    public  void triggerOnActivityResumed(Activity activity) {
        if (!Util.isEmpty(onAll) ) {
            for (ListenAll callback : onAll) {
                callback.onActivityResumed(activity);
            }
        }

        if (!Util.isEmpty(onActivityResumed)) {
            for (Func1 func1 : onActivityResumed) {
                func1.run(activity);
            }
        }
    }

    /**onActivityPaused**/
    public void triggerOnActivityPaused(Activity activity) {
        if (!Util.isEmpty(onAll) ) {
            for (ListenAll callback : onAll) {
                callback.onActivityPaused(activity);
            }
        }

        if (!Util.isEmpty(onActivityPaused)) {
            for (Func1 func1 : onActivityPaused) {
                func1.run(activity);
            }
        }
    }

    /**onActivityStopped**/
    public void triggerOnActivityStopped(Activity activity) {
        if (!Util.isEmpty(onAll) ) {
            for (ListenAll callback : onAll) {
                callback.onActivityStopped(activity);
            }
        }

        if (!Util.isEmpty(onActivityStopped)) {
            for (Func1 func1 : onActivityStopped) {
                func1.run(activity);
            }
        }
    }

    /**onActivityDestroyed**/
    public void triggerOnActivityDestroyed(Activity activity) {
        if (!Util.isEmpty(onAll) ) {
            for (ListenAll callback : onAll) {
                callback.onActivityDestroyed(activity);
            }
        }

        if (!Util.isEmpty(onActivityDestroyed)) {
            for (Func1 func1 : onActivityDestroyed) {
                func1.run(activity);
            }
        }
    }

    /**onActivityCreated**/
    public void triggerOnActivityCreated(Activity activity, Bundle bundle){
        if (!Util.isEmpty(onAll)) {
            for (ListenAll callback : onAll) {
                callback.onActivityCreated(activity , bundle);
            }
        }

        if (!Util.isEmpty(onActivityCreated)) {
            for (Func2 callback :onActivityCreated){
                callback.run(activity , bundle);
            }
        }
    }

    /**onActivitySaveInstanceState**/
    public void triggerOnActivitySaveInstanceState(Activity activity, Bundle bundle) {
        if (!Util.isEmpty(onAll) ) {
            for (ListenAll callback : onAll) {
                callback.onActivitySaveInstanceState(activity, bundle);
            }
        }

        if (!Util.isEmpty(onActivitySaveInstanceState)) {
            for (Func2 func2 : onActivitySaveInstanceState) {
                func2.run(activity, bundle);
            }
        }
    }

    /**-------------builder------------------**/
    public ListenersController addOnActivityCreatedListener(Func2 func2) {
        if (onActivityCreated == null) {
            onActivityCreated = new LinkedList<>();
        }
        onActivityCreated.add(func2);
        return this;
    }

    public ListenersController removeOnActivityCreatedListener(Func2 func2) {
        if (!Util.isEmpty(onActivityCreated) ) {
            onActivityCreated.remove(func2);
        }
        return this;
    }

    public ListenersController addOnActivityStartedListener(Func1 func1) {
        if (onActivityStarted == null) {
            onActivityStarted = new LinkedList<>();
        }
        onActivityStarted.add(func1);
        return this;
    }

    public ListenersController removeOnActivityStartedListener(Func1 func1) {
        if (!Util.isEmpty(onActivityStarted) ) {
            onActivityStarted.remove(func1);
        }
        return this;
    }

    public ListenersController addOnActivityResumedListener(Func1 func1) {
        if (onActivityResumed == null) {
            onActivityResumed = new LinkedList<>();
        }
        onActivityResumed.add(func1);
        return this;
    }

    public ListenersController removeOnActivityResumedListener(Func1 func1) {
        if (!Util.isEmpty(onActivityResumed) ) {
            onActivityResumed.remove(func1);
        }
        return this;
    }

    public ListenersController addOnActivityPausedListener(Func1 func1) {
        if (onActivityPaused == null) {
            onActivityPaused = new LinkedList<>();
        }
        onActivityPaused.add(func1);
        return this;
    }

    public ListenersController removeOnActivityPausedListener(Func1 func1) {
        if (!Util.isEmpty(onActivityPaused) ) {
            onActivityPaused.remove(func1);
        }
        return this;
    }

    public ListenersController addOnActivityStoppedListener(Func1 func1) {
        if (onActivityStopped == null) {
            onActivityStopped = new LinkedList<>();
        }
        onActivityStopped.add(func1);
        return this;
    }

    public ListenersController removeOnActivityStoppedListener(Func1 func1) {
        if (!Util.isEmpty(onActivityStopped) ) {
            onActivityStopped.remove(func1);
        }
        return this;
    }

    public ListenersController addOnActivityDestroyedListener(Func1 func1) {
        if (onActivityDestroyed == null) {
            onActivityDestroyed = new LinkedList<>();
        }
        onActivityDestroyed.add(func1);
        return this;
    }

    public ListenersController removeOnActivityDestroyedListener(Func1 func1) {
        if (!Util.isEmpty(onActivityDestroyed) ) {
            onActivityDestroyed.remove(func1);
        }
        return this;
    }

    public ListenersController addOnSaveInstanceStateListener(Func2 func2) {
        if (onActivitySaveInstanceState == null) {
            onActivitySaveInstanceState = new LinkedList<>();
        }
        onActivitySaveInstanceState.add(func2);
        return this;
    }

    public ListenersController removeOnSaveInstanceStateListener(Func2 func2) {
        if (!Util.isEmpty(onActivitySaveInstanceState) ) {
            onActivitySaveInstanceState.remove(func2);
        }
        return this;
    }

    public ListenersController addLifeCycleListener(ListenAll listener) {
        if (onAll == null) {
            onAll = new LinkedList<>();
        }
        onAll.add(listener);
        return this;
    }

    public ListenersController removeLifeCycleListener(ListenAll listener) {
        if (!Util.isEmpty(onAll)) {
            onAll.remove(listener);
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj instanceof  ListenersController) {
            ListenersController that = (ListenersController) obj;
            return this.activityId == that.activityId;
        }
        return false;
    }
}
