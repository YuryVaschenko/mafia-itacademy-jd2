package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Caporegime;
import by.itacademy.entity.Clan;
import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yury V. on 20.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDbConfig.class})
@Transactional
public class ClanDAOTest extends GenericDAOTest<Clan> {

    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private CaporegimeDAO caporegimeDAO;
    @Autowired
    private AccountUserDAO accountUserDAO;

    private Clan[] clans = new Clan[2];

    @Before
    public void entitiesInit() {

        clans[0] = new Clan();
        clans[0].setName("Corleone");

        clans[1] = new Clan();
        clans[1].setName("Benedetti");

    }

    @Test
    public void findClanByNameTest() {
        String clanName = clans[0].getName();
        clanDAO.saveNew(clans[0]);
        Clan retrievedClan = clanDAO.findClanByName(clanName);

        assertEquals(clans[0], retrievedClan);
    }

    @Test
    public void findClanIdByAccountUserLogin() {

        clanDAO.saveNew(clans[0]);
        clanDAO.saveNew(clans[1]);

        String login = "login";
        AccountUser accountUser = new AccountUser(login, "pass", Role.CAPOREGIME);
        accountUserDAO.saveNew(accountUser);

        Caporegime caporegime = new Caporegime();
        caporegime.setMemberStatus(MemberStatus.AVAILABLE);
        caporegime.setAccountUser(accountUser);
        caporegime.setClan(clans[0]);
        caporegime.setEmail("xxx@yyy.zz");
        caporegimeDAO.saveNew(caporegime);

        Long retrievedClanId = clanDAO.findClanIdByAccountUserLogin(login);

        assertEquals(clans[0].getId(), retrievedClanId);
    }

    @Override
    protected GenericDAO<Clan> getDao() {
        return clanDAO;
    }

    @Override
    protected Clan[] getModel() {
        return clans;
    }
}
