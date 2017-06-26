package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.BlackListDAO;
import by.itacademy.entity.enums.BlackListStatus;
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
public class BlackListTest {

    @Autowired
    private BlackListDAO blackListDAO;

    @Test
    public void blackListSaveAndRetrieveTest() {
        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Giovanni");
        nameDetails.setLastName("Falcone");

        BlackList blackList = new BlackList();
        blackList.setNameDetails(nameDetails);
        blackList.setBlackListStatus(BlackListStatus.DEAD);

        Long id = blackListDAO.saveNew(blackList);
        BlackList retrievedBlackList = blackListDAO.findById(id);

        Assert.assertEquals(blackList, retrievedBlackList);
    }

    @Test
    public void blackListDeletingTest() {
        NameDetails nameDetails = new NameDetails();
        nameDetails.setFirstName("Giovanni");
        nameDetails.setLastName("Falcone");

        BlackList blackList = new BlackList();
        blackList.setNameDetails(nameDetails);
        blackList.setBlackListStatus(BlackListStatus.DEAD);

        Long id = blackListDAO.saveNew(blackList);
        blackListDAO.delete(blackList);
        BlackList retrievedBlackList = blackListDAO.findById(id);

        Assert.assertNull(retrievedBlackList);
    }

}
