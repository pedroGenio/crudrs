package com.java.crud.dao;

import com.java.crud.interfaces.Crud;
import com.java.crud.model.BaseEntity;
import com.java.crud.util.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

/**
 * Abstract class to reuse methods
 */
public abstract class BaseDao implements Crud {

    public PreparedStatement getPreparedStatement(String sql) throws Exception {
        try {
            return getConnection().prepareStatement(sql);
        } catch (Exception e) {
            throw e;
        }
    }


    protected Connection getConnection() throws Exception {
        return ConnectionUtil.getConnection();
    }


    @Override
    public BaseEntity create(BaseEntity baseEntity) throws Exception {

        return null;
    }

    @Override
    public void update(BaseEntity baseEntity, BaseEntity baseEntityOld) throws Exception {

    }

    @Override
    public void delete(BaseEntity baseEntity) throws Exception {

    }

    @Override
    public List<?> readAll() throws Exception {
        return null;
    }

    @Override
    public BaseEntity findById(Integer id) throws Exception {
        return null;
    }
}
