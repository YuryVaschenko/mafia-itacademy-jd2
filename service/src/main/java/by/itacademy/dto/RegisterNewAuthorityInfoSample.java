package by.itacademy.dto;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

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

}
