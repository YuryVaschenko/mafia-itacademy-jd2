package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Clan_;
import by.itacademy.entity.Group;
import by.itacademy.entity.Group_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

@Repository
public class GroupDAOImpl extends GenericDAOImpl<Group> implements GroupDAO {

    @Override
    public List<Group> findAllGroupsByClanName(String clanName) {

        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Group> criteria = cb.createQuery(Group.class);
        Root<Group> group = criteria.from(Group.class);
        Join<Group, Clan> clan = group.join(Group_.clan);
        Path<String> clanCheckedName = clan.get(Clan_.name);


        criteria.select(group).where(cb.equal(clanCheckedName, clanName));

        return session.createQuery(criteria).getResultList();
    }
}
