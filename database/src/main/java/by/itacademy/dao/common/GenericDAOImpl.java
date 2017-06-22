package by.itacademy.dao.common;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;

import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */

public abstract class GenericDAOImpl<T extends DAOEntity> implements GenericDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;
    private final Class<T> entityClass;

    @SuppressWarnings("WeakerAccess")
    public GenericDAOImpl() {
        //noinspection unchecked
        entityClass = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), GenericDAOImpl.class);
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Long saveNew(T t) {
        return (Long) getSessionFactory().getCurrentSession().save(t);
    }

    public T findById(Long id) {
        return getSessionFactory().getCurrentSession().get(entityClass, id);
    }

    public void update(T t) {
        getSessionFactory().getCurrentSession().merge(t);
    }

    public void delete(T t) {
        getSessionFactory().getCurrentSession().delete(t);
    }

    public List<T> findAll() {
        Session session = getSessionFactory().getCurrentSession();
        return session.createQuery(
                "from " + entityClass.getSimpleName(), entityClass)
                .getResultList();
    }

}
