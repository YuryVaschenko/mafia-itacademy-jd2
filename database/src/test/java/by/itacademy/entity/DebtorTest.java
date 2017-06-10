package by.itacademy.entity;

import by.itacademy.entity.enums.Frequency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

/**
 * Created by Yury V. on 08.06.17.
 */

public class DebtorTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void debtorSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Debtor debtor = new Debtor();
        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Joseppe");
        nameDetails.setLastName("Verdi");
        nameDetails.setMiddleName("Maurinio");
        nameDetails.setNickName("LittleJoe");
        debtor.setNameDetails(nameDetails);
        debtor.setDebtAmount(100000);
        debtor.setExpDate(LocalDate.now());
        debtor.setFrequency(Frequency.MONTHLY);
        Address address = new Address();
        address.setCountry("Belarus");
        session.save(address);
        debtor.setAddress(address);
        Long id = (Long) session.save(debtor);
        Debtor retrievedDebtor = session.load(Debtor.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(debtor, retrievedDebtor);

    }

    @Test
    public void debtorDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Debtor debtor = new Debtor();
        debtor.setDebtAmount(999);
        debtor.setFrequency(Frequency.ONCE);
        Long id = (Long) session.save(debtor);
        session.delete(debtor);
        Debtor retrievedDebtor = session.get(Debtor.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(null, retrievedDebtor);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

}
