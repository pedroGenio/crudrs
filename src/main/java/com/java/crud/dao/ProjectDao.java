package com.java.crud.dao;

import com.java.crud.enums.StatusEnum;
import com.java.crud.model.Entity;
import com.java.crud.model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao extends BaseDao {


    @Override
    public Entity create(Entity entity) throws Exception {

        String sql = "INSERT INTO sch_crud.project (version, name, description, status, created_at, created_by) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = getPreparedStatement(sql);
        try {

            ps.setLong(1, ((Project) entity).getVersion());
            ps.setString(2, ((Project) entity).getName());
            ps.setString(3, ((Project) entity).getDescription());
            ps.setString(4, ((Project) entity).getStatus());
            ps.setObject(5, LocalDate.now());
            ps.setLong(6, new Long(1)); // Example of user id to create data;

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                entity.setId(rs.getInt("id")); // new id
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }

        return entity;
    }


    @Override
    public void update(Entity entity, Entity entityOld) throws Exception {

        String sql = "UPDATE sch_crud.project SET " +
                "version = ?," +
                "name = ?," +
                "description = ?," +
                "status = ?," +
                "updated_at = ?," +
                "updated_by = ?) WHERE" +
                "id = ? AND VERSION = ?";

        PreparedStatement ps = getPreparedStatement(sql);
        try {

            ps.setLong(1, ((Project) entity).getVersioning());
            ps.setString(2, ((Project) entity).getName());
            ps.setString(3, ((Project) entity).getDescription());
            ps.setString(4, ((Project) entity).getStatus());
            ps.setObject(5, LocalDate.now());
            ps.setLong(6, new Long(2)); // Example of user id;
            ps.setInt(7, entityOld.getId());
            ps.setLong(8, ((Project) entityOld).getVersion());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }


    @Override
    public Entity findById(Integer id) throws Exception {
        Project project = new Project();

        String sql = "SELECT id, version, name, description, status, created_at, created_by, updated_at, updated_by FROM sch_crud.project WHERE id = ?";
        PreparedStatement ps = getPreparedStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                 project = fillProject(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
        }

        return project;
    }

    @Override
    public void delete(Entity entity) throws Exception {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM sch_crud.project WHERE id = ? AND version = ?";

            ps = getPreparedStatement(sql);
            ps.setInt(1, entity.getId());
            ps.setLong(2, ((Project) entity).getVersion());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }

    @Override
    public List<Project> readAll() throws Exception {
        List<Project> list = new ArrayList<>();

        String sql = "SELECT id, version, name, description, status, created_at, created_by, updated_at, updated_by FROM sch_crud.project";
        PreparedStatement ps = getPreparedStatement(sql);

        ResultSet rs = ps.executeQuery();
        try {

            while (rs.next()) {
                Project project = fillProject(rs);

                list.add(project);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            rs.close();
            ps.close();
        }

        return list;
    }

    private Project fillProject(ResultSet rs) throws Exception {
        Project project = new Project();
        project.setId(rs.getInt("id"));
        project.setVersion(rs.getLong("version"));
        project.setName(rs.getString("name"));
        project.setDescription(rs.getString("description"));
        project.setStatus(rs.getString("status"));
        project.setCreatedAt(rs.getObject("created_at", LocalDate.class));
        project.setCreatedBy(rs.getLong("created_by"));
        project.setUpdatedAt(rs.getObject("updated_at", LocalDate.class));
        project.setUpdatedBy(rs.getLong("updated_by"));
        return project;
    }

    private ZonedDateTime teste(String zone) {
        Instant instant = Instant.now();

        System.out.println("Instant : " + instant);

        return instant.atZone(ZoneId.systemDefault());
    }


}
