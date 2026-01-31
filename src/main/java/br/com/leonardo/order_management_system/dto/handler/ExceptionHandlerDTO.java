package br.com.leonardo.order_management_system.dto.handler;

import java.time.LocalDateTime;

public class ExceptionHandlerDTO {

    private final LocalDateTime timestamp;
    private final int status;
    private final String message;

    public ExceptionHandlerDTO(LocalDateTime timestamp, int status, String message){
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
