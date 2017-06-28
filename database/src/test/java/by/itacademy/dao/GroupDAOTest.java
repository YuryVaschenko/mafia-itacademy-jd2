package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Group;
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
public class GroupDAOTest extends GenericDAOTest<Group> {

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private ClanDAO clanDAO;

    private Group[] groups = new Group[2];

    @Before
    public void entitiesInit() {
        Clan firstClan = new Clan();
        firstClan.setName("Benedetti");
        clanDAO.saveNew(firstClan);
        Clan secondClan = new Clan();
        secondClan.setName("Trabia");
        clanDAO.saveNew(secondClan);
        groups[0] = new Group();
        groups[0].setClan(firstClan);

        groups[1] = new Group();
        groups[1].setClan(secondClan);
    }

    @Override
    protected GenericDAO<Group> getDao() {
        return groupDAO;
    }

    @Override
    protected Group[] getModel() {
        return groups;
    }
}
