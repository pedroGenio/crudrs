package com.java.crud.test;

import com.java.crud.service.ProjectService;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;

public class ProjectServiceTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(ProjectService.class);
    }

    @Test
    public void ordersPathParamTest() {
        String response = target("testParam/123").request().get(String.class);
        Assert.assertTrue("id: 123".equals(response));
    }

    @Test
    public void ordersFixedPathTest() {
        String response = target("testFixedPath").request().get(String.class);
        Assert.assertTrue("test Jersey".equals(response));
    }
}
