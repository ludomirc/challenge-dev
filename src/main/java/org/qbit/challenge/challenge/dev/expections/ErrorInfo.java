package org.qbit.challenge.challenge.dev.expections;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({ "time", "code", "message" })
public class ErrorInfo {

    private String time;
    private String code;
    private String message;
}