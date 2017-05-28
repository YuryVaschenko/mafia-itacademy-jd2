package by.itacademy.dao;

import by.itacademy.pojos.Location;
import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by Yury V. on 28.05.17.
 */
public class DaoImplementation implements DaoInterface<Location> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public Long saveNew(Location location) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Long id = (Long) session.save(location);
        session.getTransaction().commit();
        session.close();
        return id;
    }

    public Location get(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Location location = session.get(Location.class, id);
        session.getTransaction().commit();
        session.close();
        return location;
    }

    public void update(Location location) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.merge(location);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(Location location) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(location);
        session.getTransaction().commit();
        session.close();
    }

}
