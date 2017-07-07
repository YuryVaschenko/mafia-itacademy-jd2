package by.itacademy.entity;

import by.itacademy.entity.enums.AffairStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Yury V. on 16.06.17.
 */

@Entity
@Table(name = "affairs")
@NoArgsConstructor
@ToString(callSuper = true, exclude = {"groups", "reports"})
public class Affair extends BaseEntity {

    @Column(name = "title", nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(name = "description", columnDefinition = "text")
    @Getter
    @Setter
    private String description;

    @Column(name = "exp_date")
    @Getter
    @Setter
    private LocalDateTime expDateTime;

    @Column(name = "status", nullable = false, columnDefinition = "varchar")
    @Enumerated (EnumType.STRING)
    @Getter
    @Setter
    private AffairStatus status;

    @ManyToOne
    @JoinColumn(name = "debtor_id")
    @Getter
    @Setter
    private Debtor debtor;

    @ManyToOne
    @JoinColumn(name = "black_list_id")
    @Getter
    @Setter
    private BlackList victim;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @Getter
    @Setter
    private Location location;

    @ManyToMany
    @JoinTable(name = "affairs_in_groups",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "affair_id")
    )
    @Getter
    @Setter
    private Set<Group> groups = new HashSet<>();

    @OneToMany(mappedBy = "affair")
    @Getter
    @Setter
    private Set<Report> reports = new HashSet<>();


}
