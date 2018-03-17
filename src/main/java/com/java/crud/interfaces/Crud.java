package com.java.crud.interfaces;

import com.java.crud.model.Entity;

import java.util.List;

public interface Crud  {

    Entity create(Entity entity) throws Exception;

    void update(Entity entity, Entity entityOld) throws Exception;

    void delete(Entity entity) throws Exception;

    List<?> readAll() throws Exception;

    Entity findById(Integer id) throws Exception;
}
