package by.itacademy.entity;

import by.itacademy.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

/**
 * Created by Yury V. on 01.07.17.
 */

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class AccountUser extends BaseEntity {

    @Column(name = "login", nullable = false, unique = true)
    @Getter
    @Setter
    private String login;

    @Column(name = "password", nullable = false)
    @Getter
    @Setter
    private String password;

    @Column(name = "role", nullable = false, columnDefinition = "varchar")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Role role;

}
