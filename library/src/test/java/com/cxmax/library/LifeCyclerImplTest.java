package com.cxmax.library;

import android.app.Activity;
import android.util.SparseArray;

import com.cxmax.library.functions.Consumer;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.regex.Matcher;

import static org.junit.Assert.*;

/**
 * @describe :
 * @usage :
 * <p>
 * </p>
 * Created by caixi on 17-7-14.
 */
public class LifeCyclerImplTest {

    private Activity activity;
    private SparseArray<Consumer> onActivityCreated;
    private Consumer consumer;

    @Before
    public void setUp() throws Exception {
        activity = Mockito.mock(Activity.class, Mockito.RETURNS_SMART_NULLS);
        consumer = Mockito.mock(Consumer.class, Mockito.RETURNS_SMART_NULLS);
        SparseArray comsumers = Mockito.mock(SparseArray.class, Mockito.RETURNS_SMART_NULLS);
        onActivityCreated = Mockito.spy(comsumers);
        onActivityCreated.put(activity.hashCode(), consumer);
    }

    @Test
    public void addOnActivityCreatedListener() throws Exception {
        Activity activity = Mockito.mock(Activity.class, Mockito.RETURNS_SMART_NULLS);
        Consumer consumer = Mockito.mock(Consumer.class, Mockito.RETURNS_SMART_NULLS);
        SparseArray comsumers = Mockito.mock(SparseArray.class, Mockito.RETURNS_SMART_NULLS);

        SparseArray spy = Mockito.spy(comsumers);

        comsumers.put(activity.hashCode(), consumer);
        spy.put(activity.hashCode(), consumer);

        Mockito.verify(comsumers).put(activity.hashCode(), consumer);
        Assert.assertNotNull(spy.get(activity.hashCode()));
    }

    @Test
    public void handleLifeCycleEvent() throws Exception {
        Assert.assertNotNull(onActivityCreated.get(activity.hashCode()));
    }
}