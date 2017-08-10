package by.itacademy.dto;

import by.itacademy.dto.validation.SpecializationValid;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Yury V. on 18.07.17.
 */

@Data
public class RegisterNewSoldierInfoSample {

    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;
    @SpecializationValid
    private String specialization;
    @NotEmpty(message = "validation.error.login_empty")
    private String login;
    @NotEmpty(message = "validation.error.password_empty")
    private String password;

}
