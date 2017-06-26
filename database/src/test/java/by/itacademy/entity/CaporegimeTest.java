package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.CaporegimeDAO;
import by.itacademy.dao.ClanDAO;
import by.itacademy.dao.GroupDAO;
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
public class CaporegimeTest {

    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private CaporegimeDAO caporegimeDAO;


    @Test
    public void soldierSaveAndRetrieveTest() {
        Clan clan = new Clan();
        clan.setName("Carioka");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        groupDAO.saveNew(group);
        Caporegime caporegime = new Caporegime();
        caporegime.setClan(clan);
        caporegime.setGroup(group);
        caporegime.setEmail("xxx@xxx.com");
        caporegime.setMemberStatus(MemberStatus.DEAD);

        Long id = caporegimeDAO.saveNew(caporegime);

        Caporegime retrievedCaporegime = caporegimeDAO.findById(id);

        Assert.assertEquals(caporegime, retrievedCaporegime);
    }

    @Test
    public void caporegimeDeletingTest() {
        Clan clan = new Clan();
        clan.setName("Carioka");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        groupDAO.saveNew(group);
        Caporegime caporegime = new Caporegime();
        caporegime.setClan(clan);
        caporegime.setGroup(group);
        caporegime.setEmail("xxx@xxx.com");
        caporegime.setMemberStatus(MemberStatus.IN_HOSPITAL);

        Long id = caporegimeDAO.saveNew(caporegime);
        caporegimeDAO.delete(caporegime);
        Caporegime retrievedCaporegime = caporegimeDAO.findById(id);

        Assert.assertNull(retrievedCaporegime);
    }

}
