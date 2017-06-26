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
public class LocationTest {

    @Autowired
    private LocationDAO locationDAO;
    @Autowired
    private AddressDAO addressDAO;

    @Test
    public void locationSaveAndRetrieveTest() {
        Location location = new Location();
        location.setLatitude("55.55");
        location.setLongitude("55.55");
        Long id = locationDAO.saveNew(location);
        Location retrievedLocation = locationDAO.findById(id);

        Assert.assertEquals(location, retrievedLocation);
    }

    @Test
    public void AddressThroughLocationSaveAndRetrieveTest() {
        Location location = new Location();
        location.setLatitude("77.7777");
        location.setLongitude("55.5555");
        Address address = new Address();
        location.setAddress(address);
        address.setLocation(location);
        Long id = locationDAO.saveNew(location);
        Location retrievedLocation = locationDAO.findById(id);

        Assert.assertEquals(location.getAddress(), retrievedLocation.getAddress());
    }

    @Test
    public void cascadeDeletingLocationAndAddressTest() {
        Location location = new Location();
        location.setLongitude("55.555");
        location.setLatitude("55.555");
        Address address = new Address();
        address.setCountry("Belarus");
        location.setAddress(address);
        address.setLocation(location);
        Long id = locationDAO.saveNew(location);
        locationDAO.delete(location);

        Location retrievedLocation = locationDAO.findById(id);
        Address retrievedAddress = addressDAO.findById(1L);

        Assert.assertNull(retrievedAddress);
        Assert.assertNull(retrievedLocation);
    }

}
