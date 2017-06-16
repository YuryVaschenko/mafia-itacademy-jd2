package by.itacademy.entity;

import by.itacademy.entity.enums.BlackListStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Yury V. on 16.06.17.
 */

@Entity
@Table(name = "black_list")
@NoArgsConstructor
@ToString
public class BlackList extends BaseEntity {

    @Embedded
    @Getter
    @Setter
    private NameDetails nameDetails;

    @Column (name = "status")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private BlackListStatus blackListStatus;

    @ManyToOne
    @JoinColumn (name = "location_id")
    @Getter
    @Setter
    private Location location;

}
