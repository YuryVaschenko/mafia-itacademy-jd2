package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.Soldier;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

@Repository
public class SoldierDAOImpl extends GenericDAOImpl<Soldier> implements SoldierDAO {

    @Override
    public Soldier findByAccountUserLogin(String login) {
        Session session = getSessionFactory().getCurrentSession();
        List<Soldier> resultList =
                session.createQuery("from Soldier as soldier where soldier.accountUser.login = :login",
                        Soldier.class).setParameter("login", login).getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
