package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.Address;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Yury V. on 19.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestDbConfig.class})
@Transactional
public class AddressDAOTest extends GenericDAOTest<Address> {

    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private LocationDAO locationDAO;

    private Address[] addresses = new Address[2];

    @Before
    public void entitiesInit() {
        addresses[0] = new Address();
        addresses[0].setCountry("Belarus");
        addresses[0].setCity("Minsk");
        addresses[0].setStreet("Kolasa");
        addresses[0].setHouse("55a");

        addresses[1] = new Address();
        addresses[1].setCountry("Russia");
        addresses[1].setCity("Grodno");
        addresses[1].setStreet("Gagarina");
        addresses[1].setHouse("34");
    }

    @Test
    public void findAddressesByCityNameTest() {
        Address address = addresses[0];
        String cityName = address.getCity();
        addressDAO.saveNew(address);
        List<Address> retrievedAddresses = addressDAO.findAddressesByCityName(cityName);

        assertEquals(address, retrievedAddresses.get(0));
    }

    @Override
    protected GenericDAO<Address> getDao() {
        return addressDAO;
    }

    @Override
    protected Address[] getModel() {
        return addresses;
    }
}
