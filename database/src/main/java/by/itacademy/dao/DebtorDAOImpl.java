package by.itacademy.dao;

import by.itacademy.dao.common.AbstractGenericDAOImpl;
import by.itacademy.entity.Debtor;
import by.itacademy.entity.Debtor_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Yury V. on 19.06.17.
 */

public class DebtorDAOImpl extends AbstractGenericDAOImpl<Debtor> implements DebtorDAO {

    @Override
    public List<Debtor> findLimitedDebtorsOrderedByExpDate(int limit) {
        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Debtor> criteria = cb.createQuery(Debtor.class);

        Root<Debtor> debtor = criteria.from(Debtor.class);
        Path<LocalDate> date = debtor.get(Debtor_.expDate);

        criteria.select(debtor)
                .orderBy(cb.asc(date));

        return getSession().createQuery(criteria).setMaxResults(limit).getResultList();
    }

    @Override
    public List<Debtor> findOverdueDebtors() {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Debtor> criteria = cb.createQuery(Debtor.class);

        Root<Debtor> debtor = criteria.from(Debtor.class);
        Path<LocalDate> expDate = debtor.get(Debtor_.expDate);

        criteria.select(debtor).where(cb.lessThan(expDate, LocalDate.now()));

        return getSession().createQuery(criteria).getResultList();
    }

}
