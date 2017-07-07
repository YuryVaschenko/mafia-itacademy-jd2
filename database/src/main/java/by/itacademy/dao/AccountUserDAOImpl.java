package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.AccountUser;
import by.itacademy.entity.AccountUser_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yury V. on 01.07.17.
 */

@Repository
public class AccountUserDAOImpl extends GenericDAOImpl<AccountUser> implements AccountUserDAO {

    @Override
    public AccountUser findAccountUserByLogin(String userLogin) {

        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<AccountUser> criteria = cb.createQuery(AccountUser.class);
        Root<AccountUser> user = criteria.from(AccountUser.class);
        Path<String> dbUserName = user.get(AccountUser_.login);

        criteria.select(user)
                .where(cb.equal(dbUserName, userLogin));

        List<AccountUser> resultList = session.createQuery(criteria).getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
