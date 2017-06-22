package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.entity.Clan;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 20.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class ClanDAOTest {

    @Autowired
    private ClanDAO clanDAO;

    @Test
    public void findClanByNameTest() {

        Clan checkForNullClan = clanDAO.findClanByName("Null");

        String clanName = "Carleone";
        Clan clan = new Clan();
        clan.setName(clanName);

        clanDAO.saveNew(clan);
        Clan retrievedClan = clanDAO.findClanByName(clanName);

        Assert.assertNull(checkForNullClan);
        Assert.assertEquals(clan, retrievedClan);
    }

}
