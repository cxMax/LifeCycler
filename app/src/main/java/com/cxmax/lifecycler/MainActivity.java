package com.cxmax.lifecycler;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.cxmax.library.LifeCycler;
import com.cxmax.library.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lifeCycleUsage();
    }

    private void lifeCycleUsage() {
        LifeCycler.with(this)
                .addOnActivityResumedListener(new Consumer() {
                    @Override
                    public void run(Activity activity, Bundle bundle) {
                        Toast.makeText(MainActivity.this , "hahahha" , Toast.LENGTH_LONG).show();
                    }
                })
                .addOnActivityDestroyedListener(new Consumer() {
                    @Override
                    public void run(Activity activity, Bundle bundle) {
                        Log.d(TAG, "It have hooked onDestroy() callback");
                    }
                });
    }
}
