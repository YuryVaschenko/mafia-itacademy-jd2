package by.itacademy.dto;

import by.itacademy.dto.validation.LongLatValidation;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Yury V. on 17.07.17.
 */

@Data
public class RegisterNewClanInfoSample {

    @NotEmpty(message = "validation.error.clan_name_empty")
    private String clanName;
    @LongLatValidation
    private String longitude;
    @LongLatValidation
    private String latitude;
    @NotEmpty(message = "validation.error.login_empty")
    private String login;
    @NotEmpty(message = "validation.error.password_empty")
    private String password;
}
