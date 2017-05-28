package by.itacademy.pojos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Yury V. on 28.05.17.
 */

@Entity
@Table(name = "locations")
@ToString
@NoArgsConstructor
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column (name = "name")
    @Getter
    @Setter
    private String name;

    @Column (name = "coords")
    @Getter
    @Setter
    private String coords;

}