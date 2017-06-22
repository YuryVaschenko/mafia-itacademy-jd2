package by.itacademy.dao;

import by.itacademy.entity.Debtor;
import by.itacademy.entity.NameDetails;
import by.itacademy.entity.enums.Frequency;
import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Test;

/**
 * Created by Yury V. on 21.06.17.
 */
public class DebtorDAOTest {

    @Test
    public void findLimitedDebtorsOrderedByExpDateTest() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        DebtorDAO debtorDAO = new DebtorDAOImpl();

        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Sirocco");
        nameDetails.setLastName("Cocube");

        Debtor firstDebtor = new Debtor();
        firstDebtor.setDebtAmount(1000);
        firstDebtor.setFrequency(Frequency.ONCE);
        firstDebtor.setNameDetails(nameDetails);
        debtorDAO.saveNew(firstDebtor);

        Debtor secondDebtor = new Debtor();
        secondDebtor.setDebtAmount(5000);
        secondDebtor.setFrequency(Frequency.MONTHLY);
        debtorDAO.saveNew(secondDebtor);



        session.getTransaction().rollback();

        //Assert.assertNull(checkForNullClan);
        //Assert.assertEquals(clan, retrievedClan);
    }


}
