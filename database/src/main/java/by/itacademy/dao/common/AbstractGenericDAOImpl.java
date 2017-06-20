package by.itacademy.dao.common;

import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */

public abstract class AbstractGenericDAOImpl<T extends DAOEntity> implements GenericDAO<T> {

    private Class<T> entityClass;
    private Session session;

    @SuppressWarnings("WeakerAccess")
    public AbstractGenericDAOImpl() {
        //noinspection unchecked
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public Session getSession() {
        if (session == null) {
            return HibernateUtil.getSessionFactory().getCurrentSession();
        }
        return session;
    }

    public Long saveNew(T t) {
        return (Long) getSession().save(t);
    }

    public T findById(Long id) {
        return getSession().get(entityClass, id);
    }

    public void update(T t) {
        getSession().merge(t);
    }

    public void delete(T t) {
        getSession().delete(t);
    }

    public List<T> findAll() {
        String hql = String.format("SELECT L FROM %s L", entityClass.getSimpleName());
        Query query = getSession().createQuery(hql);
        //noinspection unchecked
        return (List<T>) query.getResultList();
    }

}
