package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAO;
import by.itacademy.entity.Address;

import java.util.List;

/**
 * Created by Yury V. on 20.06.17.
 */

public interface AddressDAO extends GenericDAO<Address> {

    List<Address> findAddressesByCityName(String city);

}
