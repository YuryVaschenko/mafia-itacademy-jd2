package by.itacademy.dao;

import by.itacademy.entity.Clan;
import by.itacademy.utils.HibernateUtil;
import org.hibernate.Session;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Yury V. on 20.06.17.
 */

public class ClanDAOTest {

    @Test
    public void findClanByNameTest() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        ClanDAO clanDAO = new ClanDAOImpl();
        Clan checkForNullClan = clanDAO.findClanByName("Null");

        String clanName = "Carleone";
        Clan clan = new Clan();
        clan.setName(clanName);

        clanDAO.saveNew(clan);
        Clan retrievedClan = clanDAO.findClanByName(clanName);

        session.getTransaction().rollback();

        Assert.assertNull(checkForNullClan);
        Assert.assertEquals(clan, retrievedClan);
    }

}
