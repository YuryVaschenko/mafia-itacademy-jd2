package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAO;
import by.itacademy.entity.Group;

import java.util.List;

/**
 * Created by Yury V. on 20.06.17.
 */

public interface GroupDAO extends GenericDAO<Group> {

    List<Group> findAllGroupsByClanName(String clanName);

}
