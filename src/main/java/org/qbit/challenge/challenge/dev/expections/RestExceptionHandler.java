package org.qbit.challenge.challenge.dev.expections;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MessengersException.class)
    public ResponseEntity<ErrorInfo> messengersExceptionHandler(Exception ex, WebRequest request) {

        logError(ex, request);

        return new ResponseEntity<>(getInfo(ex), HttpStatus.NOT_FOUND);
    }

    private ErrorInfo getInfo(Exception ex) {
        ErrorInfo info =  new ErrorInfo();
        info.setTime(LocalDateTime.now().toString());
        info.setMessage(ex.getMessage());
        info.setCode(HttpStatus.NOT_FOUND.toString());
        return info;
    }

    private void logError(Exception ex, WebRequest request) {
        logger.error(ex);
        logger.error(request);
    }
}