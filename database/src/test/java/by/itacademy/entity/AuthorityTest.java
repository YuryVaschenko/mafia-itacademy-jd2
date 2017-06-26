package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.AuthorityDAO;
import by.itacademy.dao.ClanDAO;
import by.itacademy.entity.enums.MemberStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 15.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class AuthorityTest {

    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private AuthorityDAO authorityDAO;

    @Test
    public void authoritySaveAndRetrieveTest() {
        Clan clan = new Clan();
        clan.setName("Carioka");
        clanDAO.saveNew(clan);
        Authority authority = new Authority();
        authority.setBoss(true);
        authority.setClan(clan);
        authority.setMemberStatus(MemberStatus.AVAILABLE);

        Long id = authorityDAO.saveNew(authority);
        Authority retrievedAuthority = authorityDAO.findById(id);

        Assert.assertEquals(authority, retrievedAuthority);
    }

    @Test
    public void authorityDeletingTest() {
        Clan clan = new Clan();
        clan.setName("Carioka");
        clanDAO.saveNew(clan);
        Authority authority = new Authority();
        authority.setBoss(true);
        authority.setClan(clan);
        authority.setMemberStatus(MemberStatus.IN_JAIL);

        Long id = authorityDAO.saveNew(authority);
        authorityDAO.delete(authority);
        Authority retrievedAuthority = authorityDAO.findById(id);

        Assert.assertNull(retrievedAuthority);
    }

}
