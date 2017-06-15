package by.itacademy.entity;

import by.itacademy.entity.enums.MemberStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by Yury V. on 15.06.17.
 */
public class AuthorityTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void authoritySaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carioka");
        session.save(clan);
        Authority authority = new Authority();
        authority.setIsBoss(true);
        authority.setClan(clan);
        authority.setMemberStatus(MemberStatus.AVAILABLE);

        Long id = (Long) session.save(authority);
        Authority retrievedAuthority = session.get(Authority.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(authority, retrievedAuthority);
    }

    @Test
    public void authorityDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Clan clan = new Clan();
        clan.setName("Carioka");
        session.save(clan);
        Authority authority = new Authority();
        authority.setIsBoss(true);
        authority.setClan(clan);
        authority.setMemberStatus(MemberStatus.IN_JAIL);

        Long id = (Long) session.save(authority);
        session.delete(authority);
        Authority retrievedAuthority = session.get(Authority.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertNull(retrievedAuthority);
    }
    
    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }


}
