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

public class ClanTest {


    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void clanSaveAndRetrieveTest(){
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carleone");
        Long id = (Long) session.save(clan);
        Clan retrievedClan = session.load(Clan.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(clan, retrievedClan);

    }@Test
    public void clanDeletingTest(){
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carleone");
        Long id = (Long) session.save(clan);
        session.delete(clan);

        Clan retrievedClan = session.get(Clan.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(null, retrievedClan);
    }
    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

}
