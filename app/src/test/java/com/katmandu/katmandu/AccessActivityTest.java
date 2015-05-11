package com.katmandu.katmandu;

import android.content.Intent;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by jonatan on 11/05/15.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 21)
public class AccessActivityTest {

    private ActivityController<AccessActivity> controller;

    @Before
    public void setup(){
        controller = Robolectric.buildActivity(AccessActivity.class);
        controller.get().passwordStore = mock(PasswordStore.class);
    }
    @Test
    public void ifWrongPasswordThenShowMesage(){
        testVisibility(true, View.VISIBLE);

        verify(controller.get().passwordStore).delete(controller.get());
    }

    @Test
    public void ifNoWrongPasswordThenHideMesage(){
        testVisibility(false, View.GONE);
    }

    private void testVisibility(boolean wrongPasswdExtra, int visibility) {

        controller.withIntent(new Intent().putExtra(AccessActivity.WRONG_PASSWORD, wrongPasswdExtra));

        AccessActivity accessActivity = controller.create().resume().get();

        assertEquals(visibility, accessActivity.findViewById(R.id.wrongPasswordTextView).getVisibility());
    }

}