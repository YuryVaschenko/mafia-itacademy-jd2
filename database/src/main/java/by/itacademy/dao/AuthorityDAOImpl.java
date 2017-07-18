package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.Authority;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

@Repository
public class AuthorityDAOImpl extends GenericDAOImpl<Authority> implements AuthorityDAO {

    @Override
    public Authority findByAccountUserLogin(String login) {
        Session session = getSessionFactory().getCurrentSession();
        List<Authority> resultList =
                session.createQuery("from Authority as auth where auth.accountUser.login = :login",
                        Authority.class).setParameter("login", login).getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
