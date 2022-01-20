package com.hansoft.tryunittest;


import android.Manifest;
import android.app.Activity;
//import android.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.fragment.app.FragmentTransaction;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.Shadows;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.internal.IShadow;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowListView;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class ApplicationTest {

    @Test
    public void testActivity() {
        // Context of the app under test.
        Activity activity = Robolectric.buildActivity(SecondActivity.class).create().start().resume().get();
        EditText scoreEditText = activity.findViewById(R.id.scoreEditText);
        scoreEditText.setText("Hello World!");
        assertEquals(scoreEditText.getText().toString(), "Hello World!");

        ShadowActivity activityShadow = Shadows.shadowOf(activity);
        activityShadow.grantPermissions(Manifest.permission.READ_SMS);
        Button submitButton = activity.findViewById(R.id.submitButton);
        submitButton.performClick();
        assertEquals(scoreEditText.getText().toString(), "good morning");
        String nextActivityName = activityShadow.getNextStartedActivity().getComponent().getClassName();
        com.hansoft.tryunittest.Log.d("aaa", "testActivity: nextActivityName = " + nextActivityName);
        assertEquals(nextActivityName, ThirdActivity.class.getName());
    }

    @Test
    public void testListView()
    {
        Activity activity = Robolectric.buildActivity(SecondActivity.class).create().start().resume().get();
        ListView myListView = activity.findViewById(R.id.myListView);
        ShadowListView listviewShadow = Shadows.shadowOf(myListView);

        listviewShadow.populateItems();
        listviewShadow.clickFirstItemContainingText("Apple");
    }

    @Test
    public void testFragment()
    {
        ActivityController activityController = Robolectric.buildActivity(ThirdActivity.class).create();

        FirstFragment firstFragment = new FirstFragment();
        AppCompatActivity appCompatActivity = (AppCompatActivity)activityController.get();
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction fTransaction = fragmentManager.beginTransaction();

        fTransaction.add(R.id.firstfragment,firstFragment,"firstfragment").commit();

        activityController.start().resume();

    }




}
