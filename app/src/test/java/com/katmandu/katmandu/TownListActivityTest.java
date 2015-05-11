package com.katmandu.katmandu;

import android.content.Intent;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by jonatan on 11/05/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class TownListActivityTest {

    private ActivityController<TownListActivity> controller;
    private TownListActivity activity;
    private ArgumentCaptor<TownConnection.SynchCallback> captor;
    private TownAdapter mock;

    @Before
    public void setup(){
        controller = Robolectric.buildActivity(TownListActivity.class);

        activity = controller.get();

        mock = mock(TownAdapter.class);
        activity.adapter = mock;

        captor = ArgumentCaptor.forClass(TownConnection.SynchCallback.class);

        controller.create();
    }
    @Test
    public void when403ThenGoToAccessScreen(){
        activity.downloadData();
        verify(mock).downloadData(captor.capture());

        testRequestPassword();
    }
    @Test
    public void when403InUploadThenGoToAccessScreen(){
        activity.synchronize();
        verify(mock).uploadData(captor.capture());

        testRequestPassword();
    }

    private void testRequestPassword() {
        captor.getValue().fail(TownConnection.INVALID_PASSWORD);

        assertTrue(activity.isFinishing());
        Intent nextIntent = shadowOf(activity).getNextStartedActivity();
        assertTrue(nextIntent.getBooleanExtra(AccessActivity.WRONG_PASSWORD,false));
        assertEquals(AccessActivity.class.getCanonicalName(),nextIntent.getComponent().getClassName());
    }
}