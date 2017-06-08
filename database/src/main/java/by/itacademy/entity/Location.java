package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Yury V. on 28.05.17.
 */

@Entity
@Table(name = "location")
@ToString(exclude = {"address"})
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

    @OneToOne(mappedBy = "location")
    @Getter
    @Setter
    private Address address;

}