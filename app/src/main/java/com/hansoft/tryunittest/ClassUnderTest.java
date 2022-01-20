package com.hansoft.tryunittest;

import android.content.Context;

public class ClassUnderTest {
    Context mycontext;
    public ClassUnderTest(Context context)
    {
        mycontext = context;
    }

    public String getHelloWorldString()
    {
        return "HELLO_WORLD";
    }
}
