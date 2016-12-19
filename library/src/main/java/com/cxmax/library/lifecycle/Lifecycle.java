package com.cxmax.library.lifecycle;

/**
 * @Author CaiXi on  2016/12/15 00:47.
 * @Github: https://github.com/cxMax
 * @Description
 */

public interface Lifecycle {

    void addListener(LifecycleListener listener);

    void removeListener(LifecycleListener listener);
}
