package by.itacademy.services;

import by.itacademy.aspect.LogInvokedMethods;
import by.itacademy.dao.AccountUserDAO;
import by.itacademy.entity.AccountUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 04.07.17.
 */

@Service
@Transactional
@LogInvokedMethods
public class UserServiceImpl implements UserService {

    private final Logger logger = Logger.getLogger(UserServiceImpl.class);
    private final AccountUserDAO accountUserDAO;

    @Autowired
    public UserServiceImpl(AccountUserDAO accountUserDAO) {
        this.accountUserDAO = accountUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountUser foundUser = accountUserDAO.findAccountUserByLogin(username);
        if (foundUser == null) {
            logger.warn("Can't find user by provided login: " + username);
            throw new UsernameNotFoundException("Can't find user by provided login!");
        }

        return User
                .withUsername(foundUser.getLogin())
                .password(foundUser.getPassword())
                .authorities(foundUser.getRole().name())
                .build();
    }

    @Override
    public boolean isAccountUserExists(String login) {
        return null != accountUserDAO.findAccountUserByLogin(login);
    }
}
