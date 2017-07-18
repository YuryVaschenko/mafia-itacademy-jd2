package by.itacademy.entity;

import by.itacademy.entity.enums.Frequency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

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
    private Long debtAmount;

    @Column(name = "exp_date", nullable = false)
    @Getter
    @Setter
    @DateTimeFormat (pattern = "dd.MM.yyyy")
    private LocalDate expDate;

    @Column(name = "percent_per_day", columnDefinition = "smallint")
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
