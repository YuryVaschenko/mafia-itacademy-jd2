package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Yury V. on 01.07.17.
 */

@Repository
public class UserDAOImpl extends GenericDAOImpl<User> implements UserDAO {

}
