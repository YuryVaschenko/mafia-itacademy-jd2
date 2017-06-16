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
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yury V. on 08.06.17.
 */

@Entity
@ToString(exclude = {"members", "groups"}, callSuper = true)
@NoArgsConstructor
@Table(name = "clans")
public class Clan extends BaseEntity {

    @Column(name = "name", nullable = false)
    @Getter
    @Setter
    private String name;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "address_id")
    @Getter
    @Setter
    private Address address;

    @OneToMany(mappedBy = "clan", cascade = CascadeType.REMOVE)
    @Getter
    @Setter
    private Set<Group> groups;

    @OneToMany(mappedBy = "clan", cascade = CascadeType.REMOVE)
    @Getter
    @Setter
    private Set<Member> members = new HashSet<>();

}
