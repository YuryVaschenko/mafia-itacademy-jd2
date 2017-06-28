package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.Clan;
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
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class ClanDAOTest extends GenericDAOTest<Clan> {

    @Autowired
    private ClanDAO clanDAO;

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

    @Override
    protected GenericDAO<Clan> getDao() {
        return clanDAO;
    }

    @Override
    protected Clan[] getModel() {
        return clans;
    }
}
