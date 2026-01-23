package br.com.leonardo.order_management_system.exception;

public class FailedToCancelOrder extends RuntimeException {
    public FailedToCancelOrder(String message) {
        super(message);
    }
}
