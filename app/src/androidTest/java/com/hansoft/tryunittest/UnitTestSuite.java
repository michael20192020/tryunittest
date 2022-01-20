package com.hansoft.tryunittest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({UITest.class,
        UnitTestSample.class})
public class UnitTestSuite {
}
