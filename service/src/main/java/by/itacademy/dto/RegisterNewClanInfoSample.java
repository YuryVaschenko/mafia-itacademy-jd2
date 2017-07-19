package by.itacademy.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Pattern;

/**
 * Created by Yury V. on 17.07.17.
 */

@Repository
@Scope("prototype")
@Data
public class RegisterNewClanInfoSample {

    @NotEmpty(message = "Clan name must be not empty")
    private String clanName;
    @NotEmpty(message = "Longitude must be not empty")
    @Pattern(regexp = "\\d{2}[.]\\d{1,6}")
    private String longitude;
    @NotEmpty(message = "Latitude must be not empty")
    @Pattern(regexp = "\\d{2}[.]\\d{1,6}")
    private String latitude;
    @NotEmpty(message = "Login must be not empty")
    private String login;
    private String password;
}
