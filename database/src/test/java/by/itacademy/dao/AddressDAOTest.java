package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.entity.Address;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class AddressDAOTest {

    @Autowired
    private AddressDAO addressDAO;

    @Test
    public void findAddressesByCityNameTest() {

        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        addressDAO.saveNew(address);

        List<Address> retrievedAddresses = addressDAO.findAddressesByCityName("Minsk");

        Assert.assertEquals(address, retrievedAddresses.get(0));
    }


}
