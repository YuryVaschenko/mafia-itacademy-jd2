package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.ClanDAO;
import by.itacademy.dao.GroupDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 08.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class GroupTest {

    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private GroupDAO groupDAO;

    @Test
    public void groupSaveAndRetrieveTest() {
        Clan clan = new Clan();
        clan.setName("Carleone");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        Long id = groupDAO.saveNew(group);

        Group retrievedGroup = groupDAO.findById(id);

        Assert.assertEquals(group, retrievedGroup);
    }

    @Test
    public void groupDeletingTest() {
        Clan clan = new Clan();
        clan.setName("Carleone");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        Long id = groupDAO.saveNew(group);

        groupDAO.delete(group);
        Group retrievedGroup = groupDAO.findById(id);

        Assert.assertNull(retrievedGroup);
    }

}
