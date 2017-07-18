package by.itacademy.dao;

import by.itacademy.dao.common.GenericDAO;
import by.itacademy.entity.Caporegime;

/**
 * Created by Yury V. on 20.06.17.
 */

public interface CaporegimeDAO extends GenericDAO<Caporegime> {

    Caporegime findByAccountUserLogin(String login);

}
