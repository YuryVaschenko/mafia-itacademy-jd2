package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yury V. on 14.06.17.
 */

@Entity
@Table(name = "caporegimes")
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"reports"})
@PrimaryKeyJoinColumn(name = "member_id")
public class Caporegime extends Member {

    @Column(name = "email", nullable = false)
    @Getter
    @Setter
    private String email;

    @OneToOne
    @JoinColumn(name = "group_id")
    @Getter
    @Setter
    private Group group;

    @OneToMany (mappedBy = "caporegime")
    @Getter
    @Setter
    private Set<Report> reports = new HashSet<>();

}
