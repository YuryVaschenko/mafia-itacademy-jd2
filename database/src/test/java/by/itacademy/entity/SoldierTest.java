package by.itacademy.entity;

import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Specialization;
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
public class SoldierTest {

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
        Soldier soldier = new Soldier();
        soldier.setClan(clan);
        soldier.setSpecialization(Specialization.PIMP);
        soldier.setGroup(group);
        soldier.setMemberStatus(MemberStatus.AVAILABLE);
        Long id = (Long) session.save(soldier);

        Soldier retrievedSoldier = session.get(Soldier.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(soldier, retrievedSoldier);
    }

    @Test
    public void soldierDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carioka");
        session.save(clan);
        Group group = new Group();
        group.setClan(clan);
        session.save(group);
        Soldier soldier = new Soldier();
        soldier.setClan(clan);
        soldier.setSpecialization(Specialization.PIMP);
        soldier.setGroup(group);
        soldier.setMemberStatus(MemberStatus.AVAILABLE);

        Long id = (Long) session.save(soldier);
        session.delete(soldier);
        Soldier retrievedSoldier = session.get(Soldier.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertNull(retrievedSoldier);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }


}
