package com.hansoft.tryunittest;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class ScrollTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testScrollTo() throws InterruptedException {

        Thread.sleep(2000);
        onView(withId(R.id.nameEditText)).perform(clearText());
        onView(withId(R.id.nameEditText)).perform(typeText("tom"),closeSoftKeyboard());
       // Thread.sleep(2000);
        onView(withId(R.id.emailEditText)).perform(scrollTo());
        onView(withId(R.id.emailEditText)).perform(clearText());
        onView(withId(R.id.emailEditText)).perform(typeText("abc@hotmail.com"),closeSoftKeyboard());
      //  Thread.sleep(2000);
        onView(withId(R.id.saveButton)).perform(scrollTo(),click());
        Thread.sleep(2000);
    }
}
