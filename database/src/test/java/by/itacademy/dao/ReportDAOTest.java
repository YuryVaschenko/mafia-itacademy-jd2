package by.itacademy.dao;

import by.itacademy.config.TestDbConfig;
import by.itacademy.dao.common.GenericDAO;
import by.itacademy.dao.common.GenericDAOTest;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Affair;
import by.itacademy.entity.Caporegime;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Report;
import by.itacademy.entity.enums.AffairStatus;
import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Role;
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
@ContextConfiguration(classes = {TestDbConfig.class})
@Transactional
public class ReportDAOTest extends GenericDAOTest<Report> {

    @Autowired
    private ReportDAO reportDAO;
    @Autowired
    private AccountUserDAO accountUserDAO;
    @Autowired
    private AffairDAO affairDAO;
    @Autowired
    private ClanDAO clanDAO;
    @Autowired
    private CaporegimeDAO caporegimeDAO;

    private Report[] reports = new Report[2];

    @Before
    public void entitiesInit() {
        Affair affair = new Affair();
        affair.setTitle("Rob a bank");
        affair.setStatus(AffairStatus.IN_PROGRESS);
        affairDAO.saveNew(affair);

        Clan clan = new Clan();
        clan.setName("Corleonesi");
        clanDAO.saveNew(clan);

        AccountUser user = new AccountUser();
        user.setLogin("login");
        user.setPassword("pass");
        user.setRole(Role.CAPOREGIME);
        accountUserDAO.saveNew(user);
        AccountUser nextUser = new AccountUser();
        nextUser.setLogin("log");
        nextUser.setPassword("password");
        nextUser.setRole(Role.SOLDIER);
        accountUserDAO.saveNew(nextUser);

        Caporegime firstCapo = new Caporegime();
        firstCapo.setMemberStatus(MemberStatus.AVAILABLE);
        firstCapo.setClan(clan);
        firstCapo.setEmail("xxx@xxx.com");
        firstCapo.setAccountUser(user);
        caporegimeDAO.saveNew(firstCapo);

        Caporegime secondCapo = new Caporegime();
        secondCapo.setMemberStatus(MemberStatus.IN_JAIL);
        secondCapo.setClan(clan);
        secondCapo.setEmail("yyy@yyy.net");
        secondCapo.setAccountUser(nextUser);
        caporegimeDAO.saveNew(secondCapo);

        reports[0] = new Report();
        reports[0].setAffair(affair);
        reports[0].setCaporegime(firstCapo);

        reports[1] = new Report();
        reports[1].setAffair(affair);
        reports[1].setCaporegime(secondCapo);
    }

    @Override
    protected GenericDAO<Report> getDao() {
        return reportDAO;
    }

    @Override
    protected Report[] getModel() {
        return reports;
    }
}
