package org.qbit.challenge.challenge.dev.web;

import org.qbit.challenge.challenge.dev.service.TimeLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@RestController
@RequestMapping("/v001")
public class TimeLineResource {

    @Autowired
    TimeLineService timeLineService;

    @PostMapping("/time-line/{userId}/{observedUserId}")
    ResponseEntity<?> createTimeLine(
            @Valid @Size(min = 1, max = 50) @PathVariable String userId,
            @Valid @Size(min = 1, max = 50) @PathVariable String observedUserId) {

        timeLineService.saveTimeLine(userId,observedUserId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}