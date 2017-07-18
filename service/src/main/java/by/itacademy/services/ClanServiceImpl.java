package by.itacademy.services;

import by.itacademy.aspect.LogInvokedMethods;
import by.itacademy.dao.AccountUserDAO;
import by.itacademy.dao.AuthorityDAO;
import by.itacademy.dao.ClanDAO;
import by.itacademy.dao.LocationDAO;
import by.itacademy.dto.RegisterNewClanInfoSample;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Authority;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Location;
import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yury V. on 13.07.17.
 */

@Service
@Transactional
@LogInvokedMethods
public class ClanServiceImpl implements ClanService {

    private final ClanDAO clanDAO;
    private final LocationDAO locationDAO;
    private final AuthorityDAO authorityDAO;
    private final AccountUserDAO accountUserDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClanServiceImpl(ClanDAO clanDAO, LocationDAO locationDAO, AuthorityDAO authorityDAO, AccountUserDAO accountUserDAO, PasswordEncoder passwordEncoder) {
        this.clanDAO = clanDAO;
        this.locationDAO = locationDAO;
        this.authorityDAO = authorityDAO;
        this.accountUserDAO = accountUserDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Clan> getAllClans() {
        return clanDAO.findAll();
    }

    @Override
    public void registerNewClanBossAndAccountUser(RegisterNewClanInfoSample sample) {

        Location clanLocation = new Location();
        clanLocation.setLongitude(sample.getLongitude());
        clanLocation.setLatitude(sample.getLatitude());
        locationDAO.saveNew(clanLocation);
        Clan clan = new Clan();
        clan.setName(sample.getClanName());
        clan.setLocation(clanLocation);
        clanDAO.saveNew(clan);

        AccountUser accountUser = new AccountUser();
        accountUser.setLogin(sample.getLogin());
        accountUser.setPassword(passwordEncoder.encode(sample.getPassword()));
        accountUser.setRole(Role.AUTHORITY);
        accountUserDAO.saveNew(accountUser);

        Authority godFather = new Authority();
        godFather.setBoss(true);
        godFather.setMemberStatus(MemberStatus.AVAILABLE);
        godFather.setClan(clan);
        godFather.setAccountUser(accountUser);
        authorityDAO.saveNew(godFather);

    }

}
