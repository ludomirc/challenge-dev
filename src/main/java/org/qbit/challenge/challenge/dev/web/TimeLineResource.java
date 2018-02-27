package org.qbit.challenge.challenge.dev.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/v001")
public class TimeLineResource {

    @PostMapping("/time-line/{userId}/{observedUserId}")
    ResponseEntity<?> createTimeLine(
            @Valid @Size(min = 1, max = 50) @PathVariable String userId,
            @Valid @Size(min = 1, max = 50) @PathVariable String followedUserId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }
}