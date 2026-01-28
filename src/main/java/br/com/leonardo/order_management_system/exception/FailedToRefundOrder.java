package br.com.leonardo.order_management_system.exception;

public class FailedToRefundOrder extends RuntimeException {
    public FailedToRefundOrder(String message) {
        super(message);
    }
}
