package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Authority;
import by.itacademy.entity.Clan;
import by.itacademy.entity.NameDetails;
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
public class AuthorityDAOTest extends GenericDAOTest<Authority> {

    @Autowired
    private AuthorityDAO authorityDAO;
    @Autowired
    private AccountUserDAO accountUserDAO;
    @Autowired
    private ClanDAO clanDAO;

    private Authority[] authorities = new Authority[2];

    @Before
    public void entitiesInit() {
        Clan clan = new Clan();
        clan.setName("Corleonesi");
        clanDAO.saveNew(clan);

        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Luciano");
        nameDetails.setLastName("Leggio");

        AccountUser user = new AccountUser();
        user.setLogin("login");
        user.setPassword("pass");
        user.setRole(Role.CAPOREGIME);
        accountUserDAO.saveNew(user);
        authorities[0] = new Authority();
        authorities[0].setNameDetails(nameDetails);
        authorities[0].setBoss(true);
        authorities[0].setClan(clan);
        authorities[0].setMemberStatus(MemberStatus.AVAILABLE);
        authorities[0].setAccountUser(user);

        AccountUser nextUser = new AccountUser();
        nextUser.setLogin("log");
        nextUser.setPassword("password");
        nextUser.setRole(Role.SOLDIER);
        accountUserDAO.saveNew(nextUser);
        authorities[1] = new Authority();
        authorities[1].setBoss(false);
        authorities[1].setClan(clan);
        authorities[1].setMemberStatus(MemberStatus.IN_JAIL);
        authorities[1].setAccountUser(nextUser);
    }

    @Test
    public void findByAccountUserLoginTest() {
        authorityDAO.saveNew(authorities[0]);
        authorityDAO.saveNew(authorities[1]);

        Authority retrievedAuthority = authorityDAO.findByAccountUserLogin("login");

        assertNotNull(retrievedAuthority);
    }

    @Override
    protected GenericDAO<Authority> getDao() {
        return authorityDAO;
    }

    @Override
    protected Authority[] getModel() {
        return authorities;
    }
}
