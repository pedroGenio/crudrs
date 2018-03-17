package com.java.crud.dao;

import com.java.crud.interfaces.Crud;
import com.java.crud.model.Entity;
import com.java.crud.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public abstract class BaseDao implements Crud {



    public PreparedStatement getPreparedStatement(String sql) throws Exception {
        try {
            return getConnection().prepareStatement(sql);
        } catch (Exception e) {
            throw e;
        }
    }

    protected ResultSet query(String sql) throws Exception {
        return ConnectionFactory.query(sql);
    }


    protected Connection getConnection() {
        return ConnectionFactory.getConnection();
    }


    @Override
    public Entity create(Entity entity) throws Exception {

        return null;
    }

    @Override
    public void update(Entity entity, Entity entityOld) throws Exception {

    }

    @Override
    public void delete(Entity entity) throws Exception {

    }

    @Override
    public List<?> readAll() throws Exception {
        return null;
    }

    @Override
    public Entity findById(Integer id) throws Exception {
        return null;
    }
}
