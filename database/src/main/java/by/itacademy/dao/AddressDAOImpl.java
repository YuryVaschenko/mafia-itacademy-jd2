package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAOImpl;
import by.itacademy.entity.Address;
import by.itacademy.entity.Address_;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Yury V. on 28.05.17.
 */

@Repository
public class AddressDAOImpl extends GenericDAOImpl<Address> implements AddressDAO {

    @Override
    public List<Address> findAddressesByCityName(String city) {

        Session session = getSessionFactory().getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Address> criteria = cb.createQuery(Address.class);
        Root<Address> address = criteria.from(Address.class);
        Path<String> cityName = address.get(Address_.city);

        criteria.select(address)
                .where(cb.equal(cityName, city));

        return session.createQuery(criteria).getResultList();
    }

}
