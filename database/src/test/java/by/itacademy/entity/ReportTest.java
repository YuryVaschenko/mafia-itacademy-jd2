package by.itacademy.entity;

import by.itacademy.config.TestConfig;
import by.itacademy.dao.AffairDAO;
import by.itacademy.dao.CaporegimeDAO;
import by.itacademy.dao.ClanDAO;
import by.itacademy.dao.ReportDAO;
import by.itacademy.entity.enums.AffairStatus;
import by.itacademy.entity.enums.MemberStatus;
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
public class ReportTest {

    @Autowired
    private AffairDAO affairDAO;
    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private CaporegimeDAO caporegimeDAO;
    @Autowired
    private ReportDAO reportDAO;

    @Test
    public void reportSaveAndRetrieveTest() {
        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);
        affairDAO.saveNew(affair);

        Clan clan = new Clan();
        clan.setName("Corleonesi");
        clanDAO.saveNew(clan);

        Caporegime caporegime = new Caporegime();
        caporegime.setMemberStatus(MemberStatus.AVAILABLE);
        caporegime.setClan(clan);
        caporegime.setEmail("xxx@xxx.com");
        caporegimeDAO.saveNew(caporegime);

        Report report = new Report();
        report.setAffair(affair);
        report.setCaporegime(caporegime);
        Long id = reportDAO.saveNew(report);

        Report retrievedReport = reportDAO.findById(id);

        Assert.assertEquals(report, retrievedReport);
    }

    @Test
    public void reportDeletingTest() {
        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);
        affairDAO.saveNew(affair);

        Clan clan = new Clan();
        clan.setName("Corleonesi");
        clanDAO.saveNew(clan);

        Caporegime caporegime = new Caporegime();
        caporegime.setMemberStatus(MemberStatus.AVAILABLE);
        caporegime.setClan(clan);
        caporegime.setEmail("xxx@xxx.com");
        caporegimeDAO.saveNew(caporegime);

        Report report = new Report();
        report.setAffair(affair);
        report.setCaporegime(caporegime);
        Long id = reportDAO.saveNew(report);

        reportDAO.delete(report);
        Report retrievedReport = reportDAO.findById(id);

        Assert.assertNull(retrievedReport);
    }

}
