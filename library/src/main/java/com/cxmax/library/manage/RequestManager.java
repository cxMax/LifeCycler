package com.cxmax.library.manage;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;

import com.cxmax.library.Glide;
import com.cxmax.library.lifecycle.Lifecycle;
import com.cxmax.library.lifecycle.LifecycleListener;
import com.cxmax.library.lifecycle.Target;
import com.cxmax.library.lifecycle.TargetTracker;
import com.cxmax.library.util.Util;

/**
 * @Author CaiXi on  2016/12/20 00:50.
 * @Github: https://github.com/cxMax
 * @Description A class for managing and starting requests for Glide.Can use activity, fragment and connectivity
 * lifecycle events to intelligently stop, start, and restart requests. Retrieve either by
 * instantiating a new object, or to take advantage built in Activity and Fragment lifecycle
 * handling, use the static Glide.load methods with your Fragment or Activity
 */

public class RequestManager implements LifecycleListener {
    private final Glide glide;
    final Lifecycle lifecycle;
    private final TargetTracker targetTracker = new TargetTracker();
    private final Handler mainHandler = new Handler(Looper.getMainLooper());
    private final Runnable addSelfToLifecycle = new Runnable() {
        @Override
        public void run() {
            lifecycle.addListener(RequestManager.this);
        }
    };

    public RequestManager(Glide glide, Lifecycle lifecycle) {
        this.glide = glide;
        this.lifecycle = lifecycle;
        if (Util.isOnBackgroundThread()) {
            mainHandler.post(addSelfToLifecycle);
        } else {
            lifecycle.addListener(this);
        }
    }

    @Override
    public void onStart() {
        targetTracker.onStart();
    }

    @Override
    public void onStop() {
        targetTracker.onStop();
    }

    @Override
    public void onDestroy() {
        targetTracker.onDestroy();
        for (Target<?> target : targetTracker.getAll()) {
            clear(target);
        }
        targetTracker.clear();
    }
    public void clear(@Nullable final Target<?> target) {
        if (target == null) {
            return;
        }

        if (Util.isOnMainThread()) {
            untrackOrDelegate(target);
        } else {
            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    clear(target);
                }
            });
        }
    }
    void track(Target<?> target){
        targetTracker.track(target);
    }
    void untrack(Target<?> target) {
        targetTracker.untrack(target);
    }
    private void untrackOrDelegate(Target<?> target) {
        untrack(target);
    }
}
