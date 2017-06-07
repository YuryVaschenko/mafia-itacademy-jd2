package by.itacademy.dao;

import by.itacademy.entity.Location;
import by.itacademy.utils.HibernateUtil;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */
public class DaoImplementation implements GenericDAO<Location, ID> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Long saveNew(Location location) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(location);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    @Override
    public Location findById(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Location location = session.get(Location.class, id);
        session.getTransaction().commit();
        session.close();
        return location;
    }

    @Override
    public void update(Location location) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(location);
        session.getTransaction().commit();
        session.close();
    }


    @Override
    public void delete(Location location) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(location);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Location> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT L FROM Location L";
        Query query = session.createQuery(hql);
        List<Location> locationsList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (locationsList.size() > 0) {
            return locationsList;
        } else {
            return new ArrayList<>();
        }
    }

    public Location findByName(String name) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "SELECT L FROM Location L WHERE L.name='Minsk'";
        Query query = session.createQuery(hql);
        List<Location> locationsList = query.getResultList();
        session.getTransaction().commit();
        session.close();
        if (locationsList.size() > 0) {
            return locationsList.get(0);
        } else {
            return null;
        }
    }

}
