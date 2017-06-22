package by.itacademy.dao;

import by.itacademy.dao.common.AbstractGenericDAOImpl;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Clan_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

public class ClanDAOImpl extends AbstractGenericDAOImpl<Clan> implements ClanDAO {

    @Override
    public Clan findClanByName(String clanName) {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Clan> criteria = cb.createQuery(Clan.class);
        Root<Clan> clan = criteria.from(Clan.class);
        Path<String> dbClanName = clan.get(Clan_.name);

        criteria.select(clan)
                .where(cb.equal(dbClanName, clanName));

        List<Clan> resultList = getSession().createQuery(criteria).getResultList();

        if (resultList.size() > 0) {
            return resultList.get(0);
        }
        return null;
    }
}
