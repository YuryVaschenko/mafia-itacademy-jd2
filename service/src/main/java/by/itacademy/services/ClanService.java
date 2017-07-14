package by.itacademy.services;

import by.itacademy.entity.Clan;

import java.util.List;

/**
 * Created by Yury V. on 13.07.17.
 */

public interface ClanService {

    List<Clan> getAllClans();
    Long saveNewClan(Clan clan);

}
