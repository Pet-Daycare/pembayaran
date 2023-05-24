package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;

public class CustomerAlreadyExistException extends RuntimeException{

    public CustomerAlreadyExistException(String username) {
        super("Customer with id " + username + " already exist!");
    }
}
