package com.java.crud.test;

import com.java.crud.dao.ProjectDao;
import com.java.crud.model.Project;
import com.java.crud.util.ConnectionUtil;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Before running all Tests is recommend running the project.sql file at Resources folder and have an empty table
 */
@RunWith(MockitoJUnitRunner.class)
public class ProjectDaoTest {
    @Mock
    private Connection connection;

    ProjectDao projectDao = new ProjectDao();

    private Project project;

    @Before
    public void setUp() throws Exception{

        project = new Project();
        project.setName("TestName");
        project.setDescription("Test Description");
        project.setStatus("REVIEW");
        project.setCreatedBy(1L);
        project.setCreatedAt(LocalDateTime.now());
        project.setUpdatedBy(2L);
        project.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void testIfConnectionNotNull() throws Exception{
        connection = ConnectionUtil.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void testCreateProject() throws Exception {
        projectDao.create(project);
        Project test = (Project) projectDao.findById(1);
        assertEquals(project.getName(), test.getName());
        assertEquals(project.getDescription(), test.getDescription());
        assertEquals(project.getStatus(), test.getStatus());

    }


}
