package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Caporegime;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Group;
import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Role;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Yury V. on 28.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDbConfig.class)
@Transactional
public class CaporegimeDAOTest extends GenericDAOTest<Caporegime> {

    @Autowired
    private CaporegimeDAO caporegimeDAO;
    @Autowired
    private AccountUserDAO accountUserDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private ClanDAO clanDAO;

    private Caporegime[] caporegimes = new Caporegime[2];

    @Before
    public void entitiesInit() {
        Clan clan = new Clan();
        clan.setName("Angelo La Barbera");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        groupDAO.saveNew(group);
        AccountUser user = new AccountUser();
        user.setLogin("login");
        user.setPassword("pass");
        user.setRole(Role.CAPOREGIME);
        accountUserDAO.saveNew(user);
        AccountUser nextUser = new AccountUser();
        nextUser.setLogin("log");
        nextUser.setPassword("password");
        nextUser.setRole(Role.SOLDIER);
        accountUserDAO.saveNew(nextUser);

        caporegimes[0] = new Caporegime();
        caporegimes[0].setClan(clan);
        caporegimes[0].setGroup(group);
        caporegimes[0].setEmail("xxx@xxx.com");
        caporegimes[0].setMemberStatus(MemberStatus.AVAILABLE);
        caporegimes[0].setAccountUser(user);

        caporegimes[1] = new Caporegime();
        caporegimes[1].setClan(clan);
        caporegimes[1].setGroup(group);
        caporegimes[1].setEmail("yyy@yyy.net");
        caporegimes[1].setMemberStatus(MemberStatus.IN_HOSPITAL);
        caporegimes[1].setAccountUser(nextUser);
    }

    @Test
    public void findByAccountUserLoginTest() {
        caporegimeDAO.saveNew(caporegimes[0]);
        caporegimeDAO.saveNew(caporegimes[1]);

        Caporegime caporegime = caporegimeDAO.findByAccountUserLogin("login");
        assertNotNull(caporegime);
    }

    @Override
    protected GenericDAO<Caporegime> getDao() {
        return caporegimeDAO;
    }

    @Override
    protected Caporegime[] getModel() {
        return caporegimes;
    }
}
