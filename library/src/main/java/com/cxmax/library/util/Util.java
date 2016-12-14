package com.cxmax.library.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @Author CaiXi on  2016/12/15 00:55.
 * @Github: https://github.com/cxMax
 * @Description
 */

public final class Util {

    /**
     * Returns a copy of the given list that is safe to iterate over and perform actions that may
     * modify the original list.
     *
     * <p> See #303 and #375. </p>
     */
    public static <T> List<T> getSnapshot(Collection<T> other) {
        List<T>  result = new ArrayList<T>(other.size());
        for (T item : other) {
            result.add(item);
        }
        return result;
    }
}
