package com.cxmax.library;


import android.app.Activity;
import android.app.Fragment;
import android.support.annotation.NonNull;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-7-13.
 */

public class LifeCycler {

    public static ILifeCycler with(@NonNull Activity activity) {
        return LifeCyclerRetriever.getInstance().get(activity);
    }

    public static ILifeCycler with(@NonNull Fragment fragment) {
        return LifeCyclerRetriever.getInstance().get(fragment.getActivity());
    }

}
