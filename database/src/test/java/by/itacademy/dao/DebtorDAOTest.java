package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Debtor;
import by.itacademy.entity.NameDetails;
import by.itacademy.entity.enums.Frequency;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Yury V. on 21.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDbConfig.class})
@Transactional
public class DebtorDAOTest extends GenericDAOTest<Debtor> {

    @Autowired
    private DebtorDAO debtorDAO;

    @Autowired
    private ClanDAO clanDAO;

    private Debtor[] debtors = new Debtor[4];

    @Before
    public void entitiesInit() {
        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Sirocco");
        nameDetails.setLastName("Cocube");
        nameDetails.setNickName("LittleJoe");

        Clan clan = new Clan();
        clan.setName("Benedetti");
        clanDAO.saveNew(clan);

        debtors[0] = new Debtor();
        debtors[0].setDebtAmount(1000L);
        debtors[0].setFrequency(Frequency.ONCE);
        debtors[0].setNameDetails(nameDetails);
        debtors[0].setExpDate(LocalDate.now().minusDays(3));
        debtors[0].setClan(clan);

        debtors[1] = new Debtor();
        debtors[1].setDebtAmount(5000L);
        debtors[1].setFrequency(Frequency.MONTHLY);
        debtors[1].setExpDate(LocalDate.now().minusMonths(1));
        debtors[1].setClan(clan);

        debtors[2] = new Debtor();
        debtors[2].setDebtAmount(3000L);
        debtors[2].setFrequency(Frequency.DECADE);
        debtors[2].setExpDate(LocalDate.now().plusDays(1));
        debtors[2].setClan(clan);

        debtors[3] = new Debtor();
        debtors[3].setDebtAmount(5000L);
        debtors[3].setFrequency(Frequency.MONTHLY);
        debtors[3].setExpDate(LocalDate.now().plusWeeks(1));
        debtors[3].setClan(clan);
    }

    @Test
    public void getPaginatedListOfDebtorsTest() {
        debtorDAO.saveNew(debtors[0]);
        debtorDAO.saveNew(debtors[1]);
        debtorDAO.saveNew(debtors[2]);
        debtorDAO.saveNew(debtors[3]);

        List<Debtor> retrievedDebtors = debtorDAO.getPaginatedListOfDebtors(1, 2);
        Debtor[] debtorsToCheck = {debtors[1], debtors[2]};

        assertArrayEquals(debtorsToCheck, retrievedDebtors.toArray());
    }

    @Test
    public void findLimitedDebtorsOrderedByExpDateTest() {
        debtorDAO.saveNew(debtors[0]);
        debtorDAO.saveNew(debtors[1]);
        debtorDAO.saveNew(debtors[2]);
        debtorDAO.saveNew(debtors[3]);

        Debtor[] debtorsToCheck = {debtors[1], debtors[0], debtors[2]};
        List<Debtor> retrievedDebtors = debtorDAO.findLimitedDebtorsOrderedByExpDate(3);

        assertArrayEquals(debtorsToCheck, retrievedDebtors.toArray());
    }

    @Test
    public void findOverdueDebtorsOrderedByExpDateTest() {
        debtorDAO.saveNew(debtors[0]);
        debtorDAO.saveNew(debtors[1]);
        debtorDAO.saveNew(debtors[2]);
        debtorDAO.saveNew(debtors[3]);

        Debtor[] debtorsToCheck = {debtors[1], debtors[0]};
        List<Debtor> overdueDebtors = debtorDAO.findOverdueDebtorsOrderedByExpDate();

        assertArrayEquals(debtorsToCheck, overdueDebtors.toArray());
    }

    @Override
    @Test
    public void updateTest() {
        Debtor debtor = debtors[0];
        Long id = debtorDAO.saveNew(debtor);
        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("NewFirstName");
        nameDetails.setMiddleName("NewMiddleName");
        nameDetails.setLastName("NewLastName");
        nameDetails.setNickName("NewNickname");
        debtor.setNameDetails(nameDetails);
        debtor.setDebtAmount(100000L);
        debtorDAO.update(debtor);
        Debtor retrievedDebtor = debtorDAO.findById(id);

        assertEquals(debtor, retrievedDebtor);
    }

    @Override
    protected GenericDAO<Debtor> getDao() {
        return debtorDAO;
    }

    @Override
    protected Debtor[] getModel() {
        return debtors;
    }
}
