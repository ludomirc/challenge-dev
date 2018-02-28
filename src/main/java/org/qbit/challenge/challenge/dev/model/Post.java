package org.qbit.challenge.challenge.dev.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "post")
@Getter
@Setter
@ToString
public class Post extends AbstractPersistable<Long> {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Size(min = 1, max = 140)
    @Column(name = "body", length = 140, nullable = false)
    private String body;

    public void setId(Long id) {
        super.setId(id);
    }
}
