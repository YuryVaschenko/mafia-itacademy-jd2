package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Set;

/**
 * Created by Yury V. on 08.06.17.
 */

@Entity
@ToString(exclude = {"members"}, callSuper = true)
@NoArgsConstructor
@Table(name = "clans")
public class Clan extends BaseEntity {

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @Getter
    @Setter
    private Address address;



    @OneToMany(mappedBy = "clan")
    @Getter
    @Setter
    private Set<Member> members;

}
