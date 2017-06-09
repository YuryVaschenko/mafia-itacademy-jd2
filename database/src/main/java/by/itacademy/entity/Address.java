package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Yury V. on 08.06.17.
 */

@Entity
@Table(name = "addresses")
@ToString(exclude = {"location"}, callSuper = true)
@NoArgsConstructor
public class Address extends BaseEntity {


    @Column(name = "country")
    @Getter
    @Setter
    private String country;

    @Column(name = "city")
    @Getter
    @Setter
    private String city;

    @Column(name = "street")
    @Getter
    @Setter
    private String street;

    @Column(name = "house")
    @Getter
    @Setter
    private String house;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id", unique = true)
    @Getter
    @Setter
    private Location location;

}
