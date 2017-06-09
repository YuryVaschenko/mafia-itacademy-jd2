package by.itacademy.entity;

import by.itacademy.dao.DAOEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Created by Yury V. on 08.06.17.
 */

@MappedSuperclass
@ToString
@EqualsAndHashCode
abstract class BaseEntity implements DAOEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true, columnDefinition = "INT UNSIGNED")
    @Getter
    @Setter
    private Long id;

}
