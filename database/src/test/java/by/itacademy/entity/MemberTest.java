package by.itacademy.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void clanSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

/*
        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Gektor");
        Clan clan = new Clan();
        clan.setName("Carioka");
        session.save(clan);
        Member member = new Member(){};
        //member.setSpecialization(Specialization.HACKER);
        member.setNameDetails(nameDetails);
        member.setClan(clan);
        member.setMemberStatus(MemberStatus.AVAILABLE);
        Long id = (Long) session.save(member);

        Member retrievedMember = session.get(Member.class, id);
*/
        session.getTransaction().commit();
        session.close();

//        Assert.assertEquals(member, retrievedMember);

    }

    @Test
    public void clanDeletingTest() {
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


}
