package org.qbit.challenge.challenge.dev.dto;

import lombok.*;

import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto implements Serializable {

    private Long id;

    private String userId;

    @Size(min = 1, max = 140)
    private String body;
}
