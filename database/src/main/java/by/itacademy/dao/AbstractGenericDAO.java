package by.itacademy.dao;

import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */

public abstract class AbstractGenericDAO<T extends DAOEntity> implements GenericDAO<T> {

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    AbstractGenericDAO() {
        this.entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Long saveNew(T t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(t);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public T findById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T t = session.get(entityClass, id);
        session.getTransaction().commit();
        session.close();
        return t;
    }

    public void update(T t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(t);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(T t) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(t);
        session.getTransaction().commit();
        session.close();
    }

    public List<T> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT L FROM " + entityClass.getSimpleName() + " L";
        Query query = session.createQuery(hql);
        @SuppressWarnings("unchecked")
        List<T> resultList = (List<T>) query.getResultList();
        session.getTransaction().commit();
        session.close();

        return resultList;
    }

}
