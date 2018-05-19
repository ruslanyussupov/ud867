package com.example.jokes;

import org.junit.Assert;


public class DataTest {

    @org.junit.Test
    public void getJokesJson() throws Exception {

        Assert.assertNotNull(Data.getJokesJson());

    }

}