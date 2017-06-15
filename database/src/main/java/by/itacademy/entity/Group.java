package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Yury V. on 15.06.17.
 */

@Entity
@Table(name = "groups")
@ToString(callSuper = true)
@NoArgsConstructor
public class Group extends BaseEntity {

    @Column(name = "clan_id")
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "clan_id")
    private Clan clan;

    @OneToOne
    @Getter
    @Setter
    @JoinColumn(name = "caporegime_id")
    private Caporegime caporegime;

}
