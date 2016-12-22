package com.cxmax.lifecycler;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cxmax.library.LifeCycler;
import com.cxmax.library.func.Func1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lifeCycleUsage();
    }

    private void lifeCycleUsage() {
        new LifeCycler().with(this)
                .addOnActivityDestroyedListener(new Func1() {
                    @Override
                    public void run(Activity activity) {
                        //do what you want to do in this period
                    }
                });
    }
}
