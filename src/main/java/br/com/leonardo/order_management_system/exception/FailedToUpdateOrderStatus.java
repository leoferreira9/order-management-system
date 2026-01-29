package br.com.leonardo.order_management_system.exception;

public class FailedToUpdateOrderStatus extends RuntimeException {
    public FailedToUpdateOrderStatus(String message) {
        super(message);
    }
}
