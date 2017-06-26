package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.AffairDAO;
import by.itacademy.entity.enums.AffairStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 16.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class AffairTest {

    @Autowired
    private AffairDAO affairDAO;

    @Test
    public void affairSaveAndRetrieveTest() {
        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);

        Long id = affairDAO.saveNew(affair);
        Affair retrievedAffair = affairDAO.findById(id);

        Assert.assertEquals(affair, retrievedAffair);
    }

    @Test
    public void affairDeletingTest() {
        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);

        Long id = affairDAO.saveNew(affair);
        affairDAO.delete(affair);

        Affair retrievedAffair = affairDAO.findById(id);

        Assert.assertNull(retrievedAffair);
    }

}
