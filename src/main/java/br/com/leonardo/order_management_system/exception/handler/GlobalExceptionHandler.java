package br.com.leonardo.order_management_system.exception.handler;

import br.com.leonardo.order_management_system.dto.handler.ExceptionHandlerDTO;

import br.com.leonardo.order_management_system.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionHandlerDTO> handleEntityNotFoundException(EntityNotFoundException ex){
        ExceptionHandlerDTO handler = new ExceptionHandlerDTO(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(handler);
    }

    @ExceptionHandler({FailedToCancelOrder.class, FailedToCreateOrder.class, FailedToRefundOrder.class, FailedToUpdateOrderStatus.class, UpdateNotAvailable.class})
    public ResponseEntity<ExceptionHandlerDTO> handleBusinessRuleExceptions(Exception ex){
        ExceptionHandlerDTO handler = new ExceptionHandlerDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handler);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ExceptionHandlerDTO> handleInvalidEnumValue(Exception ex){
        ExceptionHandlerDTO handler = new ExceptionHandlerDTO(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), "Invalid enum value");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(handler);
    }
}
