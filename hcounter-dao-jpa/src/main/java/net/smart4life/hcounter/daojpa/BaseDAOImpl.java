package net.smart4life.hcounter.daojpa;

import net.smart4life.hcounter.dao.BaseDAO;
import net.smart4life.hcounter.datamodel.entity.BaseEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by roman on 21.09.2015.
 */
public abstract class BaseDAOImpl<T extends BaseEntity> implements BaseDAO<T> {

    @PersistenceContext
    protected EntityManager em;

    @Override
    public T insert(T entity) {
        em.persist(entity);

        return entity;
    }

    @Override
    public T update(T entity) {
        return em.merge(entity);
    }

    @Override
    public void delete(T entity) {
        em.remove(entity);
    }

    @Override
    public T findById(long id) {
        return em.find(getEntityClass(), id);
    }

    @Override
    public List<T> findAll() {
        return em.createQuery("select o from "+getEntityClass().getSimpleName()+" o ", getEntityClass()).getResultList();
    }
}
