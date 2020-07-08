package ua.com.admin.console.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ua.com.admin.console.dto.ExceptionDto;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ExceptionDto> handleEntityNotFound(EntityNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), HttpStatus.NOT_FOUND);

        return ResponseEntity
                .status(exceptionDto.getHttpStatus())
                .body(exceptionDto);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ExceptionDto> handleIllegalArgumentException(RuntimeException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage(), HttpStatus.BAD_REQUEST);

        return ResponseEntity
                .status(exceptionDto.getHttpStatus())
                .body(exceptionDto);
    }
}
