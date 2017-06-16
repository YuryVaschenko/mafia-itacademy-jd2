package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Yury V. on 28.05.17.
 */

@Entity
@Table(name = "locations")
@ToString(exclude = {"address"}, callSuper = true)
@NoArgsConstructor
public class Location extends BaseEntity {

    @Column(name = "latitude", nullable = false)
    @Getter
    @Setter
    private String latitude;

    @Column(name = "longitude", nullable = false)
    @Getter
    @Setter
    private String longitude;

    @OneToOne(mappedBy = "location", cascade = CascadeType.REMOVE)
    @Getter
    @Setter
    private Address address;

}