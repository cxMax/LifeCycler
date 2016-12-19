package com.cxmax.library.manage;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.cxmax.library.Glide;
import com.cxmax.library.lifecycle.ApplicationLifecycle;
import com.cxmax.library.util.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author CaiXi on  2016/12/20 00:48.
 * @Github: https://github.com/cxMax
 * @Description A collection of static methods for creating new RequestManager or
 * retrieving existing ones from activities and fragment
 */

public class RequestManagerRetriever implements Handler.Callback {

    private static final RequestManagerRetriever INSTANCE = new RequestManagerRetriever();
    private volatile RequestManager applicationManager;
    final Map<FragmentManager, RequestManagerFragment> pendingRequestManagerFragments =
            new HashMap<>();
    private final Handler handler;

    public static RequestManagerRetriever get() {
        return INSTANCE;
    }

    RequestManagerRetriever() {
        handler = new Handler(Looper.getMainLooper(), this /* Callback */);
    }

    @Override
    public boolean handleMessage(Message message) {
        return false;
    }

    public RequestManager get(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        } else if (Util.isOnMainThread() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return get((FragmentActivity) context);
            } else if (context instanceof Activity) {
                return get((Activity) context);
            } else if (context instanceof ContextWrapper) {
                return get(((ContextWrapper) context).getBaseContext());
            }
        }

        return getApplicationManager(context);
    }

    public RequestManager get(FragmentActivity activity) {
        if (Util.isOnBackgroundThread()) {
            return get(activity.getApplicationContext());
        } else {
            assertNotDestroyed(activity);
            android.support.v4.app.FragmentManager fm = activity.getSupportFragmentManager();
            return supportFragmentGet(activity, fm, null);
        }
    }

    public RequestManager get(Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException(
                    "You cannot start a load on a fragment before it is attached");
        }
        if (Util.isOnBackgroundThread()) {
            return get(fragment.getActivity().getApplicationContext());
        } else {
            android.support.v4.app.FragmentManager fm = fragment.getChildFragmentManager();
            return supportFragmentGet(fragment.getActivity(), fm, fragment);
        }
    }

    public RequestManager get(Activity activity) {
        if (Util.isOnBackgroundThread() || Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return get(activity.getApplicationContext());
        } else {
            assertNotDestroyed(activity);
            android.app.FragmentManager fm = activity.getFragmentManager();
            return fragmentGet(activity, fm, null);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static void assertNotDestroyed(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    private RequestManager getApplicationManager(Context context) {
        if (applicationManager == null) {
            synchronized (this) {
                if (applicationManager == null) {

                    // TODO(b/27524013): Factor out this Glide.get() call.
                    Glide glide = Glide.get(context);
                    applicationManager =
                            new RequestManager(
                                    glide, new ApplicationLifecycle());
                }
            }
        }

        return applicationManager;
    }

    RequestManager fragmentGet(Context context, android.app.FragmentManager fm) {
        RequestManagerFragment current = getRequestManagerFragment(fm);
        RequestManager requestManager = current.getRequestManager();
        if (requestManager == null) {
            // TODO(b/27524013): Factor out this Glide.get() call.
            Glide glide = Glide.get(context);
            requestManager =
                    new RequestManager(glide, current.getLifecycle());
        }
        return requestManager;
    }
}
