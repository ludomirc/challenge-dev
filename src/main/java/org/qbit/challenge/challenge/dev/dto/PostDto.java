package org.qbit.challenge.challenge.dev.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostDto implements Serializable{

    private Long id;
    private String userId;
    private String body;
}
