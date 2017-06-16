package by.itacademy.entity;

import by.itacademy.entity.enums.AffairStatus;
import by.itacademy.entity.enums.MemberStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Yury V. on 08.06.17.
 */

public class ReportTest {

    private static SessionFactory SESSION_FACTORY;

    @Before
    public void init() {
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
    }

    @Test
    public void reportSaveAndRetrieveTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);
        session.save(affair);

        Clan clan = new Clan();
        clan.setName("Corleonesi");
        session.save(clan);

        Caporegime caporegime = new Caporegime();
        caporegime.setMemberStatus(MemberStatus.AVAILABLE);
        caporegime.setClan(clan);
        caporegime.setEmail("xxx@xxx.com");
        session.save(caporegime);

        Report report = new Report();
        report.setAffair(affair);
        report.setCaporegime(caporegime);
        Long id = (Long) session.save(report);

        Report retrievedReport = session.load(Report.class, id);
        session.getTransaction().commit();
        session.close();

        Assert.assertEquals(report, retrievedReport);
    }

    @Test
    public void reportDeletingTest() {
        Session session = SESSION_FACTORY.openSession();
        session.beginTransaction();

        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);
        session.save(affair);

        Clan clan = new Clan();
        clan.setName("Corleonesi");
        session.save(clan);

        Caporegime caporegime = new Caporegime();
        caporegime.setMemberStatus(MemberStatus.AVAILABLE);
        caporegime.setClan(clan);
        caporegime.setEmail("xxx@xxx.com");
        session.save(caporegime);

        Report report = new Report();
        report.setAffair(affair);
        report.setCaporegime(caporegime);
        Long id = (Long) session.save(report);

        session.delete(report);
        Report retrievedReport = session.get(Report.class, id);

        session.getTransaction().commit();
        session.close();

        Assert.assertNull(retrievedReport);
    }

    @After
    public void destroy() {
        SESSION_FACTORY.close();
    }

}
