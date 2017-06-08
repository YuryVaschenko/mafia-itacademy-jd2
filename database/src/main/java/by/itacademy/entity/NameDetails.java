package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Created by Yury V. on 08.06.17.
 */

@Embeddable
@NoArgsConstructor
@ToString
public class NameDetails {

    @Column(name = "first_name")
    @Getter
    @Setter
    private String firstName;

    @Column(name = "middle_name")
    @Getter
    @Setter
    private String middleName;

    @Column(name = "last_name")
    @Getter
    @Setter
    private String lastName;

    @Column(name = "nickname")
    @Getter
    @Setter
    private String nickName;


}
