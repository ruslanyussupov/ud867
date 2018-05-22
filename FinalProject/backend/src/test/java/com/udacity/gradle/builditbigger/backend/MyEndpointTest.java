package com.udacity.gradle.builditbigger.backend;

import org.junit.Assert;
import org.junit.Test;



public class MyEndpointTest {
    @Test
    public void getJokesJson() throws Exception {

        Assert.assertNotNull(new MyEndpoint().getJokesJson().getJson());

    }

}