package by.itacademy.entity;

import by.itacademy.entity.enums.AffairStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yury V. on 16.06.17.
 */
public class AffairTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void affairSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);

        Long id = (Long) session.save(affair);

        Affair retrievedAffair = session.load(Affair.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(affair, retrievedAffair);
    }

    @Test
    public void affairDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);

        Long id = (Long) session.save(affair);
        session.delete(affair);

        Affair retrievedAffair = session.get(Affair.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertNull(retrievedAffair);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }


}
