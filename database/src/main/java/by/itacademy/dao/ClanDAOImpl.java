package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Clan_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

@Repository
public class ClanDAOImpl extends GenericDAOImpl<Clan> implements ClanDAO {

    @Override
    public Clan findClanByName(String clanName) {

        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Clan> criteria = cb.createQuery(Clan.class);
        Root<Clan> clan = criteria.from(Clan.class);
        Path<String> dbClanName = clan.get(Clan_.name);

        criteria.select(clan)
                .where(cb.equal(dbClanName, clanName));

        List<Clan> resultList = session.createQuery(criteria).getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }

    @Override
    public Long findClanIdByAccountUserLogin(String login) {

        Session session = getSessionFactory().getCurrentSession();
        List<Long> resultList =
                session.createQuery("select mb.clan.id from Member as mb where mb.accountUser.login = :login",
                        Long.class).setParameter("login", login).getResultList();

        return resultList.isEmpty() ? null : resultList.get(0);
    }
}
