package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.entity.Debtor;
import by.itacademy.entity.NameDetails;
import by.itacademy.entity.enums.Frequency;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Yury V. on 21.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class DebtorDAOTest {

    @Autowired
    private DebtorDAO debtorDAO;

    @Test
    public void findLimitedDebtorsOrderedByExpDateTest() {

        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Sirocco");
        nameDetails.setLastName("Cocube");

        Debtor firstDebtor = new Debtor();
        firstDebtor.setDebtAmount(1000);
        firstDebtor.setFrequency(Frequency.ONCE);
        firstDebtor.setNameDetails(nameDetails);
        firstDebtor.setExpDate(LocalDate.now().minusDays(3));
        debtorDAO.saveNew(firstDebtor);

        Debtor secondDebtor = new Debtor();
        secondDebtor.setDebtAmount(5000);
        secondDebtor.setFrequency(Frequency.MONTHLY);
        secondDebtor.setExpDate(LocalDate.now().minusMonths(1));
        debtorDAO.saveNew(secondDebtor);


        Debtor thirdDebtor = new Debtor();
        thirdDebtor.setDebtAmount(3000);
        thirdDebtor.setFrequency(Frequency.DECADE);
        thirdDebtor.setExpDate(LocalDate.now().plusDays(1));
        debtorDAO.saveNew(thirdDebtor);

        Debtor fourthDebtor = new Debtor();
        fourthDebtor.setDebtAmount(5000);
        fourthDebtor.setFrequency(Frequency.MONTHLY);
        fourthDebtor.setExpDate(LocalDate.now().plusWeeks(1));
        debtorDAO.saveNew(fourthDebtor);

        int limit = 3;
        Object[] debtors = {secondDebtor, firstDebtor, thirdDebtor};
        List<Debtor> retrievedDebtors = debtorDAO.findLimitedDebtorsOrderedByExpDate(limit);

        Assert.assertEquals(limit, retrievedDebtors.size());
        Assert.assertArrayEquals(debtors, retrievedDebtors.toArray());
    }

    @Test
    public void findOverdueDebtorsTest() {

    }


}
