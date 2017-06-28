package by.itacademy.entity;

import by.itacademy.entity.enums.Frequency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

/**
 * Created by Yury V. on 09.06.17.
 */

@Entity
@Table(name = "debtors")
@NoArgsConstructor
@ToString(callSuper = true)
public class Debtor extends BaseEntity {

    @Embedded
    @Getter
    @Setter
    private NameDetails nameDetails;

    @Column(name = "debt_amount")
    @Getter
    @Setter
    private Integer debtAmount;

    @Column(name = "exp_date", nullable = false)
    @Getter
    @Setter
    private LocalDate expDate;

    @Column(name = "percent_per_day")
    @Getter
    @Setter
    private Integer percentPerDay;

    @Column(name = "frequency")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private Frequency frequency;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @Getter
    @Setter
    private Address address;

}
