package com.hansoft.tryunittest;

import android.app.Activity;
import android.os.Build;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Arrays;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.describedAs;
import static org.hamcrest.Matchers.either;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;


@RunWith(RobolectricTestRunner.class)
@Config(sdk = {Build.VERSION_CODES.O_MR1})
public class SimpleTest {

    @Before
    public void init()
    {
        com.hansoft.tryunittest.Log.d("aaa", "init: before test");
       // System.out.println("init: before test");
    }

    @After
    public void release()
    {
        com.hansoft.tryunittest.Log.d("aaa", "release: after test");
       // System.out.println("release: after test");
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void substraction_isCorrect()
    {
        assertEquals(2,4 - 2);
    }

    @Test
    public void multiple_isCorrect()
    {
        assertNotEquals(7,2 * 3);
    }

    @Test
    public void array_isEqual()
    {
        assertArrayEquals(new int[]{1,2,3},new int[]{1,2,3});
    }

    @Test
    public void null_isEqual()
    {
        String name = null;
        assertNull(name);
    }

    @Test
    public void null_isNotEqual()
    {
        String name = "tom";
        assertNotNull(name);
    }

    @Test
    public void true_isEqual()
    {
        boolean Ready = true;
        assertTrue(Ready);
    }

    @Test
    public void false_isEqual()
    {
        boolean Ready = false;
        assertFalse(Ready);
    }

    @Test
    public void same_isEqual()
    {
        int a = 10;
        int b = 10;
        assertSame(a,b);
    }

    @Test
    public void same_notEqual()
    {
        int a = 20;
        int b = 10;
        assertNotSame(a,b);
    }

    @Test
    public void check()
    {
        org.hamcrest.MatcherAssert.assertThat("myStringOfNote", startsWith("my"));
        org.hamcrest.MatcherAssert.assertThat("myStringOfNote", containsString("ring"));
        org.hamcrest.MatcherAssert.assertThat("foo", equalTo("foo"));
        org.hamcrest.MatcherAssert.assertThat(new String[] {"foo", "bar"}, equalTo(new String[] {"foo", "bar"}));
        org.hamcrest.MatcherAssert.assertThat("myValue", allOf(startsWith("my"), containsString("Val")));
        org.hamcrest.MatcherAssert.assertThat("myValue", anyOf(startsWith("foo"), containsString("Val")));
        org.hamcrest.MatcherAssert.assertThat("fab", both(containsString("a")).and(containsString("b")));
        org.hamcrest.MatcherAssert.assertThat("fan", either(containsString("a")).or(containsString("b")));
        org.hamcrest.MatcherAssert.assertThat(110, describedAs("expected ：%0", equalTo(110), 110));
        org.hamcrest.MatcherAssert.assertThat(Arrays.asList("bar", "baz"), everyItem(startsWith("ba")));
        org.hamcrest.MatcherAssert.assertThat("cheese", is(equalTo("cheese")));
        ThirdActivity thirdActivity = new ThirdActivity();
        org.hamcrest.MatcherAssert.assertThat(thirdActivity, instanceOf(ThirdActivity.class));
        org.hamcrest.MatcherAssert.assertThat(thirdActivity, isA(ThirdActivity.class));
    }

    @Test
    public void emailValidator_CorrectEmailSimple_ReturnsTrue() {
        com.google.common.truth.Truth.assertThat(EmailValidator.isValidEmail("name@email.com")).isTrue();
        com.google.common.truth.Truth.assertThat("a").isEqualTo("a");
    }

}
