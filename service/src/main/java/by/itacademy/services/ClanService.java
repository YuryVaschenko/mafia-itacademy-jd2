package by.itacademy.services;

import by.itacademy.dto.RegisterNewClanInfoSample;
import by.itacademy.entity.Clan;

import java.util.List;

/**
 * Created by Yury V. on 13.07.17.
 */

public interface ClanService {

    List<Clan> getAllClans();

    void registerNewClanBossAndAccountUser(RegisterNewClanInfoSample sample);

    Long findClanIdByLogin(String login);

    boolean isClanNameExists(String clanName);

}
