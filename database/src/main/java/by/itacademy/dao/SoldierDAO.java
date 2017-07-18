package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAO;
import by.itacademy.entity.Soldier;

/**
 * Created by Yury V. on 20.06.17.
 */

public interface SoldierDAO extends GenericDAO<Soldier> {

    Soldier findByAccountUserLogin(String login);

}
