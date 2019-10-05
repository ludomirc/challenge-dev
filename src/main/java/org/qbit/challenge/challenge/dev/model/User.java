package org.qbit.challenge.challenge.dev.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "duser")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = 1;

    @Id
    @Size(min = 1, max = 50)
    private String id;
}
