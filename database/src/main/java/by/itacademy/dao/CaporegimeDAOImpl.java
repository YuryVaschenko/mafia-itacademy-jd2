package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.Caporegime;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

@Repository
public class CaporegimeDAOImpl extends GenericDAOImpl<Caporegime> implements CaporegimeDAO {

    @Override
    public Caporegime findByAccountUserLogin(String login) {
        Session session = getSessionFactory().getCurrentSession();
        List<Caporegime> resultList =
                session.createQuery("from Caporegime as capo where capo.accountUser.login = :login",
                        Caporegime.class).setParameter("login", login).getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }

}
