package net.smart4life.hcounter.dao;

import java.util.List;

import net.smart4life.hcounter.datamodel.entity.BaseEntity;

/**
 * Created by roman on 21.09.2015.
 */
public interface BaseDAO <T extends BaseEntity> {

    Class<T> getEntityClass();

    T insert(T entity);

    T update(T entity);

    void delete(T entity);

    T findById(long id);

    List<T> findAll();
}
