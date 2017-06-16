package by.itacademy.entity;

import by.itacademy.entity.enums.BlackListStatus;
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

public class BlackListTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void blackListSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Giovanni");
        nameDetails.setLastName("Falcone");

        BlackList blackList = new BlackList();
        blackList.setNameDetails(nameDetails);
        blackList.setBlackListStatus(BlackListStatus.DEAD);

        Long id = (Long) session.save(blackList);

        BlackList retrievedBlackList = session.load(BlackList.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(blackList, retrievedBlackList);
    }

    @Test
    public void blackListDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Giovanni");
        nameDetails.setLastName("Falcone");

        BlackList blackList = new BlackList();
        blackList.setNameDetails(nameDetails);
        blackList.setBlackListStatus(BlackListStatus.DEAD);

        Long id = (Long) session.save(blackList);

        session.delete(blackList);
        BlackList retrievedBlackList = session.get(BlackList.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertNull(retrievedBlackList);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

}
