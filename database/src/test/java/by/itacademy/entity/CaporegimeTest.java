package by.itacademy.entity;

import by.itacademy.entity.enums.MemberStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yury V. on 15.06.17.
 */
public class CaporegimeTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void soldierSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carioka");
        session.save(clan);
        Group group = new Group();
        group.setClan(clan);
        session.save(group);
        Caporegime caporegime = new Caporegime();
        caporegime.setClan(clan);
        caporegime.setGroup(group);
        caporegime.setEmail("xxx@xxx.com");
        caporegime.setMemberStatus(MemberStatus.DEAD);

        Long id = (Long) session.save(caporegime);

        Caporegime retrievedCaporegime = session.get(Caporegime.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(caporegime, retrievedCaporegime);
    }

    @Test
    public void caporegimeDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carioka");
        session.save(clan);
        Group group = new Group();
        group.setClan(clan);
        session.save(group);
        Caporegime caporegime = new Caporegime();
        caporegime.setClan(clan);
        caporegime.setGroup(group);
        caporegime.setEmail("xxx@xxx.com");
        caporegime.setMemberStatus(MemberStatus.IN_HOSPITAL);

        Long id = (Long) session.save(caporegime);
        session.delete(caporegime);
        Caporegime retrievedCaporegime = session.get(Caporegime.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertNull(retrievedCaporegime);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }


}
