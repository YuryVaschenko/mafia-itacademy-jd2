package by.itacademy.services;

import by.itacademy.dto.RegisterNewDebtorInfoSample;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Debtor;

import java.util.List;

/**
 * Created by Yury V. on 18.07.17.
 */
public interface DebtorService {

    void registerNewDebtor(RegisterNewDebtorInfoSample sample, Clan clan);

    List<Debtor> getPaginatedListOfDebtors(Long clanId, int firstResult, int maxResult);

    int getDebtorsCount(Long clanId);

}
