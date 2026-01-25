package br.com.leonardo.order_management_system.exception;

public class FailedToCreateOrder extends RuntimeException {
    public FailedToCreateOrder(String message) {
        super(message);
    }
}
