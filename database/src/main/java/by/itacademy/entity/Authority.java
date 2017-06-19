package by.itacademy.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Created by Yury V. on 14.06.17.
 */

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@ToString(callSuper = true)
@PrimaryKeyJoinColumn(name = "member_id")
public class Authority extends Member {

    @Column(name = "votes")
    @Getter
    @Setter
    private Integer votes;

    @Column(name = "is_boss")
    @Getter
    @Setter
    private boolean isBoss;

}