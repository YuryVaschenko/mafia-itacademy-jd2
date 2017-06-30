package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.BlackList;
import by.itacademy.entity.Location;
import by.itacademy.entity.NameDetails;
import by.itacademy.entity.enums.BlackListStatus;
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
public class BlackListDAOTest extends GenericDAOTest<BlackList> {

    @Autowired
    private BlackListDAO blackListDAO;
    @Autowired
    private LocationDAO locationDAO;

    private BlackList[] blackLists = new BlackList[2];

    @Before
    public void entitiesInit() {
        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Giovanni");
        nameDetails.setLastName("Falcone");

        Location location = new Location();
        location.setLongitude("43.33");
        location.setLatitude("90.22");
        locationDAO.saveNew(location);

        blackLists[0] = new BlackList();
        blackLists[0].setNameDetails(nameDetails);
        blackLists[0].setBlackListStatus(BlackListStatus.WANTED);

        blackLists[1] = new BlackList();
        blackLists[1].setLocation(location);
        blackLists[1].setBlackListStatus(BlackListStatus.DIFFERED);
    }

    @Override
    protected GenericDAO<BlackList> getDao() {
        return blackListDAO;
    }

    @Override
    protected BlackList[] getModel() {
        return blackLists;
    }
}
