package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Group;
import by.itacademy.entity.NameDetails;
import by.itacademy.entity.Soldier;
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
 * Created by Yury V. on 28.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDbConfig.class})
@Transactional
public class SoldierDAOTest extends GenericDAOTest<Soldier> {

    @Autowired
    private SoldierDAO soldierDAO;
    @Autowired
    private AccountUserDAO accountUserDAO;
    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private GroupDAO groupDAO;

    private Soldier[] soldiers = new Soldier[2];

    @Before
    public void entitiesInit() {
        Clan clan = new Clan();
        clan.setName("San Lorenzo");
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

        soldiers[0] = new Soldier();
        soldiers[0].setClan(clan);
        soldiers[0].setSpecialization(Specialization.PIMP);
        soldiers[0].setGroup(group);
        soldiers[0].setMemberStatus(MemberStatus.AVAILABLE);
        soldiers[0].setAccountUser(user);

        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Grisha");
        nameDetails.setLastName("Kurkul");
        nameDetails.setNickName("Rushed");

        soldiers[1] = new Soldier();
        soldiers[1].setNameDetails(nameDetails);
        soldiers[1].setClan(clan);
        soldiers[1].setSpecialization(Specialization.PIMP);
        soldiers[1].setGroup(group);
        soldiers[1].setMemberStatus(MemberStatus.AVAILABLE);
        soldiers[1].setAccountUser(nextUser);
    }

    @Override
    protected GenericDAO<Soldier> getDao() {
        return soldierDAO;
    }

    @Override
    protected Soldier[] getModel() {
        return soldiers;
    }
}
