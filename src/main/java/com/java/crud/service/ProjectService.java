package com.java.crud.service;

import com.java.crud.dao.ProjectDao;
import com.java.crud.model.Project;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.List;

@Path("/project")
public class ProjectService {
    final static Log logger = LogFactory.getLog(ProjectService.class);

    @Context
    UriInfo uriInfo;
    private ProjectDao dao = new ProjectDao();

    @GET
    @Path("/projects")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Project> list() throws Exception {

        ProjectDao dao = new ProjectDao();
        return dao.readAll();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response create(Project project) {

        try {
            project = (Project) dao.create(project);
            String view = uriInfo.getPath() + "/" + project.getId();
            URI uri = new URI(view);
            return Response.created(uri).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response update(@PathParam("id") Integer id, Project project) throws Exception {

        ProjectDao dao = new ProjectDao();
        try {
            Project projectOld = (Project) dao.findById(id);
            dao.update(project, projectOld);
            String view = uriInfo.getPath() + "/" + project.getId();
            URI uri = new URI(view);
            return Response.created(uri).build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findById(@PathParam("id") Integer id) throws Exception {

        try {
            Project project = (Project) dao.findById(id);
            return Response.ok(project).build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Integer id) throws Exception {

        try {
            Project project = (Project) dao.findById(id);
            dao.delete(project);
            return Response.noContent().build();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Response.serverError().build();
    }

}
