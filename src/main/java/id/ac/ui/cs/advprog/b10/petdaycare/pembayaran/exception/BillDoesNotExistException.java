package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran.exception;

public class BillDoesNotExistException extends RuntimeException {
    public BillDoesNotExistException(Integer id) {
        super("Bill with id " + id + " does not exist");
    }

}
