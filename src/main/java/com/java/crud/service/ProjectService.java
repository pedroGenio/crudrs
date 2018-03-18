package com.java.crud.service;

import com.java.crud.dao.ProjectDao;
import com.java.crud.model.Project;
import com.java.crud.util.ConnectionUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class for control of the operation and calling class DAO to communicate with the DB
 */
@Path("/project")
public class ProjectService {
    final static Log logger = LogFactory.getLog(ProjectService.class);

    @Context
    UriInfo uriInfo;
    private ProjectDao dao = new ProjectDao();

    /**
     * @return
     * @throws Exception
     */
    @GET
    @Path("/projects")
    @Produces({MediaType.APPLICATION_JSON})
    public List<Project> list() throws Exception {
        List<Project> list;
        try {
            list = dao.readAll();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        } finally {
            ConnectionUtil.getConnection().close();
        }
        return list;
    }

    /**
     * @param project
     * @return
     * @throws Exception
     */
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Project project) throws Exception {

        try {
            ConnectionUtil.getConnection().setAutoCommit(false);

            project = (Project) dao.create(project);
            String view = uriInfo.getPath() + "/" + project.getId();
            URI uri = new URI(view);

            ConnectionUtil.getConnection().commit();

            return Response.created(uri).build();

        } catch (SQLException e) {
            ConnectionUtil.getConnection().rollback();

            logger.error(e.getStackTrace());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } finally {
            ConnectionUtil.autoCommitAndClose();
        }
    }

    /**
     * @param id
     * @param project
     * @return
     * @throws Exception
     */
    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(@PathParam("id") Integer id, Project project) throws Exception {
        try {
            ConnectionUtil.getConnection().setAutoCommit(false);
            Project projectOld = (Project) dao.findById(id);
            dao.update(project, projectOld);
            String view = uriInfo.getPath() + "/" + project.getId();
            URI uri = new URI(view);

            ConnectionUtil.getConnection().commit();
            return Response.created(uri).build();
        } catch (SQLException e) {
            ConnectionUtil.getConnection().rollback();
            e.printStackTrace();
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } finally {
            ConnectionUtil.autoCommitAndClose();
        }
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) throws Exception {

        try {
            Project project = (Project) dao.findById(id);
            return Response.ok(project).build();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } finally {
            ConnectionUtil.close();
        }
    }

    /**
     * @param id
     * @return
     * @throws Exception
     */
    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Integer id) throws Exception {
        try {
            ConnectionUtil.getConnection().setAutoCommit(false);

            Project project = (Project) dao.findById(id);

            // simple validation to check Project class
            if (validatorProject(project)) {
                dao.delete(project);
                ConnectionUtil.getConnection().commit();
                return Response.noContent().build();
            }

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("The project doesn't exist").build();
        } catch (SQLException e) {
            ConnectionUtil.getConnection().rollback();

            logger.error(e.getStackTrace());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        } finally {
            ConnectionUtil.autoCommitAndClose();
        }
    }

    private boolean validatorProject(Project project) {
        if (project == null) {
            return false;
        }
        return true;
    }

    @GET
    @Path("/testParam/{id}")
    public String getTestParam(@PathParam("id") String id) {
        return "id: " + id;
    }

    @GET
    @Path("/testFixedPath")
    public String getTestFixedPath() {
        return "test Jersey";
    }
}
