package br.com.leonardo.order_management_system.exception;

public class UpdateNotAvailable extends RuntimeException {
    public UpdateNotAvailable(String message) {
        super(message);
    }
}
