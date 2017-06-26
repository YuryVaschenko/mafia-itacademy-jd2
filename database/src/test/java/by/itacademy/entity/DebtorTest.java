package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.AddressDAO;
import by.itacademy.dao.DebtorDAO;
import by.itacademy.entity.enums.Frequency;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * Created by Yury V. on 08.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class DebtorTest {

    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private DebtorDAO debtorDAO;

    @Test
    public void debtorSaveAndRetrieveTest() {
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
        addressDAO.saveNew(address);
        debtor.setAddress(address);
        Long id = debtorDAO.saveNew(debtor);
        Debtor retrievedDebtor = debtorDAO.findById(id);

        Assert.assertEquals(debtor, retrievedDebtor);
    }

    @Test
    public void debtorDeletingTest() {
        Debtor debtor = new Debtor();
        debtor.setDebtAmount(999);
        debtor.setFrequency(Frequency.ONCE);
        debtor.setExpDate(LocalDate.now());
        Long id = debtorDAO.saveNew(debtor);
        debtorDAO.delete(debtor);
        Debtor retrievedDebtor = debtorDAO.findById(id);

        Assert.assertNull(retrievedDebtor);
    }

}
