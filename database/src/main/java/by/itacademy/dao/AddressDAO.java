package by.itacademy.dao;

import by.itacademy.entity.Address;
import by.itacademy.entity.Address_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */

public class AddressDAO extends AbstractGenericDAO<Address> {


    public List<Address> getAddressByCityName(String city) {

        CriteriaBuilder cb = getSession().getCriteriaBuilder();
        CriteriaQuery<Address> criteria = cb.createQuery(Address.class);
        Root<Address> address = criteria.from(Address.class);
        Path<String> cityName = address.get(Address_.city);

        criteria.select(address)
                .where(cb.equal(cityName, city));

        return getSession().createQuery(criteria).getResultList();
    }

}
