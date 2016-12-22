package com.cxmax.library;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.util.SparseArray;

import com.cxmax.library.util.Asserts;

/**
 * @Author CaiXi on  2016/12/23 00:30.
 * @Github: https://github.com/cxMax
 * @Description
 */

public class LifeCycler {

    private static final SparseArray<SparseArray<ListenersController>> sAppIndex;
    static {
        sAppIndex = new SparseArray<>();
    }

    private static SparseArray<ListenersController> getControllerGroupByApplication(Application app) {
        SparseArray<ListenersController> group = findControllerGroupByApplication(app);
        if (group == null) {
            group = new SparseArray<>();
            app.registerActivityLifecycleCallbacks(mInnerCallbackImpl);
            sAppIndex.put(app.hashCode() , group);
        }
        return group;
    }

    private static ListenersController findListenerControllerByActivity(Activity activity) {
        Asserts.requireNonNull(activity);
        SparseArray<ListenersController> controllerGroup =
                findControllerGroupByApplication(activity.getApplication() );
        if (controllerGroup != null){
            return controllerGroup.get(activity.hashCode());
        }
        return null;
    }

    public static ListenersController with(Activity activity) {
        int activityId = Asserts.requireNonNull(activity).hashCode();
        SparseArray<ListenersController> group =
                getControllerGroupByApplication(activity.getApplication() );
        ListenersController controller = group.get(activityId);
        if (controller == null) {
            controller = new ListenersController(activityId);
            group.put(activityId, controller);
        }
        return controller;
    }

    private static SparseArray<ListenersController> findControllerGroupByApplication(Application app){
        return sAppIndex.get(Asserts.requireNonNull(app).hashCode());
    }

    private static Application.ActivityLifecycleCallbacks
            mInnerCallbackImpl = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            ListenersController controller = findListenerControllerByActivity(activity);
            if (controller != null) {
                controller.triggerOnActivityCreated(activity, bundle);
            }
        }

        @Override
        public void onActivityStarted(Activity activity) {
            ListenersController controller = findListenerControllerByActivity(activity);
            if (controller != null) {
                controller.triggerOnActivityStarted(activity);
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            ListenersController controller = findListenerControllerByActivity(activity);
            if (controller != null) {
                controller.triggerOnActivityResumed(activity);
            }
        }

        @Override
        public void onActivityPaused(Activity activity) {
            ListenersController controller = findListenerControllerByActivity(activity);
            if (controller != null) {
                controller.triggerOnActivityPaused(activity);
            }
        }

        @Override
        public void onActivityStopped(Activity activity) {
            ListenersController controller = findListenerControllerByActivity(activity);
            if (controller != null) {
                controller.triggerOnActivityStopped(activity);
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            ListenersController controller = findListenerControllerByActivity(activity);
            if (controller != null) {
                controller.triggerOnActivitySaveInstanceState(activity, bundle);
            }
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            ListenersController controller = findListenerControllerByActivity(activity);
            if (controller != null) {
                controller.triggerOnActivityDestroyed(activity);
                SparseArray<ListenersController> group =
                        findControllerGroupByApplication(activity.getApplication());
                if (group != null) {
                    group.remove(controller.getActivityId() );
                }
            }
        }

    };
}
