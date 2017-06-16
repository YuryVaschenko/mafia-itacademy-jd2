package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Yury V. on 16.06.17.
 */

@Entity
@Table(name = "reports")
@NoArgsConstructor
@ToString
public class Report extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "affair_id")
    @Getter
    @Setter
    private Affair affair;

    @ManyToOne(optional = false)
    @JoinColumn(name = "caporegime_id")
    @Getter
    @Setter
    private Caporegime caporegime;

    @Column(name = "content")
    @Getter
    @Setter
    private String content;
}
