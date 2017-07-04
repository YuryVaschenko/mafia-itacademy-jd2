package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAO;
import by.itacademy.entity.AccountUser;

/**
 * Created by Yury V. on 01.07.17.
 */

public interface AccountUserDAO extends GenericDAO<AccountUser> {

    AccountUser findAccountUserByLogin(String userLogin);

}
