package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.Debtor;
import by.itacademy.entity.Debtor_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

@Repository
public class DebtorDAOImpl extends GenericDAOImpl<Debtor> implements DebtorDAO {

    @Override
    public List<Debtor> getPaginatedListOfDebtors(Long clanId, int firstResult, int maxResult) {

        Session session = getSessionFactory().getCurrentSession();

        return session.createQuery("from Debtor deb where deb.clan.id = :clanId", Debtor.class)
                .setParameter("clanId", clanId)
                .setFirstResult(firstResult)
                .setMaxResults(maxResult).getResultList();
    }

    @Override
    public List<Debtor> findLimitedDebtorsOrderedByExpDate(int limit) {
        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Debtor> criteria = cb.createQuery(Debtor.class);
        Root<Debtor> debtor = criteria.from(Debtor.class);
        Path<LocalDate> date = debtor.get(Debtor_.expDate);

        criteria.select(debtor)
                .orderBy(cb.asc(date));

        return session.createQuery(criteria).setMaxResults(limit).getResultList();
    }

    @Override
    public List<Debtor> findOverdueDebtorsOrderedByExpDate() {

        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Debtor> criteria = cb.createQuery(Debtor.class);
        Root<Debtor> debtor = criteria.from(Debtor.class);
        Path<LocalDate> expDate = debtor.get(Debtor_.expDate);

        criteria.select(debtor).where(cb.lessThan(expDate, LocalDate.now())).orderBy(cb.asc(expDate));

        return session.createQuery(criteria).getResultList();
    }

    @Override
    public int getDebtorsCount(Long clanId) {

        Session session = getSessionFactory().getCurrentSession();

        //Criteria works fine but uses join
        /*
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = cb.createQuery(Long.class);
        Root<Debtor> debtor = criteria.from(Debtor.class);
        Join<Debtor, Clan> clan = debtor.join(Debtor_.clan);

        criteria.select(cb.count(debtor))
                .where(cb.equal(clan.get(Clan_.id), clanId));

        return Math.toIntExact(session.createQuery(criteria).uniqueResult());
        */

        return Math.toIntExact(session.createQuery("select count(*) from Debtor deb where deb.clan.id = :clanId", Long.class)
                .setParameter("clanId", clanId).uniqueResult());
    }

}
