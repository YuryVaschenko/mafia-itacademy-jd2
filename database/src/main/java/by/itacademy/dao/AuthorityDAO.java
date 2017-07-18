package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAO;
import by.itacademy.entity.Authority;

/**
 * Created by Yury V. on 20.06.17.
 */

public interface AuthorityDAO extends GenericDAO<Authority> {

    Authority findByAccountUserLogin(String login);

}
