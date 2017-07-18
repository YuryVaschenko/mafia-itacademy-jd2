package by.itacademy.services;

import by.itacademy.dto.RegisterNewDebtorInfoSample;
import by.itacademy.entity.Clan;

/**
 * Created by Yury V. on 18.07.17.
 */
public interface DebtorService {

    void registerNewDebtor(RegisterNewDebtorInfoSample sample, Clan clan);

}
