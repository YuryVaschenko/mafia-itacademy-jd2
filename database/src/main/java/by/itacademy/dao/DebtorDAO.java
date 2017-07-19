package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAO;
import by.itacademy.entity.Debtor;

import java.util.List;

/**
 * Created by Yury V. on 20.06.17.
 */

public interface DebtorDAO extends GenericDAO<Debtor> {

    List<Debtor> getPaginatedListOfDebtors(int firstResult, int maxResult);

    List<Debtor> findLimitedDebtorsOrderedByExpDate(int limit);

    List<Debtor> findOverdueDebtorsOrderedByExpDate();

}
