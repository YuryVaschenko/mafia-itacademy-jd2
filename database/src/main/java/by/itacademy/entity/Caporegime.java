package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Yury V. on 14.06.17.
 */

@Entity
@Table(name = "caporegimes")
@NoArgsConstructor
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "member_id")
public class Caporegime extends Member {

    @Column(name = "email")
    @Getter
    @Setter
    private String email;

    @OneToOne(mappedBy = "caporegime")
    @Getter
    @Setter
    private Group group;

}
