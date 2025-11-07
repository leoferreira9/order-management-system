package leo.order_management_system.exceptions;

public class ClientAlreadyRegistered extends RuntimeException {
    public ClientAlreadyRegistered(String message) {
        super(message);
    }
}
