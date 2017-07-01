package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.Caporegime;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Group;
import by.itacademy.entity.Soldier;
import by.itacademy.entity.User;
import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Role;
import by.itacademy.entity.enums.Specialization;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 01.07.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDbConfig.class)
@Transactional
public class UserDAOTest extends GenericDAOTest<User> {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private SoldierDAO soldierDAO;
    @Autowired
    private CaporegimeDAO caporegimeDAO;

    private User[] users = new User[2];

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
        users[0] = new User();
        users[0].setLogin("login");
        users[0].setPassword("password");
        users[0].setRole(Role.SOLDIER);
        users[0].setMember(soldier);

        Caporegime caporegime = new Caporegime();
        caporegime.setClan(clan);
        caporegime.setGroup(group);
        caporegime.setEmail("xxx@xxx.com");
        caporegime.setMemberStatus(MemberStatus.AVAILABLE);
        caporegimeDAO.saveNew(caporegime);
        users[1] = new User();
        users[1].setLogin("log");
        users[1].setPassword("pass");
        users[1].setRole(Role.CAPOREGIME);
        users[1].setMember(caporegime);
    }

    @Override
    protected GenericDAO<User> getDao() {
        return userDAO;
    }

    @Override
    protected User[] getModel() {
        return users;
    }
}
