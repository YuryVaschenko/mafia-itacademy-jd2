package by.itacademy.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yury V. on 08.06.17.
 */

public class GroupTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void groupSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carleone");
        session.save(clan);
        Group group = new Group();
        group.setClan(clan);
        Long id = (Long) session.save(group);

        Group retrievedGroup = session.load(Group.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(group, retrievedGroup);
    }

    @Test
    public void groupDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carleone");
        session.save(clan);
        Group group = new Group();
        group.setClan(clan);
        Long id = (Long) session.save(group);

        session.delete(group);
        Group retrievedGroup = session.get(Group.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertNull(retrievedGroup);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

}
