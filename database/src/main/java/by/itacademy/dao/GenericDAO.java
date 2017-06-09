package by.itacademy.dao;

import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */

public abstract class GenericDAO<T> {

    private Class<T> clazz;

    GenericDAO(Class<T> parametrizedClazz) {
        clazz = parametrizedClazz;
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
        T t = session.get(clazz, id);
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
        String hql = "SELECT L FROM " + clazz.getSimpleName() + " L";
        Query query = session.createQuery(hql);
        @SuppressWarnings("unchecked")
        List<T> resultList = (List<T>) query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (resultList.size() > 0) {
            return resultList;
        } else {
            return new ArrayList<>();
        }
    }

}
