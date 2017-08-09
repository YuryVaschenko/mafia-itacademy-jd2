package by.itacademy.fetch;

import by.itacademy.entity.Location;
import by.itacademy.entity.NameDetails;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Created by Yury V. on 13.07.17.
 */

@Repository
@Scope("prototype")
@Data
public class ClanAndBossBaseInfoSample {

    private String clanName;
    private Location clanLocation;
    private NameDetails bossNameDetails;

}
