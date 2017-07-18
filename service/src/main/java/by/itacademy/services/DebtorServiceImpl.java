package by.itacademy.services;

import by.itacademy.aspect.LogInvokedMethods;
import by.itacademy.dao.AddressDAO;
import by.itacademy.dao.DebtorDAO;
import by.itacademy.dto.RegisterNewDebtorInfoSample;
import by.itacademy.entity.Address;
import by.itacademy.entity.Clan;
import by.itacademy.entity.Debtor;
import by.itacademy.entity.NameDetails;
import by.itacademy.entity.enums.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Yury V. on 18.07.17.
 */

@Service
@Transactional
@LogInvokedMethods
public class DebtorServiceImpl implements DebtorService {

    private final DebtorDAO debtorDAO;
    private final AddressDAO addressDAO;

    @Autowired
    public DebtorServiceImpl(DebtorDAO debtorDAO, AddressDAO addressDAO) {
        this.debtorDAO = debtorDAO;
        this.addressDAO = addressDAO;
    }

    @Override
    public void registerNewDebtor(RegisterNewDebtorInfoSample sample, Clan clan) {

        Debtor debtor = new Debtor();
        debtor.setClan(clan);
        Address address = getAddressFromSample(sample);
        if (address != null) {
            addressDAO.saveNew(address);
            debtor.setAddress(address);
        }
        NameDetails nameDetails = getNameDetailsFromSample(sample);
        debtor.setNameDetails(nameDetails);
        debtor.setExpDate(sample.getExpDate());
        debtor.setFrequency(Frequency.ONCE);
        debtor.setDebtAmount(sample.getDebtAmount());
        if (sample.getPercentPerDay() != null) {
            debtor.setPercentPerDay(sample.getPercentPerDay());
        } else {
            debtor.setPercentPerDay(10);
        }

        debtorDAO.saveNew(debtor);
    }

    private Address getAddressFromSample(RegisterNewDebtorInfoSample sample) {
        Address address = new Address();

        if (sample.getCountry() != null) {
            address.setCountry(sample.getCountry());
            if (sample.getCity() != null) {           // city can`t exists without country
                address.setCity(sample.getCity());
                if (sample.getStreet() != null) {     // street can`t exists without city
                    address.setStreet(sample.getStreet());
                    if (sample.getHouse() != null) {  // house can`t exists without street
                        address.setHouse(sample.getHouse());
                    }
                }
            }
        } else {    // return null if address not valid or empty
            return null;
        }
        return address;
    }

    private NameDetails getNameDetailsFromSample(RegisterNewDebtorInfoSample sample) {
        NameDetails nameDetails = new NameDetails();
        if (sample.getFirstName() != null) {
            nameDetails.setFirstName(sample.getFirstName());
        }
        if (sample.getLastName() != null) {
            nameDetails.setLastName(sample.getLastName());
        }
        if (sample.getMiddleName() != null) {
            nameDetails.setMiddleName(sample.getMiddleName());
        }
        if (sample.getNickName() != null) {
            nameDetails.setNickName(sample.getNickName());
        }
        return nameDetails;
    }

}
