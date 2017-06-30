package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.Location;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 28.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestDbConfig.class)
@Transactional
public class LocationDAOTest extends GenericDAOTest<Location> {

    @Autowired
    private LocationDAO locationDAO;
    @Autowired
    private AddressDAO addressDAO;

    private Location[] locations = new Location[2];

    @Before
    public void entitiesInit() {
        locations[0] = new Location();
        locations[0].setLatitude("77.7777");
        locations[0].setLongitude("55.5555");

        locations[1] = new Location();
        locations[1].setLatitude("22.222");
        locations[1].setLongitude("33.333");
    }

    @Override
    protected GenericDAO<Location> getDao() {
        return locationDAO;
    }

    @Override
    protected Location[] getModel() {
        return locations;
    }
}
