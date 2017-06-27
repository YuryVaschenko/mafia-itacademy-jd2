package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.entity.Address;
import by.itacademy.entity.Location;
import org.hibernate.ObjectNotFoundException;
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
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class AddressDAOTest extends GenericDAOTest<Address> {

    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private LocationDAO locationDAO;

    private Address[] addresses;

    @Before
    public void entitiesInit() {
        addresses = new Address[2];
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

    @Override
    protected GenericDAO<Address> getDao() {
        return addressDAO;
    }

    @Override
    protected Address[] getModel() {
        return addresses;
    }

    @Test
    public void locationThroughAddressSaveAndRetrieveTest() {
        Address address = addresses[0];
        Location location = new Location();
        location.setLatitude("77.7777");
        location.setLongitude("55.5555");
        address.setLocation(location);
        location.setAddress(address);
        Long id = addressDAO.saveNew(address);
        Address retrievedAddress = addressDAO.findById(id);

        assertEquals(address.getLocation(), retrievedAddress.getLocation());
    }

    @Test(expected = ObjectNotFoundException.class)
    public void cascadeDeletingAddressAndLocationTest() {
        Address address = addresses[0];
        Location location = new Location();
        location.setLongitude("55.555");
        location.setLatitude("55.555");
        address.setLocation(location);
        location.setAddress(address);
        addressDAO.saveNew(address);
        addressDAO.delete(address);

        locationDAO.findById(1L);
    }

    @Test
    public void findAddressesByCityNameTest() {
        Address address = addresses[0];
        String cityName = address.getCity();
        addressDAO.saveNew(address);
        List<Address> retrievedAddresses = addressDAO.findAddressesByCityName(cityName);

        assertEquals(address, retrievedAddresses.get(0));
    }
}
