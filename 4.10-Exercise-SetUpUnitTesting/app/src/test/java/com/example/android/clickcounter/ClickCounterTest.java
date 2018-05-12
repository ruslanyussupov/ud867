package com.example.android.clickcounter;

import org.junit.Assert;
import org.junit.Test;

public class ClickCounterTest {

    private ClickCounter clickCounter = new ClickCounter();

    @Test
    public void getCount() {
        int count = clickCounter.getCount();
        Assert.assertEquals(0, count);
    }

    @Test
    public void increment() {
        clickCounter.increment();
        int count = clickCounter.getCount();
        Assert.assertEquals(1, count);
    }


}