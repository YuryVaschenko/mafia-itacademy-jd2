package by.itacademy.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Created by Yury V. on 18.07.17.
 */

@Data
public class RegisterNewDebtorInfoSample {

    private String firstName;
    private String middleName;
    private String lastName;
    private String nickName;
    private Long debtAmount;
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate expDate;
    private Integer percentPerDay;
    private String country;
    private String city;
    private String street;
    private String house;

}
