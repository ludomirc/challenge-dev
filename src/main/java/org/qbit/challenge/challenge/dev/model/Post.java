package org.qbit.challenge.challenge.dev.model;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@Entity
@Table(name = "post")
public class Post extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(name ="body", length = 140, nullable = false)
    private String body;

}
