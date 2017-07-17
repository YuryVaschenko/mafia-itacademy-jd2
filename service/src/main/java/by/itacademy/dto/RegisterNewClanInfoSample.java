package by.itacademy.dto;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Created by Yury V. on 17.07.17.
 */

@Repository
@Scope("prototype")
@Data
public class RegisterNewClanInfoSample {

    private String clanName;
    private String longitude;
    private String latitude;
    private String login;
    private String password;
}
