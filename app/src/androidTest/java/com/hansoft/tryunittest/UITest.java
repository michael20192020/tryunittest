package com.hansoft.tryunittest;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static com.google.common.truth.Truth.assertThat;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class UITest {
    private static final String FAKE_STRING = "HELLO_WORLD";
    private Context context = ApplicationProvider.getApplicationContext();

    @Rule
    public ActivityTestRule<SecondActivity> rule = new ActivityTestRule<>(SecondActivity.class);



    @Test
    public void clickTest() {
        onView(withId(R.id.submitButton)).perform(click());
    }


    @Test
    public void startActivity() {

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        Intent myIntent = new Intent(appContext, ThirdActivity.class);

        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        appContext.startActivity(myIntent);
    }



    @Test
    public void testTypeText()
    {
        onView(withId(R.id.scoreEditText)).perform(typeText("Hello"), click());
    }

    @Test
    public void readStringFromContext_LocalizedString() {
        // Given a Context object retrieved from Robolectric...
        ClassUnderTest myObjectUnderTest = new ClassUnderTest(context);

        // ...when the string is returned from the object under test...
        String result = myObjectUnderTest.getHelloWorldString();

        // ...then the result should be the expected one.
        assertThat(result).isEqualTo(FAKE_STRING);

    }

}
