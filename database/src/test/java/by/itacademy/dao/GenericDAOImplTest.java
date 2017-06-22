package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.entity.Address;
import by.itacademy.entity.Location;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 10.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class GenericDAOImplTest {

    @Autowired
    private LocationDAO locationDAO;
    @Autowired
    private AddressDAO addressDAO;

    @Test
    public void saveNewAndFindByIdTest() {

        Location location = new Location();
        location.setLatitude("55.55");
        location.setLongitude("55.55");
        Long id_location = locationDAO.saveNew(location);

        Location secondLocation = new Location();
        secondLocation.setLatitude("77.77");
        secondLocation.setLongitude("77.77");
        Long id_secondLocation = locationDAO.saveNew(secondLocation);

        Address address = new Address();
        address.setCountry("Belarus");
        Long id_address = addressDAO.saveNew(address);

        Location retrievedLocation = locationDAO.findById(id_location);
        Location retrievedSecondLocation = locationDAO.findById(id_secondLocation);
        Address retrievedAddress = addressDAO.findById(id_address);


        Assert.assertEquals(location, retrievedLocation);
        Assert.assertEquals(secondLocation, retrievedSecondLocation);
        Assert.assertEquals(address, retrievedAddress);
    }

}
