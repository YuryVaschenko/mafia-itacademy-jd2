package by.itacademy.entity;

import by.itacademy.entity.enums.MemberStatus;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * Created by Yury V. on 15.06.17.
 */

public class MemberTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void memberSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        /*NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Gektor");
        Clan clan = new Clan();
        clan.setName("Carioka");
        session.save(clan);
        MemberForTest member = new MemberForTest();
        //member.setSpecialization(Specialization.HACKER);
        member.setNameDetails(nameDetails);
        member.setClan(clan);
        member.setMemberStatus(MemberStatus.AVAILABLE);
        Long id = (Long) session.save(member);

        MemberForTest retrievedMember = session.get(MemberForTest.class, id);
        */
        session.getTransaction().commit();
        session.close();

        // Assert.assertEquals(member, retrievedMember);

    }

    @Test
    public void memberDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        session.getTransaction().commit();
        session.close();

        //Assert.assertEquals(null, retrievedClan);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

   /* @Entity
    @Table (name = "members")
    @NoArgsConstructor
    @ToString
    private class MemberForTest extends Member {

    }*/

}
