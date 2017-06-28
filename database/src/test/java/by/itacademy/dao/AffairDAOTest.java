package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.Affair;
import by.itacademy.entity.Location;
import by.itacademy.entity.enums.AffairStatus;
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
@ContextConfiguration(classes = TestConfig.class)
@Transactional
public class AffairDAOTest extends GenericDAOTest<Affair> {

    @Autowired
    private AffairDAO affairDAO;
    @Autowired
    private LocationDAO locationDAO;

    private Affair[] affairs = new Affair[2];

    @Before
    public void entitiesInit() {
        Location location = new Location();
        location.setLatitude("74.00");
        location.setLongitude("22.00");
        locationDAO.saveNew(location);

        affairs[0] = new Affair();
        affairs[0].setTitle("Rob a bank");
        affairs[0].setLocation(location);
        affairs[0].setStatus(AffairStatus.IN_PROGRESS);

        affairs[1] = new Affair();
        affairs[1].setTitle("Meet Ricco in jail");
        affairs[1].setDescription("Buy some grocery for Ricco");
        affairs[1].setStatus(AffairStatus.IN_PROGRESS);
    }

    @Override
    protected GenericDAO<Affair> getDao() {
        return affairDAO;
    }

    @Override
    protected Affair[] getModel() {
        return affairs;
    }
}
