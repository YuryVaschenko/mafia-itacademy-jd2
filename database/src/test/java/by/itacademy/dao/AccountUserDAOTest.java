package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Caporegime;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Group;
import by.itacademy.entity.Soldier;
import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Role;
import by.itacademy.entity.enums.Specialization;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Yury V. on 01.07.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDbConfig.class)
@Transactional
public class AccountUserDAOTest extends GenericDAOTest<AccountUser> {

    @Autowired
    private AccountUserDAO accountUserDAO;
    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private SoldierDAO soldierDAO;
    @Autowired
    private CaporegimeDAO caporegimeDAO;

    private AccountUser[] accountUsers = new AccountUser[2];

    @Before
    public void entitiesInit() {
        Clan clan = new Clan();
        clan.setName("San Lorenzo");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        groupDAO.saveNew(group);
        Soldier soldier = new Soldier();
        soldier.setClan(clan);
        soldier.setSpecialization(Specialization.PIMP);
        soldier.setGroup(group);
        soldier.setMemberStatus(MemberStatus.AVAILABLE);
        soldierDAO.saveNew(soldier);
        accountUsers[0] = new AccountUser();
        accountUsers[0].setLogin("login");
        accountUsers[0].setPassword("password");
        accountUsers[0].setRole(Role.SOLDIER);
        accountUsers[0].setMember(soldier);

        Caporegime caporegime = new Caporegime();
        caporegime.setClan(clan);
        caporegime.setGroup(group);
        caporegime.setEmail("xxx@xxx.com");
        caporegime.setMemberStatus(MemberStatus.AVAILABLE);
        caporegimeDAO.saveNew(caporegime);
        accountUsers[1] = new AccountUser();
        accountUsers[1].setLogin("log");
        accountUsers[1].setPassword("pass");
        accountUsers[1].setRole(Role.CAPOREGIME);
        accountUsers[1].setMember(caporegime);
    }

    @Test
    public void findAccountUserByLogin() {
        accountUserDAO.saveNew(accountUsers[0]);
        AccountUser retrievedAccountUser = accountUserDAO.findAccountUserByLogin("login");

        assertNotNull(retrievedAccountUser);
    }

    @Test
    public void findAbsentAccountUserByLogin() {
        AccountUser retrievedAccountUser = accountUserDAO.findAccountUserByLogin("login");

        assertNull(retrievedAccountUser);
    }

    @Override
    protected GenericDAO<AccountUser> getDao() {
        return accountUserDAO;
    }

    @Override
    protected AccountUser[] getModel() {
        return accountUsers;
    }
}
