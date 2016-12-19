package com.cxmax.library.lifecycle;

/**
 * @Author CaiXi on  2016/12/20 01:14.
 * @Github: https://github.com/cxMax
 * @Description
 */

public class ApplicationLifecycle implements Lifecycle {
    @Override
    public void addListener(LifecycleListener listener) {
        listener.onStart();
    }

    @Override
    public void removeListener(LifecycleListener listener) {
        // Do nothing.
    }
}