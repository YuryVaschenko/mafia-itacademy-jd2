package by.itacademy.services;

import by.itacademy.aspect.LogInvokedMethods;
import by.itacademy.dao.AccountUserDAO;
import by.itacademy.dao.AuthorityDAO;
import by.itacademy.dao.CaporegimeDAO;
import by.itacademy.dao.ClanDAO;
import by.itacademy.dao.SoldierDAO;
import by.itacademy.dto.RegisterNewAuthorityInfoSample;
import by.itacademy.dto.RegisterNewCaporegimeInfoSample;
import by.itacademy.dto.RegisterNewSoldierInfoSample;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Authority;
import by.itacademy.entity.Caporegime;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Member;
import by.itacademy.entity.NameDetails;
import by.itacademy.entity.Soldier;
import by.itacademy.entity.enums.MemberStatus;
import by.itacademy.entity.enums.Role;
import by.itacademy.entity.enums.Specialization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 18.07.17.
 */

@Service
@Transactional
@LogInvokedMethods
public class MemberServiceImpl implements MemberService {

    private final AccountUserDAO accountUserDAO;
    private final AuthorityDAO authorityDAO;
    private final CaporegimeDAO caporegimeDAO;
    private final SoldierDAO soldierDAO;
    private final ClanDAO clanDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(AccountUserDAO accountUserDAO, AuthorityDAO authorityDAO, CaporegimeDAO caporegimeDAO, SoldierDAO soldierDAO, ClanDAO clanDAO, PasswordEncoder passwordEncoder) {
        this.accountUserDAO = accountUserDAO;
        this.authorityDAO = authorityDAO;
        this.caporegimeDAO = caporegimeDAO;
        this.soldierDAO = soldierDAO;
        this.clanDAO = clanDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Member findMemberByLogin(String login) {

        AccountUser user = accountUserDAO.findAccountUserByLogin(login);
        Member member = null;
        switch (user.getRole()) {
            case AUTHORITY:
                member = authorityDAO.findByAccountUserLogin(login);
                break;
            case CAPOREGIME:
                member = caporegimeDAO.findByAccountUserLogin(login);
                break;
            case SOLDIER:
                member = soldierDAO.findByAccountUserLogin(login);
        }
        return member;
    }

    @Override
    public Long saveNewAuthority(Long clanId, RegisterNewAuthorityInfoSample authorityInfoSample) {

        AccountUser accountUser = new AccountUser();
        accountUser.setRole(Role.AUTHORITY);
        accountUser.setLogin(authorityInfoSample.getLogin());
        accountUser.setPassword(passwordEncoder.encode(authorityInfoSample.getPassword()));
        accountUserDAO.saveNew(accountUser);

        Clan clan = clanDAO.findById(clanId);

        Authority authority = new Authority();
        authority.setClan(clan);
        authority.setMemberStatus(MemberStatus.AVAILABLE);
        authority.setBoss(false);
        authority.setAccountUser(accountUser);
        NameDetails nameDetails = setNameDetailsAvoidingEmptyStringsInDataBase(
                authorityInfoSample.getFirstName(),
                authorityInfoSample.getMiddleName(),
                authorityInfoSample.getLastName(),
                authorityInfoSample.getNickName());
        authority.setNameDetails(nameDetails);

        return authorityDAO.saveNew(authority);
    }

    @Override
    public Long saveNewCaporegime(Long clanId, RegisterNewCaporegimeInfoSample caporegimeInfoSample) {

        AccountUser accountUser = new AccountUser();
        accountUser.setRole(Role.CAPOREGIME);
        accountUser.setLogin(caporegimeInfoSample.getLogin());
        accountUser.setPassword(passwordEncoder.encode(caporegimeInfoSample.getPassword()));
        accountUserDAO.saveNew(accountUser);

        Clan clan = clanDAO.findById(clanId);

        Caporegime caporegime = new Caporegime();
        caporegime.setClan(clan);
        caporegime.setMemberStatus(MemberStatus.AVAILABLE);
        caporegime.setEmail(caporegimeInfoSample.getEmail());
        caporegime.setAccountUser(accountUser);
        NameDetails nameDetails = setNameDetailsAvoidingEmptyStringsInDataBase(
                caporegimeInfoSample.getFirstName(),
                caporegimeInfoSample.getMiddleName(),
                caporegimeInfoSample.getLastName(),
                caporegimeInfoSample.getNickName());
        caporegime.setNameDetails(nameDetails);

        return caporegimeDAO.saveNew(caporegime);
    }

    @Override
    public Long saveNewSoldier(Long clanId, RegisterNewSoldierInfoSample soldierInfoSample) {
        AccountUser accountUser = new AccountUser();
        accountUser.setRole(Role.SOLDIER);
        accountUser.setLogin(soldierInfoSample.getLogin());
        accountUser.setPassword(passwordEncoder.encode(soldierInfoSample.getPassword()));
        accountUserDAO.saveNew(accountUser);

        Clan clan = clanDAO.findById(clanId);

        Soldier soldier = new Soldier();
        soldier.setClan(clan);
        soldier.setMemberStatus(MemberStatus.AVAILABLE);
        soldier.setSpecialization(Specialization.valueOf(soldierInfoSample.getSpecialization()));
        soldier.setAccountUser(accountUser);
        NameDetails nameDetails = setNameDetailsAvoidingEmptyStringsInDataBase(
                soldierInfoSample.getFirstName(),
                soldierInfoSample.getMiddleName(),
                soldierInfoSample.getLastName(),
                soldierInfoSample.getNickName());
        soldier.setNameDetails(nameDetails);

        return soldierDAO.saveNew(soldier);
    }

    private NameDetails setNameDetailsAvoidingEmptyStringsInDataBase(String firstName, String middleName, String lastName, String nickname) {

        NameDetails nameDetails = new NameDetails();
        if (!"".equals(firstName.trim())) {
            nameDetails.setFirstName(firstName);
        }
        if (!"".equals(middleName.trim())) {
            nameDetails.setMiddleName(middleName);
        }
        if (!"".equals(lastName.trim())) {
            nameDetails.setLastName(lastName);
        }
        if (!"".equals(nickname.trim())) {
            nameDetails.setNickName(nickname);
        }

        return nameDetails;
    }
}
