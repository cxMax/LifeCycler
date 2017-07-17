package com.cxmax.lifecycler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.junit.Assert.*;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-7-14.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class , sdk = 21)
public class MainActivityTest {

    private MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);
    }

    @Test
    public void testHookActivityCreatedToast() throws Exception {
        Robolectric.buildActivity(MainActivity.class).create().start();
        assertEquals(ShadowToast.getTextOfLatestToast(), "这是一个弹窗");
    }
}