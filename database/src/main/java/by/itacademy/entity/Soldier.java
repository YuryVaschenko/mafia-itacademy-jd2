package by.itacademy.entity;

import by.itacademy.entity.enums.Specialization;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Yury V. on 14.06.17.
 */

@Entity
@Table(name = "soldiers")
@NoArgsConstructor
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "member_id")
public class Soldier extends Member {

    @Column(name = "specializacion")
    @Getter
    @Setter
    @Enumerated (EnumType.STRING)
    private Specialization specialization;

    @ManyToOne
    @Getter
    @Setter
    @JoinColumn (name = "group_id")
    private Group group;

}
