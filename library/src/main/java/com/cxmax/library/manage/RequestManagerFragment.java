package com.cxmax.library.manage;

import android.annotation.SuppressLint;
import android.app.Fragment;

import com.cxmax.library.lifecycle.ActivityFragmentLifecycle;

/**
 * @Author CaiXi on  2016/12/20 00:23.
 * @Github: https://github.com/cxMax
 * @Description  A view-less Fragment used to safely store an RequestManager that can be
 * used to start,stop and manage glide requests started for targets the fragment or activity
 * this fragment is a child of.
 */

public class RequestManagerFragment extends Fragment {
    private final ActivityFragmentLifecycle lifecycle;

    public RequestManagerFragment() {
        this(new ActivityFragmentLifecycle());
    }

    // For testing only.
    @SuppressLint("ValidFragment")
    RequestManagerFragment(ActivityFragmentLifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    ActivityFragmentLifecycle getLifecycle() {
        return lifecycle;
    }

    @Override
    public void onStart() {
        super.onStart();
        lifecycle.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        lifecycle.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        lifecycle.onDestroy();
    }
}
