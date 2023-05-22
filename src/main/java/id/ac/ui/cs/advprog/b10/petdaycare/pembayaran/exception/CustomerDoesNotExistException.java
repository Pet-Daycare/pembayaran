package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;

public class CustomerDoesNotExistException extends RuntimeException  {

    public CustomerDoesNotExistException(String username) {
        super("Customer with id " + username + " does not exist");
    }
}

