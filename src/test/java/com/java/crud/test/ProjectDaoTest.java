package com.java.crud.test;

import com.java.crud.dao.ProjectDao;
import com.java.crud.model.Project;
import com.java.crud.util.ConnectionUtil;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Remembering that, to do this test has success, the TABLE must be EMPTY
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
//        project.setCreatedBy(1L);
//        project.setCreatedAt(LocalDateTime.now());
//        project.setUpdatedBy(2L);
//        project.setUpdatedAt(LocalDateTime.now());
    }

    /**
     * Testing connection
     * @throws Exception
     */
    @Test
    public void testAIfConnectionNotNull() throws Exception{
        connection = ConnectionUtil.getConnection();
        assertNotNull(connection);
    }

//    @Test
//    public void testCrud() throws Exception {
//        Project test = (Project) projectDao.create(project);
//        assertNotNull(test.getId());
//
//        test = (Project) projectDao.findById(1);
//        assertEquals(project.getName(), test.getName());
//        assertEquals(project.getDescription(), test.getDescription());
//        assertEquals(project.getStatus(), test.getStatus());
//        assertEquals(project.getCreatedBy(), test.getCreatedBy());
////        assertEquals(project.getCreatedAt(), test.getCreatedAt());
//        assertNull(test.getUpdatedBy());
//        assertNull(test.getUpdatedAt());
//
//        List<Project> list = projectDao.readAll();
//        assertEquals(1, list.size());
//
//        test = (Project) projectDao.findById(1);
//        projectDao.delete(test);
//
//        list = projectDao.readAll();
//        assertEquals(0, list.size());
//
//    }

    /**
     * Test create row
     * @throws Exception
     */
    @Test
    public void testBCreateProject() throws Exception {
        Project test = (Project) projectDao.create(project);
        assertNotNull(test.getId());
    }


    /**
     * Test find by
     * @throws Exception
     */
    @Test
    public void testCFindByProject() throws Exception {
        Project test = (Project) projectDao.findById(1);
        assertEquals(project.getName(), test.getName());
        assertEquals(project.getDescription(), test.getDescription());
        assertEquals(project.getStatus(), test.getStatus());
//        assertEquals(project.getCreatedBy(), test.getCreatedBy());
//        assertEquals(project.getCreatedAt(), test.getCreatedAt());
        assertEquals(new Long(0), test.getUpdatedBy());
        assertNull(test.getUpdatedAt());
    }

    /**
     * Test read all
     * @throws Exception
     */
    @Test
    public void testDReadAllProject() throws Exception {
        List<Project> list = projectDao.readAll();
        assertEquals(1, list.size());
    }

    /**
     * Test delete row
     * @throws Exception
     */
//    @Test
//    public void testERemoveProject() throws Exception {
//        Project test = (Project) projectDao.findById(1);
//        projectDao.delete(test);
//        List<Project> list = projectDao.readAll();
//        assertEquals(0, list.size());
//    }
}
