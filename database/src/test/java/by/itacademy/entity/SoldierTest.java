package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.ClanDAO;
import by.itacademy.dao.GroupDAO;
import by.itacademy.dao.SoldierDAO;
import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Specialization;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 15.06.17.
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
public class SoldierTest {

    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private SoldierDAO soldierDAO;

    @Test
    public void soldierSaveAndRetrieveTest() {
        Clan clan = new Clan();
        clan.setName("Carioka");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        groupDAO.saveNew(group);
        Soldier soldier = new Soldier();
        soldier.setClan(clan);
        soldier.setSpecialization(Specialization.PIMP);
        soldier.setGroup(group);
        soldier.setMemberStatus(MemberStatus.AVAILABLE);
        Long id = soldierDAO.saveNew(soldier);

        Soldier retrievedSoldier = soldierDAO.findById(id);

        Assert.assertEquals(soldier, retrievedSoldier);
    }

    @Test
    public void soldierDeletingTest() {
        Clan clan = new Clan();
        clan.setName("Carioka");
        clanDAO.saveNew(clan);
        Group group = new Group();
        group.setClan(clan);
        groupDAO.saveNew(group);
        Soldier soldier = new Soldier();
        soldier.setClan(clan);
        soldier.setSpecialization(Specialization.PIMP);
        soldier.setGroup(group);
        soldier.setMemberStatus(MemberStatus.AVAILABLE);

        Long id = soldierDAO.saveNew(soldier);
        soldierDAO.delete(soldier);
        Soldier retrievedSoldier = soldierDAO.findById(id);

        Assert.assertNull(retrievedSoldier);
    }

}
