package by.itacademy.services;

import by.itacademy.dao.ClanDAO;
import by.itacademy.entity.Clan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Yury V. on 13.07.17.
 */

@Service
@Transactional
public class ClanServiceImpl implements ClanService {

    private final ClanDAO clanDAO;

    @Autowired
    public ClanServiceImpl(ClanDAO clanDAO) {
        this.clanDAO = clanDAO;
    }

    @Override
    public List<Clan> getAllClans() {
        return clanDAO.findAll();
    }

    @Override
    public Long saveNewClan(Clan clan) {
        return clanDAO.saveNew(clan);
    }
}
