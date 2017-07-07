package by.itacademy.services;

import by.itacademy.dao.AccountUserDAO;
import by.itacademy.entity.AccountUser;
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
public class UserServiceImpl implements UserService {

    private final AccountUserDAO accountUserDAO;

    @Autowired
    public UserServiceImpl(AccountUserDAO accountUserDAO) {
        this.accountUserDAO = accountUserDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountUser foundUser = accountUserDAO.findAccountUserByLogin(username);
        if (foundUser == null) {
            throw new UsernameNotFoundException("Can't find user by provided login!");
        }

        return User
                .withUsername(foundUser.getLogin())
                .password(foundUser.getPassword())
                .authorities(foundUser.getRole().name())
                .build();
    }


}
