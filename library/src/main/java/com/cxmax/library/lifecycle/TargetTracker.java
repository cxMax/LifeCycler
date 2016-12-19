package com.cxmax.library.lifecycle;

import com.cxmax.library.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * @Author CaiXi on  2016/12/15 00:53.
 * @Github: https://github.com/cxMax
 * @Description
 */

public final class TargetTracker implements LifecycleListener{

    private final Set<Target<?>> targets =
            Collections.newSetFromMap(new WeakHashMap<Target<?>, Boolean>());

    public void track(Target<?> target){
        targets.add(target);
    }

    public void untrack(Target<?> target){
        targets.remove(target);
    }

    @Override
    public void onStart() {
        for (Target<?> target : Util.getSnapshot(targets)) {
            target.onStart();
        }
    }

    @Override
    public void onStop() {
        for (Target<?> target : Util.getSnapshot(targets)) {
            target.onStop();
        }
    }

    @Override
    public void onDestroy() {
        for (Target<?> target : Util.getSnapshot(targets)) {
            target.onDestroy();
        }
    }

    public List<Target<?>> getAll() {
        return new ArrayList<>(targets);
    }

    public void clear() {
        targets.clear();
    }
}
