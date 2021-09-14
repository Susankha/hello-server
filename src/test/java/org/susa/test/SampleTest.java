package org.susa.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.logging.Logger;

public class SampleTest {

    String name;
    Logger log = Logger.getLogger(SampleTest.class.getName());

    @Before
    public void setUp() throws Exception {
        log.info("==== Starting SampleTest ====");
        name = "Susankha";
    }

    @Test
    public void meth() {
        Assert.assertEquals("Susankha",name);
        Assert.assertTrue(true);
        log.info("==== Executing meth() ====");
    }

    @After
    public void tearDown() throws Exception {
        log.info("==== Ending SampleTest ====");
    }
}
