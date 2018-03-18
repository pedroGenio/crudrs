package com.java.crud.dao;

import com.java.crud.model.BaseEntity;
import com.java.crud.model.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class DAO for communication with the database
 */
public class ProjectDao extends BaseDao {

    /**
     * Inserts data from JSON application
     * For createdBy, is using an example of ID for User
     *
     * @param baseEntity
     * @return
     * @throws Exception
     */
    @Override
    public BaseEntity create(BaseEntity baseEntity) throws Exception {

        String sql = "INSERT INTO sch_crud.project (version, name, description, status, created_at, created_by) VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";

        PreparedStatement ps = getPreparedStatement(sql);
        ResultSet rs = null;
        try {

            ps.setLong(1, ((Project) baseEntity).getVersion());
            ps.setString(2, ((Project) baseEntity).getName());
            ps.setString(3, ((Project) baseEntity).getDescription());
            ps.setString(4, ((Project) baseEntity).getStatus());
            ps.setObject(5, LocalDateTime.now());
            ps.setLong(6, 1L); // Example of user id to create data;

            rs = ps.executeQuery();

            if (rs.next()) {
                baseEntity.setId(rs.getInt("id")); // new id
            }
            rs.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }

        return baseEntity;
    }

    /**
     * Update method and versioning column version
     * One way for versioning control
     * For updateAt, is using an example of id User
     *
     * @param baseEntity    fields from JSON
     * @param baseEntityOld from findBy to use id and version for versioning control
     * @throws Exception
     */
    @Override
    public void update(BaseEntity baseEntity, BaseEntity baseEntityOld) throws Exception {

        String sql = "UPDATE sch_crud.project SET " +
                "version = ?," +
                "name = ?," +
                "description = ?," +
                "status = ?," +
                "updated_at = ?," +
                "updated_by = ? WHERE " +
                "id = ? AND VERSION = ?";

        PreparedStatement ps = getPreparedStatement(sql);

        try {
            ps.setLong(1, ((Project) baseEntityOld).getVersion() + 1);  // Version control, just one way to have this control
            ps.setString(2, ((Project) baseEntity).getName());
            ps.setString(3, ((Project) baseEntity).getDescription());
            ps.setString(4, ((Project) baseEntity).getStatus());
            ps.setObject(5, LocalDateTime.now());
            ps.setLong(6, 2L); // Example of user id;
            ps.setInt(7, baseEntityOld.getId());
            ps.setLong(8, ((Project) baseEntityOld).getVersion());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw e;
        } finally {
            ps.close();
        }
    }


    /**
     * Finds one data by ID
     *
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public BaseEntity findById(Integer id) throws Exception {
        Project project = null;

        String sql = "SELECT id, version, name, description, status, created_by, created_at, updated_by, updated_at FROM sch_crud.project WHERE id = ?";
        PreparedStatement ps = getPreparedStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        try {
            if (rs.next()) {
                project = fillProject(rs);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            rs.close();
            ps.close();
        }

        return project;
    }

    /**
     * Remove data using ID and Version from findBy
     * One way for versioning control
     *
     * @param baseEntity
     * @throws Exception
     */
    @Override
    public void delete(BaseEntity baseEntity) throws Exception {
        PreparedStatement ps = null;
        try {
            String sql = "DELETE FROM sch_crud.project WHERE id = ? AND version = ?";

            ps = getPreparedStatement(sql);
            ps.setInt(1, baseEntity.getId());
            ps.setLong(2, ((Project) baseEntity).getVersion());

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
        project.setCreatedAt(rs.getObject("created_at", LocalDateTime.class));
        project.setCreatedBy(rs.getLong("created_by"));
        project.setUpdatedAt(rs.getObject("updated_at", LocalDateTime.class));
        project.setUpdatedBy(rs.getLong("updated_by"));
        return project;
    }

}
