package by.itacademy.services;

import by.itacademy.aspect.LogInvokedMethods;
import by.itacademy.dao.AccountUserDAO;
import by.itacademy.dao.AuthorityDAO;
import by.itacademy.dao.CaporegimeDAO;
import by.itacademy.dao.SoldierDAO;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public MemberServiceImpl(AccountUserDAO accountUserDAO, AuthorityDAO authorityDAO, CaporegimeDAO caporegimeDAO, SoldierDAO soldierDAO) {
        this.accountUserDAO = accountUserDAO;
        this.authorityDAO = authorityDAO;
        this.caporegimeDAO = caporegimeDAO;
        this.soldierDAO = soldierDAO;
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
}
