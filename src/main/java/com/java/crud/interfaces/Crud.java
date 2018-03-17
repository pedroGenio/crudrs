package com.java.crud.interfaces;

import com.java.crud.model.BaseEntity;

import java.util.List;

/**
 * Interface where there are all main methods for Crud/DAO
 */
public interface Crud  {

    BaseEntity create(BaseEntity baseEntity) throws Exception;

    void update(BaseEntity baseEntity, BaseEntity baseEntityOld) throws Exception;

    void delete(BaseEntity baseEntity) throws Exception;

    List<?> readAll() throws Exception;

    BaseEntity findById(Integer id) throws Exception;
}
