package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.AddressDAO;
import by.itacademy.dao.LocationDAO;
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
public class AddressTest {

    @Autowired
    private AddressDAO addressDAO;
    @Autowired
    private LocationDAO locationDAO;

    @Test
    public void addressSaveAndRetrieveTest() {
        Address address = new Address();
        address.setCountry("Belarus");
        address.setCity("Minsk");
        address.setStreet("Kolasa");
        address.setHouse("55a");
        Long id = addressDAO.saveNew(address);
        Address retrievedAddress = addressDAO.findById(id);

        Assert.assertEquals(address, retrievedAddress);
    }

    @Test
    public void locationThroughAddressSaveAndRetrieveTest() {
        Address address = new Address();
        Location location = new Location();
        location.setLatitude("77.7777");
        location.setLongitude("55.5555");
        address.setLocation(location);
        location.setAddress(address);
        Long id = addressDAO.saveNew(address);
        Address retrievedAddress = addressDAO.findById(id);

        Assert.assertEquals(address.getLocation(), retrievedAddress.getLocation());
    }

    @Test
    public void cascadeDeletingAddressAndLocationTest() {
        Address address = new Address();
        address.setCountry("Belarus");
        Location location = new Location();
        location.setLongitude("55.555");
        location.setLatitude("55.555");
        address.setLocation(location);
        location.setAddress(address);
        Long id = addressDAO.saveNew(address);
        addressDAO.delete(address);

        Address retrievedAddress = addressDAO.findById(id);
        Location retrievedLocation = locationDAO.findById(1L);

        Assert.assertNull(retrievedAddress);
        Assert.assertNull(retrievedLocation);
    }

}
