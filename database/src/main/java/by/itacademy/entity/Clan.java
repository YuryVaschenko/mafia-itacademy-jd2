package by.itacademy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString(exclude = {"members", "groups", "debtors"}, callSuper = true)
@NoArgsConstructor
@Table(name = "clans")
public class Clan extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    @Getter
    @Setter
    private String name;

    @OneToOne
    @JoinColumn(name = "location_id")
    @Getter
    @Setter
    private Location location;

    @OneToMany(mappedBy = "clan")
    @Getter
    @Setter
    @JsonIgnore
    private Set<Group> groups;

    @OneToMany(mappedBy = "clan")
    @Getter
    @Setter
    @JsonIgnore
    private Set<Member> members = new HashSet<>();

    @OneToMany(mappedBy = "clan")
    @Getter
    @Setter
    @JsonIgnore
    private Set<Debtor> debtors = new HashSet<>();

}
