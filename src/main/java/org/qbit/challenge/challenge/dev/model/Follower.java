package org.qbit.challenge.challenge.dev.model;

import lombok.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.List;

@Entity
@Table(name = "follower")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Follower extends AbstractPersistable<Long> {


    @OneToOne
    @JoinColumn(name = "owner_id", unique = true)
    private User owner;

    @OneToMany
    @JoinTable(name = "follower_duser",
            joinColumns = {@JoinColumn(name = "follower_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "duser_id", referencedColumnName = "id", unique = true)}
    )
    private List<User> observed;
}
