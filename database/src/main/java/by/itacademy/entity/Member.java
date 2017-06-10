package by.itacademy.entity;

import by.itacademy.entity.enums.MemberStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Yury V. on 10.06.17.
 */

@Entity
@Table(name = "members")
@ToString
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Member extends BaseEntity {

    @Embedded
    @Getter
    @Setter
    private NameDetails nameDetails;

    @Column (name = "status")
    @Enumerated
    @Getter
    @Setter
    private MemberStatus memberStatus;

    @ManyToOne (optional = false)
    @JoinColumn (name = "clan_id")
    @Getter
    @Setter
    private Clan clan;

}
