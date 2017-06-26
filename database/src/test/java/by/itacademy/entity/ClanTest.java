package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.AddressDAO;
import by.itacademy.dao.ClanDAO;
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
public class ClanTest {

    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private AddressDAO addressDAO;

    @Test
    public void clanSaveAndRetrieveTest() {
        Clan clan = new Clan();
        clan.setName("Carleone");
        Long id = clanDAO.saveNew(clan);
        Clan retrievedClan = clanDAO.findById(id);

        Assert.assertEquals(clan, retrievedClan);
    }

    @Test
    public void clanDeletingTest() {
        Clan clan = new Clan();
        clan.setName("Carleone");
        Long id = clanDAO.saveNew(clan);
        clanDAO.delete(clan);

        Clan retrievedClan = clanDAO.findById(id);

        Assert.assertNull(retrievedClan);
    }

    @Test
    public void cascadeDeletingClanAndAddressTest() {
        Address address = new Address();
        address.setCountry("Belarus");
        Clan clan = new Clan();
        clan.setName("Carleone");
        clan.setAddress(address);
        Long id = clanDAO.saveNew(clan);
        clanDAO.delete(clan);

        Clan retrievedClan = clanDAO.findById(id);
        Address retrievedAddress = addressDAO.findById(1L);

        Assert.assertNull(retrievedClan);
        Assert.assertNull(retrievedAddress);
    }

}
