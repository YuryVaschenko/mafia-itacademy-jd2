package by.itacademy.dao;

import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;

import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */

public abstract class AbstractGenericDAO<T extends DAOEntity> implements GenericDAO<T> {

    private Class<T> entityClass;
    private Session session;

    {
        //noinspection unchecked
        entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    AbstractGenericDAO() {
        this.session = HibernateUtil.getSessionFactory().getCurrentSession();
    }

    AbstractGenericDAO(Session session) {
        this.session = session;
    }

    public Long saveNew(T t) {
        return (Long) session.save(t);
    }

    public T findById(Long id) {
        return session.get(entityClass, id);
    }

    public void update(T t) {
        session.merge(t);
    }

    public void delete(T t) {
        session.delete(t);
    }

    public List<T> findAll() {
        String hql = "SELECT L FROM " + entityClass.getSimpleName() + " L";
        Query query = session.createQuery(hql);

        //noinspection unchecked
        return (List<T>) query.getResultList();
    }

}
