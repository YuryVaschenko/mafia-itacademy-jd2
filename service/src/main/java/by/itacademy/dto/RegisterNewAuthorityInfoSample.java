package by.itacademy.dto;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/**
 * Created by Yury V. on 18.07.17.
 */

@Repository
@Scope("prototype")
@Data
public class RegisterNewAuthorityInfoSample {

    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;
    @NotEmpty(message = "validation.error.login_empty")
    private String login;
    @NotEmpty(message = "validation.error.password_empty")
    private String password;
}
