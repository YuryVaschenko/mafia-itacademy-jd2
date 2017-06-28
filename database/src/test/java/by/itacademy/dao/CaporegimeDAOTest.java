package by.itacademy.dao;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.Caporegime;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Group;
import by.itacademy.entity.enums.MemberStatus;
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
public class CaporegimeDAOTest extends GenericDAOTest<Caporegime> {

    @Autowired
    private CaporegimeDAO caporegimeDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private ClanDAO clanDAO;

    private Caporegime[] caporegimes = new Caporegime[2];

    @Before
    public void entitiesInit() {
        Clan clan = new Clan();
        clan.setName("Angelo La Barbera");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        groupDAO.saveNew(group);
        caporegimes[0] = new Caporegime();
        caporegimes[0].setClan(clan);
        caporegimes[0].setGroup(group);
        caporegimes[0].setEmail("xxx@xxx.com");
        caporegimes[0].setMemberStatus(MemberStatus.AVAILABLE);

        caporegimes[1] = new Caporegime();
        caporegimes[1].setClan(clan);
        caporegimes[1].setGroup(group);
        caporegimes[1].setEmail("yyy@yyy.net");
        caporegimes[1].setMemberStatus(MemberStatus.IN_HOSPITAL);
    }

    @Override
    protected GenericDAO<Caporegime> getDao() {
        return caporegimeDAO;
    }

    @Override
    protected Caporegime[] getModel() {
        return caporegimes;
    }
}
